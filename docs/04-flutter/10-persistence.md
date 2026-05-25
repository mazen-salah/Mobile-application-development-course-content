# Local Persistence

Three storage options, each for a different scale:

| Use case | Library |
|---|---|
| Small key-value (user prefs, flags) | `shared_preferences` |
| Local NoSQL (offline cache, fast) | `hive` |
| Full SQL (relations, queries) | `sqflite` / `drift` |

## shared_preferences — quick and tiny

```bash
flutter pub add shared_preferences
```

```dart
import 'package:shared_preferences/shared_preferences.dart';

Future<void> saveTheme(String theme) async {
  final prefs = await SharedPreferences.getInstance();
  await prefs.setString('theme', theme);
}

Future<String?> loadTheme() async {
  final prefs = await SharedPreferences.getInstance();
  return prefs.getString('theme');
}
```

Supports `String`, `int`, `double`, `bool`, `List<String>`. Stored in platform-specific storage (NSUserDefaults on iOS, SharedPreferences on Android, localStorage on web).

**Don't use for** sensitive data — it's not encrypted. Use `flutter_secure_storage` for tokens, passwords.

## Hive — fast NoSQL

```bash
flutter pub add hive hive_flutter
flutter pub add --dev hive_generator build_runner
```

```dart
// 1. Define a model
@HiveType(typeId: 0)
class Todo {
  @HiveField(0)
  String title;

  @HiveField(1)
  bool done;

  Todo({required this.title, required this.done});
}
```

```bash
dart run build_runner build
```

```dart
// 2. Initialize once
await Hive.initFlutter();
Hive.registerAdapter(TodoAdapter());

// 3. Open a "box" (table)
final box = await Hive.openBox<Todo>('todos');

// 4. Use it
box.add(Todo(title: 'Buy bread', done: false));
box.put('home', Todo(title: 'Mow lawn', done: false));

final all = box.values.toList();
final one = box.get('home');
box.delete('home');

// Reactive — listen for changes
box.listenable().addListener(() {
  // re-render
});
```

Hive is **much faster** than SQLite for object storage. No SQL, no schema migrations. Trade-off: no relational queries.

## sqflite — SQLite

For complex relational data:

```bash
flutter pub add sqflite path
```

```dart
final db = await openDatabase(
  join(await getDatabasesPath(), 'app.db'),
  version: 1,
  onCreate: (db, version) async {
    await db.execute('''
      CREATE TABLE tasks (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        title TEXT NOT NULL,
        done INTEGER NOT NULL DEFAULT 0
      )
    ''');
  },
);

await db.insert('tasks', {'title': 'Buy bread', 'done': 0});
final rows = await db.query('tasks', where: 'done = ?', whereArgs: [0]);
await db.update('tasks', {'done': 1}, where: 'id = ?', whereArgs: [1]);
await db.delete('tasks', where: 'id = ?', whereArgs: [1]);
```

For type safety + auto-generated queries, use **`drift`** (formerly Moor) — Flutter's answer to Room.

## drift — modern, type-safe SQLite

```bash
flutter pub add drift drift_dev sqlite3_flutter_libs
```

```dart
class Tasks extends Table {
  IntColumn get id => integer().autoIncrement()();
  TextColumn get title => text()();
  BoolColumn get done => boolean().withDefault(const Constant(false))();
}

@DriftDatabase(tables: [Tasks])
class AppDatabase extends _$AppDatabase {
  AppDatabase() : super(_openConnection());

  @override
  int get schemaVersion => 1;

  Stream<List<Task>> watchAll() => select(tasks).watch();
  Future<int> add(TasksCompanion task) => into(tasks).insert(task);
  Future<int> delete(int id) => (delete(tasks)..where((t) => t.id.equals(id))).go();
}
```

drift generates type-safe query methods, reactive streams, migration helpers, and joins. The Flutter equivalent of Android's Room — and if you went through Module 3, the patterns are nearly identical.

## flutter_secure_storage — for sensitive data

```bash
flutter pub add flutter_secure_storage
```

```dart
final storage = const FlutterSecureStorage();

await storage.write(key: 'jwt_token', value: 'eyJhbGc...');
final token = await storage.read(key: 'jwt_token');
await storage.delete(key: 'jwt_token');
```

Uses Keychain on iOS, EncryptedSharedPreferences on Android. Use this for **auth tokens, refresh tokens, payment data**.

## Pattern: repository abstraction

Don't call storage directly from your UI/BLoC. Wrap it:

```dart
abstract class TodoRepository {
  Stream<List<Todo>> watchAll();
  Future<void> add(Todo todo);
  Future<void> remove(int id);
}

class HiveTodoRepository implements TodoRepository {
  final Box<Todo> _box;
  HiveTodoRepository(this._box);

  @override
  Stream<List<Todo>> watchAll() => _box.watch().map((_) => _box.values.toList());

  @override
  Future<void> add(Todo todo) => _box.add(todo);

  @override
  Future<void> remove(int id) => _box.deleteAt(id);
}
```

Now the rest of your code doesn't know whether you're using Hive, Drift, or anything else. Easy to test, easy to migrate.

## Try it yourself

Add persistence to your Todo app:

1. Pick Hive (simple) or shared_preferences (sufficient for small lists)
2. Load todos on app start
3. Save on every change
4. Verify: kill the app, reopen — todos are still there

[← Previous](09-http-rest.md){ .md-button } [Next: Firebase →](11-firebase.md){ .md-button }
