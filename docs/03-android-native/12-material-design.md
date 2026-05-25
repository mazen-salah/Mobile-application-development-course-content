# Material Design

Material is Google's design system. The **Material Components for Android** library gives you ready-made widgets that look and behave the right way out of the box.

## Setup

```kotlin
dependencies {
    implementation("com.google.android.material:material:1.11.0")
}
```

Your app's theme must descend from `Theme.Material3.*`:

```xml title="res/values/themes.xml"
<resources>
    <style name="Theme.MyApp" parent="Theme.Material3.DayNight.NoActionBar">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorOnPrimary">@color/on_primary</item>
        <item name="colorSecondary">@color/secondary</item>
        <item name="android:windowBackground">@color/background</item>
    </style>
</resources>
```

`Theme.Material3.DayNight.*` automatically supports dark mode based on system settings.

## Common Material widgets

### MaterialButton

```xml
<com.google.android.material.button.MaterialButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Submit"
    app:icon="@drawable/ic_send" />
```

Style variants: `Widget.Material3.Button` (filled), `.Outlined`, `.Text`, `.Icon`.

### MaterialTextInput

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:endIconMode="clear_text">

    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />
</com.google.android.material.textfield.TextInputLayout>
```

Floating label, error state, clear button, password reveal — all built in.

### FloatingActionButton

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|end"
    android:layout_margin="16dp"
    android:src="@drawable/ic_add" />
```

### BottomNavigationView

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/bottom_nav"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:menu="@menu/bottom_nav" />
```

### MaterialCardView

```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardElevation="4dp"
    app:cardCornerRadius="12dp">

    <!-- card contents -->
</com.google.android.material.card.MaterialCardView>
```

### Snackbar — better than Toast

```kotlin
Snackbar.make(view, "Saved", Snackbar.LENGTH_LONG)
    .setAction("Undo") { undo() }
    .show()
```

## Color system

Material 3 organizes colors into roles, not raw values:

| Role | When to use |
|---|---|
| `colorPrimary` | Brand color, key buttons |
| `colorOnPrimary` | Text/icons on top of `colorPrimary` |
| `colorSecondary` | Accent color, FAB |
| `colorSurface` | Card backgrounds |
| `colorError` | Error states |

Reference them: `?attr/colorPrimary` in XML, or `MaterialColors.getColor(view, R.attr.colorPrimary)` in code.

## Typography scale

Material defines 13 named text styles. Use them instead of guessing sizes:

```xml
android:textAppearance="?attr/textAppearanceTitleLarge"
android:textAppearance="?attr/textAppearanceBodyMedium"
android:textAppearance="?attr/textAppearanceLabelSmall"
```

Sizes: `Display`, `Headline`, `Title`, `Body`, `Label` × `Large`/`Medium`/`Small`.

## Dark mode

If your theme inherits from `*.DayNight.*`, dark mode just works — as long as you reference colors by role (`?attr/colorPrimary`) instead of hard-coding hex.

To force a mode:

```kotlin
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
```

## Theme overrides

Want one screen to use a different color? Wrap it:

```xml
<LinearLayout android:theme="@style/Theme.MyApp.RedAccent">
    ...
</LinearLayout>
```

Define `Theme.MyApp.RedAccent` overriding the color attrs.

## Material Design 3 (M3)

Google's latest spec (2021+). Key changes from M2:

- **Dynamic color** — Android 12+ generates a palette from the user's wallpaper
- New components: `NavigationDrawer`, `BottomSheet`, expressive shapes
- Updated typography & elevation

Enable dynamic color:

```xml
<style name="Theme.MyApp" parent="Theme.Material3.DayNight.NoActionBar">
    ...
</style>
```

```kotlin
// in Application or MainActivity
DynamicColors.applyToActivitiesIfAvailable(this)
```

## Try it yourself

Rebuild a profile screen using only Material components:

- `MaterialToolbar` at the top with the user's name
- `MaterialCardView` containing avatar, bio, action buttons
- `MaterialButton` (filled) for "Follow"
- `MaterialButton` (outlined) for "Message"
- `Snackbar` confirmation after tapping either button
- All colors via `?attr/colorPrimary` etc. — works in dark mode automatically

[← Previous](11-retrofit-networking.md){ .md-button } [Next: Jetpack Compose →](13-jetpack-compose.md){ .md-button }
