# Retrofit Networking

**Retrofit** turns REST API calls into Kotlin function calls. Define an interface, Retrofit generates the implementation.

## Setup

```kotlin
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}
```

And in your manifest:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Define your API

Suppose you're hitting `https://jsonplaceholder.typicode.com/todos`:

```kotlin
data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo

    @POST("todos")
    suspend fun create(@Body todo: Todo): Todo

    @PUT("todos/{id}")
    suspend fun update(@Path("id") id: Int, @Body todo: Todo): Todo

    @DELETE("todos/{id}")
    suspend fun delete(@Path("id") id: Int)

    @GET("todos")
    suspend fun search(
        @Query("userId") userId: Int? = null,
        @Query("completed") completed: Boolean? = null
    ): List<Todo>
}
```

## Build the Retrofit instance

```kotlin
object NetworkModule {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()

    val todoApi: TodoApi = retrofit.create(TodoApi::class.java)
}
```

`HttpLoggingInterceptor` logs every request/response to Logcat — invaluable for debugging.

## Call it from a ViewModel

```kotlin
class TodoViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init { loadTodos() }

    fun loadTodos() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val todos = NetworkModule.todoApi.getTodos()
                _state.value = UiState.Success(todos)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed interface UiState {
    object Loading : UiState
    data class Success(val data: List<Todo>) : UiState
    data class Error(val message: String) : UiState
}
```

## Observe in UI

```kotlin
lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED) {
        vm.state.collect { state ->
            when (state) {
                is UiState.Loading -> showSpinner()
                is UiState.Success -> showList(state.data)
                is UiState.Error -> showError(state.message)
            }
        }
    }
}
```

## Authentication — interceptors

To add a Bearer token to every request:

```kotlin
val authInterceptor = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}")
        .build()
    chain.proceed(request)
}

val client = OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
    .build()
```

## Error handling — sealed Result

```kotlin
sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val code: Int?, val message: String) : ApiResult<Nothing>
}

suspend fun <T> safeApiCall(call: suspend () -> T): ApiResult<T> = try {
    ApiResult.Success(call())
} catch (e: HttpException) {
    ApiResult.Error(e.code(), e.message())
} catch (e: IOException) {
    ApiResult.Error(null, "Network error")
} catch (e: Exception) {
    ApiResult.Error(null, e.message ?: "Unknown")
}

// usage:
val result = safeApiCall { api.getTodos() }
```

## Upload files

```kotlin
interface UploadApi {
    @Multipart
    @POST("upload")
    suspend fun upload(
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    ): UploadResponse
}

val filePart = MultipartBody.Part.createFormData(
    "file",
    file.name,
    file.asRequestBody("image/jpeg".toMediaType())
)
api.upload("My photo".toRequestBody(), filePart)
```

## Common patterns

- **Repository wraps the API** — keeps networking out of the ViewModel
- **Combine with Room** — Repository decides: cache hit → return DB; cache miss → fetch from API and store
- **Coroutines + Flow** — `Flow<List<Item>>` from DB + occasional refresh from API = offline-first apps

## Try it yourself

Use the public [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API:

1. Define `PostApi` with `getPosts()` and `getPost(id)`
2. ViewModel loads all posts on init
3. UI: RecyclerView of post titles; tap a post → second screen with full post body

[← Previous](10-room-database.md){ .md-button } [Next: Material Design →](12-material-design.md){ .md-button }
