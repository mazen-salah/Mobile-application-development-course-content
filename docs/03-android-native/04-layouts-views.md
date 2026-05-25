# Layouts & Views (XML)

The classic Android UI is built from **Views** (buttons, text, images) arranged inside **ViewGroups** (layout containers).

## The basic widgets

| Widget | What it shows |
|---|---|
| `TextView` | Text |
| `EditText` | Editable text input |
| `Button` | Tappable button |
| `ImageView` | An image |
| `Switch` / `CheckBox` / `RadioButton` | Toggles |
| `ProgressBar` | Loading indicator |
| `SeekBar` | Slider |
| `Spinner` | Dropdown |
| `WebView` | Embedded browser |

## The basic containers (ViewGroups)

| Container | How it arranges children |
|---|---|
| `LinearLayout` | Vertical or horizontal stack |
| `RelativeLayout` | Relative to parent or siblings (older, mostly replaced) |
| `ConstraintLayout` | Flexible constraints; **most powerful**, the modern default |
| `FrameLayout` | Stacked on top of each other |
| `ScrollView` / `NestedScrollView` | One scrollable child |

## A minimal layout

```xml title="res/layout/activity_main.xml"
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/welcome"
        android:textSize="24sp" />

    <EditText
        android:id="@+id/input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="@string/enter_name" />

    <Button
        android:id="@+id/submit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/submit" />

</LinearLayout>
```

## Width/height — the most important attribute

Every view must specify both:

| Value | Meaning |
|---|---|
| `match_parent` | "Fill the parent" |
| `wrap_content` | "Just as big as my contents" |
| `0dp` | "Let the layout decide" (used in ConstraintLayout) |
| `Xdp` | Exact size in density-independent pixels |

**Use `dp` (density-independent pixels) for sizes, `sp` for text.** `sp` respects the user's font scale settings — important for accessibility.

## Connecting layout to code

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title = findViewById<TextView>(R.id.title)
        val input = findViewById<EditText>(R.id.input)
        val submit = findViewById<Button>(R.id.submit)

        submit.setOnClickListener {
            val name = input.text.toString()
            title.text = "Hello, $name"
        }
    }
}
```

`findViewById` is the classic way. Modern code uses **View Binding** (auto-generated, type-safe) — we'll cover that in lesson 13.

## ConstraintLayout — the modern default

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Title"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <Button
        android:id="@+id/action"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Tap me"
        app:layout_constraintTop_toBottomOf="@id/title"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

The 4 directions: `Start`, `End`, `Top`, `Bottom`. Each view says where each of its edges is anchored. **Drag-and-drop in the Layout Editor** is the fastest way to learn this — generates the XML for you.

## Common attributes

```xml
android:padding="16dp"             <!-- space inside the view -->
android:layout_margin="16dp"       <!-- space outside the view -->
android:background="#FF5722"       <!-- background color -->
android:background="@drawable/bg"  <!-- background drawable -->
android:visibility="visible"       <!-- visible | invisible | gone -->
android:enabled="false"            <!-- grays out, blocks input -->
android:textColor="@color/primary"
android:textStyle="bold|italic"
android:gravity="center"           <!-- content alignment inside -->
android:layout_gravity="center"    <!-- this view's position in parent -->
```

## visibility — three states, not two

| Value | Effect |
|---|---|
| `visible` | Shown, takes space |
| `invisible` | Hidden, **still takes space** |
| `gone` | Hidden, **no space allocated** |

`gone` is what you want 99% of the time. `invisible` is for keeping layout stable.

## Reusing layouts with `<include>`

```xml
<include layout="@layout/section_header" />
```

Inlines the contents of `section_header.xml` here. Great for repeated UI fragments.

## Try it yourself

Build a "Profile" screen with:

- An `ImageView` at the top (use any placeholder from `@android:drawable`)
- Two `TextView`s below (name, bio)
- A `Button` at the bottom labeled "Edit"

When the button is tapped, change the name TextView to "Anonymous" temporarily.

??? success "Solution"
    ```xml
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="24dp">

        <ImageView
            android:id="@+id/avatar"
            android:layout_width="120dp"
            android:layout_height="120dp"
            android:layout_gravity="center"
            android:src="@android:drawable/sym_def_app_icon" />

        <TextView
            android:id="@+id/name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="16dp"
            android:text="Mazen Tamer"
            android:textSize="20sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/bio"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="8dp"
            android:text="Full-stack engineer" />

        <Button
            android:id="@+id/edit"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="24dp"
            android:text="Edit" />
    </LinearLayout>
    ```

    ```kotlin
    findViewById<Button>(R.id.edit).setOnClickListener {
        findViewById<TextView>(R.id.name).text = "Anonymous"
    }
    ```

[← Previous](03-activity-lifecycle.md){ .md-button } [Next: Intents →](05-intents.md){ .md-button }
