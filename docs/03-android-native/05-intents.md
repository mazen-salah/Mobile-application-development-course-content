# Intents

An **Intent** is a message asking the system to "do something" — usually start an activity or pass data between screens.

## Two flavors

| Type | Use case |
|---|---|
| **Explicit** | You name the exact activity to launch (within your app) |
| **Implicit** | You describe an action ("open a URL", "share text") — the system picks an app |

## Explicit intent — start an activity in your app

```kotlin
// In MainActivity
val intent = Intent(this, DetailActivity::class.java)
startActivity(intent)
```

That launches `DetailActivity`. You must declare it in `AndroidManifest.xml`:

```xml
<activity android:name=".DetailActivity" android:exported="false" />
```

## Passing data via extras

```kotlin
val intent = Intent(this, DetailActivity::class.java).apply {
    putExtra("USER_ID", 42)
    putExtra("USER_NAME", "Mazen")
    putExtra("IS_ADMIN", true)
}
startActivity(intent)
```

In `DetailActivity`:

```kotlin
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra("USER_ID", -1)
        val name = intent.getStringExtra("USER_NAME")
        val isAdmin = intent.getBooleanExtra("IS_ADMIN", false)

        // use them...
    }
}
```

For type safety, define constants:

```kotlin
companion object {
    const val EXTRA_USER_ID = "user_id"
    const val EXTRA_USER_NAME = "user_name"

    fun launch(ctx: Context, id: Int, name: String) {
        val intent = Intent(ctx, DetailActivity::class.java).apply {
            putExtra(EXTRA_USER_ID, id)
            putExtra(EXTRA_USER_NAME, name)
        }
        ctx.startActivity(intent)
    }
}

// caller:
DetailActivity.launch(this, 42, "Mazen")
```

## Getting a result back

When you need data back from the launched activity (e.g., user picks an image):

```kotlin
// Modern API (registers BEFORE the launch — required in onCreate or earlier)
private val pickContact = registerForActivityResult(
    ActivityResultContracts.PickContact()
) { uri: Uri? ->
    if (uri != null) {
        // got a contact
    }
}

// Launch later, e.g. from a button:
button.setOnClickListener {
    pickContact.launch(null)
}
```

The old `startActivityForResult` / `onActivityResult` pattern is deprecated. Use the new `registerForActivityResult` API.

## Implicit intents — common system actions

### Open a URL

```kotlin
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://summationworks.com"))
startActivity(intent)
```

### Open a phone dialer

```kotlin
val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+201010112468"))
startActivity(intent)
```

### Send an email

```kotlin
val intent = Intent(Intent.ACTION_SENDTO).apply {
    data = Uri.parse("mailto:")  // only email apps respond
    putExtra(Intent.EXTRA_EMAIL, arrayOf("hello@example.com"))
    putExtra(Intent.EXTRA_SUBJECT, "Hi")
    putExtra(Intent.EXTRA_TEXT, "Body of the email")
}
startActivity(intent)
```

### Share text

```kotlin
val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, "Check out this app!")
}
startActivity(Intent.createChooser(intent, "Share via"))
```

`Intent.createChooser` always shows the system picker (rather than the user's default app).

### Open a map

```kotlin
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:31.2,29.9?z=12"))
startActivity(intent)
```

### Take a picture

Requires the camera permission and image capture handling — covered in lesson 12.

## Handle "no app can do this"

If the user has no email client and you launch a SENDTO email intent, you crash. Always check:

```kotlin
if (intent.resolveActivity(packageManager) != null) {
    startActivity(intent)
} else {
    Toast.makeText(this, "No app available", Toast.LENGTH_SHORT).show()
}
```

## Try it yourself

Build a 2-activity flow:

1. `MainActivity` — has an `EditText` and a button "Show"
2. `DisplayActivity` — receives the entered text via Intent extra and displays it in a large `TextView`
3. Add a second button on `MainActivity` labeled "Open Website" that launches an implicit intent to `https://google.com`

??? success "Solution"
    ```kotlin
    // MainActivity.kt
    findViewById<Button>(R.id.show).setOnClickListener {
        val text = findViewById<EditText>(R.id.input).text.toString()
        val i = Intent(this, DisplayActivity::class.java)
        i.putExtra(DisplayActivity.EXTRA_TEXT, text)
        startActivity(i)
    }

    findViewById<Button>(R.id.web).setOnClickListener {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com")))
    }

    // DisplayActivity.kt
    class DisplayActivity : AppCompatActivity() {
        companion object { const val EXTRA_TEXT = "text" }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_display)
            findViewById<TextView>(R.id.label).text =
                intent.getStringExtra(EXTRA_TEXT) ?: ""
        }
    }
    ```

    Remember to register `DisplayActivity` in `AndroidManifest.xml`.

[← Previous](04-layouts-views.md){ .md-button } [Next: RecyclerView →](06-recyclerview.md){ .md-button }
