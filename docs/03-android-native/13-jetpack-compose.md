# Jetpack Compose

Compose is Google's modern UI toolkit. Instead of XML layouts + imperative findViewById, you describe UI as Kotlin functions.

```kotlin
@Composable
fun Greeting(name: String) {
    Text("Hello, $name")
}
```

That's a UI element. Reusable, type-safe, no XML.

## Why Compose

| XML + View | Compose |
|---|---|
| Two languages (XML + Kotlin) | One language |
| `findViewById` boilerplate | Direct references |
| ViewBinding helps but still verbose | Inherently concise |
| State changes require manual updates | State changes auto-recompose |
| Reuse via custom Views (hard) | Reuse via Composables (trivial) |
| Slow recompile | Fast preview |

Google now recommends Compose for new projects. XML is still everywhere but is being phased out gradually.

## Setup

```kotlin
android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    val compose = "1.5.4"
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.8.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
```

## Your first Composable activity

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface {
                    Greeting("Mazen")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text("Hello, $name", fontSize = 24.sp)
}

@Preview
@Composable
fun GreetingPreview() {
    Greeting("Mazen")
}
```

`setContent { ... }` replaces `setContentView(R.layout.xxx)`. The `@Preview` shows the composable in Android Studio without running the app.

## State

```kotlin
@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Count: $count", fontSize = 24.sp)
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}
```

`mutableStateOf(0)` creates an observable Int. `remember` keeps it across recompositions. When `count` changes, the `Text` recomposes automatically — no `findViewById` and no `setText`.

For state that survives configuration changes:

```kotlin
var count by rememberSaveable { mutableIntStateOf(0) }
```

## Layouts

```kotlin
Column {           // vertical
    Text("Top")
    Text("Bottom")
}

Row {              // horizontal
    Text("Left")
    Text("Right")
}

Box {              // stacked (z-index)
    Image(...)
    Text("Caption", modifier = Modifier.align(Alignment.BottomEnd))
}
```

With modifiers for spacing, alignment, padding:

```kotlin
Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("Hello")
    Button(onClick = { }) { Text("OK") }
}
```

## Lists — LazyColumn (RecyclerView for Compose)

```kotlin
@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(users, key = { it.id }) { user ->
            UserRow(user)
        }
    }
}

@Composable
fun UserRow(user: User) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(user.name, style = MaterialTheme.typography.titleMedium)
            Text(user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
```

That's the whole list + each row, in 15 lines. Compare to XML + Adapter + ViewHolder.

## Theming

```kotlin
@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorScheme() else lightColorScheme()
    MaterialTheme(colorScheme = colors, content = content)
}
```

Reference colors anywhere: `MaterialTheme.colorScheme.primary`.

## Side effects

Run code when the screen first appears:

```kotlin
LaunchedEffect(Unit) {
    viewModel.loadData()
}
```

When a specific value changes:

```kotlin
LaunchedEffect(userId) {
    viewModel.fetch(userId)
}
```

## Collect state from ViewModel

```kotlin
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> UserList((state as UiState.Success).users)
        is UiState.Error -> Text("Error: ${(state as UiState.Error).message}")
    }
}
```

## Navigation in Compose

```kotlin
@Composable
fun App() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "home") {
        composable("home") { HomeScreen { nav.navigate("detail/$it") } }
        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetailScreen(id)
        }
    }
}
```

## Interop with old Views

You can drop a Composable into an XML-based screen:

```xml
<androidx.compose.ui.platform.ComposeView
    android:id="@+id/compose_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

```kotlin
findViewById<ComposeView>(R.id.compose_view).setContent {
    Greeting("Mazen")
}
```

And the reverse — embed a `View` in Compose with `AndroidView`. Lets you migrate gradually.

## Try it yourself

Build a small Compose screen from scratch:

1. Top: a Text showing "Counter App"
2. Middle: a number, starts at 0
3. Bottom: two buttons "+1" and "-1"
4. Buttons update the number live
5. Use `Material3` theme; light + dark modes work

??? success "Solution"
    ```kotlin
    @Composable
    fun CounterApp() {
        var count by rememberSaveable { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Counter App", style = MaterialTheme.typography.headlineMedium)
            Text("$count", style = MaterialTheme.typography.displayLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { count-- }) { Text("-1") }
                Button(onClick = { count++ }) { Text("+1") }
            }
        }
    }
    ```

[← Previous](12-material-design.md){ .md-button } [Next: Labs →](labs.md){ .md-button }
