# HTTP & REST

Two popular HTTP clients: the built-in **`http`** package (simple) and **`dio`** (interceptors, more features). Production apps usually pick `dio`.

## Option 1 — http (lightweight)

```bash
flutter pub add http
```

```dart
import 'package:http/http.dart' as http;
import 'dart:convert';

Future<List<Todo>> fetchTodos() async {
  final response = await http.get(
    Uri.parse('https://jsonplaceholder.typicode.com/todos'),
  );

  if (response.statusCode != 200) {
    throw Exception('Failed to load: ${response.statusCode}');
  }

  final List<dynamic> json = jsonDecode(response.body);
  return json.map((j) => Todo.fromJson(j)).toList();
}
```

```dart
class Todo {
  final int id;
  final String title;
  final bool completed;

  Todo({required this.id, required this.title, required this.completed});

  factory Todo.fromJson(Map<String, dynamic> json) => Todo(
    id: json['id'],
    title: json['title'],
    completed: json['completed'],
  );

  Map<String, dynamic> toJson() => {
    'id': id,
    'title': title,
    'completed': completed,
  };
}
```

POST/PUT/DELETE:

```dart
await http.post(
  Uri.parse('$baseUrl/todos'),
  headers: {'Content-Type': 'application/json'},
  body: jsonEncode(todo.toJson()),
);

await http.put(...);
await http.delete(Uri.parse('$baseUrl/todos/$id'));
```

## Option 2 — dio (recommended for real apps)

```bash
flutter pub add dio
```

```dart
class ApiClient {
  final Dio _dio = Dio(BaseOptions(
    baseUrl: 'https://api.example.com/',
    connectTimeout: const Duration(seconds: 10),
    receiveTimeout: const Duration(seconds: 10),
  ))..interceptors.add(LogInterceptor(responseBody: true));

  Future<List<Todo>> getTodos() async {
    final response = await _dio.get<List<dynamic>>('/todos');
    return response.data!.map((j) => Todo.fromJson(j)).toList();
  }

  Future<Todo> createTodo(Todo todo) async {
    final response = await _dio.post<Map<String, dynamic>>(
      '/todos',
      data: todo.toJson(),
    );
    return Todo.fromJson(response.data!);
  }
}
```

### Why dio over http

- Interceptors (auth tokens, logging, error retry)
- Built-in timeouts
- Cancel tokens
- Form data + multipart uploads
- Better error types (`DioException` with status, response, request)

## Auth interceptor pattern

```dart
class AuthInterceptor extends Interceptor {
  final AuthService _auth;
  AuthInterceptor(this._auth);

  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    final token = _auth.token;
    if (token != null) {
      options.headers['Authorization'] = 'Bearer $token';
    }
    handler.next(options);
  }

  @override
  void onError(DioException err, ErrorInterceptorHandler handler) async {
    if (err.response?.statusCode == 401) {
      // try refresh, retry once
      final newToken = await _auth.refresh();
      if (newToken != null) {
        // retry the original request
        final response = await Dio().fetch(err.requestOptions);
        return handler.resolve(response);
      }
    }
    handler.next(err);
  }
}
```

## JSON code generation (advanced)

For complex models, hand-rolled `fromJson` gets tedious. Use `json_serializable`:

```dart
@JsonSerializable()
class Todo {
  final int id;
  final String title;
  final bool completed;

  Todo({required this.id, required this.title, required this.completed});

  factory Todo.fromJson(Map<String, dynamic> json) => _$TodoFromJson(json);
  Map<String, dynamic> toJson() => _$TodoToJson(this);
}
```

Run `dart run build_runner build` and Dart generates the serialization for you.

## Error handling pattern

```dart
sealed class ApiResult<T> {
  const ApiResult();
}
class ApiSuccess<T> extends ApiResult<T> {
  final T data;
  const ApiSuccess(this.data);
}
class ApiError<T> extends ApiResult<T> {
  final String message;
  final int? code;
  const ApiError(this.message, {this.code});
}

Future<ApiResult<T>> safeCall<T>(Future<T> Function() call) async {
  try {
    return ApiSuccess(await call());
  } on DioException catch (e) {
    return ApiError(e.message ?? 'Network error', code: e.response?.statusCode);
  } catch (e) {
    return ApiError(e.toString());
  }
}

// usage
final result = await safeCall(() => api.getTodos());
switch (result) {
  case ApiSuccess(:final data): /* use data */
  case ApiError(:final message): /* show error */
}
```

## Wiring HTTP into a Cubit

```dart
class TodosCubit extends Cubit<TodosState> {
  final ApiClient _api;
  TodosCubit(this._api) : super(TodosInitial());

  Future<void> load() async {
    emit(TodosLoading());
    try {
      final todos = await _api.getTodos();
      emit(TodosLoaded(todos));
    } catch (e) {
      emit(TodosError(e.toString()));
    }
  }

  Future<void> create(String title) async {
    final todo = await _api.createTodo(Todo(id: 0, title: title, completed: false));
    if (state case TodosLoaded(:final todos)) {
      emit(TodosLoaded([todo, ...todos]));
    }
  }
}
```

## Testing API calls

Use `mocktail` or `mockito` to fake the Dio response:

```dart
test('load returns success', () async {
  final mock = MockApi();
  when(() => mock.getTodos()).thenAnswer((_) async => [Todo(...)]);

  final cubit = TodosCubit(mock);
  await cubit.load();

  expect(cubit.state, isA<TodosLoaded>());
});
```

## Try it yourself

Build a screen that:

1. Calls `https://jsonplaceholder.typicode.com/posts` on first build
2. Shows a loading spinner during fetch
3. Renders the post titles in a `ListView`
4. Shows an error message + Retry button on failure
5. Pull-to-refresh re-fetches

Use Cubit + dio. Bonus: cache the most-recent response in memory so a second open is instant.

[← Previous](08-bloc-pattern.md){ .md-button } [Next: Persistence →](10-persistence.md){ .md-button }
