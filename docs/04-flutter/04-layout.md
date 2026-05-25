# Layout — Row, Column, Stack

Flutter's layout system is built on three primitives that handle 95% of cases: **Row**, **Column**, and **Stack**.

## Column — vertical

```dart
Column(
  children: const [
    Text('First'),
    Text('Second'),
    Text('Third'),
  ],
)
```

Children stack top to bottom. Default size: just big enough for the children (like `wrap_content`).

## Row — horizontal

```dart
Row(
  children: const [
    Icon(Icons.star),
    SizedBox(width: 8),
    Text('5.0'),
  ],
)
```

Same as Column but horizontal.

## Stack — overlapping (z-index)

```dart
Stack(
  children: [
    Container(width: 200, height: 200, color: Colors.blue),
    const Positioned(
      bottom: 8, right: 8,
      child: Icon(Icons.favorite, color: Colors.white),
    ),
  ],
)
```

Children stack on top of each other. Use `Positioned` to anchor children to edges.

## Main axis vs cross axis

- **Main axis** — the direction of layout (vertical for Column, horizontal for Row)
- **Cross axis** — perpendicular

Alignment is set per axis:

```dart
Column(
  mainAxisAlignment: MainAxisAlignment.center,        // vertical centering
  crossAxisAlignment: CrossAxisAlignment.stretch,     // fill horizontally
  children: [...],
)

Row(
  mainAxisAlignment: MainAxisAlignment.spaceBetween,
  crossAxisAlignment: CrossAxisAlignment.center,
  children: [...],
)
```

### MainAxisAlignment options

| Value | Layout |
|---|---|
| `start` | Children packed at start |
| `end` | Packed at end |
| `center` | Centered |
| `spaceBetween` | Even gaps between (no leading/trailing space) |
| `spaceAround` | Gaps before/after each child, half-gaps at ends |
| `spaceEvenly` | Equal spacing including at ends |

## Expanded — fill remaining space

```dart
Row(
  children: const [
    Icon(Icons.menu),
    Expanded(
      child: Text('This expands to take remaining space'),
    ),
    Icon(Icons.search),
  ],
)
```

`Expanded` with no `flex` takes all remaining space. With multiple `Expanded` children, use `flex` to set proportions:

```dart
Row(
  children: const [
    Expanded(flex: 2, child: ColoredBox(color: Colors.red)),
    Expanded(flex: 1, child: ColoredBox(color: Colors.blue)),
  ],
)
// red takes 2/3, blue takes 1/3
```

## Flexible — take some space if available

```dart
Row(
  children: const [
    Flexible(child: Text('Some text that might wrap')),
    Icon(Icons.close),
  ],
)
```

`Flexible` lets the child be smaller than its content. `Expanded` is `Flexible(fit: tight)`.

## ListView — scrollable column

```dart
ListView(
  children: const [
    ListTile(leading: Icon(Icons.home), title: Text('Home')),
    ListTile(leading: Icon(Icons.settings), title: Text('Settings')),
    ListTile(leading: Icon(Icons.info), title: Text('About')),
  ],
)
```

For long or infinite lists, use the **builder** variant — only constructs visible widgets:

```dart
ListView.builder(
  itemCount: items.length,
  itemBuilder: (context, index) {
    return ListTile(title: Text(items[index]));
  },
)
```

This is the Flutter equivalent of `RecyclerView`. Memory-efficient for any list size.

## GridView

```dart
GridView.count(
  crossAxisCount: 2,
  padding: const EdgeInsets.all(8),
  children: List.generate(20, (i) =>
    Container(margin: const EdgeInsets.all(4), color: Colors.amber, child: Center(child: Text('$i'))),
  ),
)
```

Or `GridView.builder` for large/infinite grids.

## SafeArea

Wraps content so it doesn't overlap the notch, status bar, or home indicator:

```dart
Scaffold(
  body: SafeArea(
    child: Column(children: [...]),
  ),
)
```

Always wrap your top-level body in `SafeArea` unless your design specifically extends edge-to-edge.

## Scaffold — page skeleton

```dart
Scaffold(
  appBar: AppBar(title: const Text('My Page')),
  body: const Center(child: Text('Body')),
  floatingActionButton: FloatingActionButton(
    onPressed: () {},
    child: const Icon(Icons.add),
  ),
  bottomNavigationBar: BottomNavigationBar(items: [...]),
  drawer: const Drawer(child: Text('Menu')),
)
```

Scaffold is the standard "page" container — gives you AppBar, body, FAB, bottom bar, drawer slots.

## Responsive layout

Use `MediaQuery` to check screen size:

```dart
@override
Widget build(BuildContext context) {
  final width = MediaQuery.of(context).size.width;
  return width > 600
    ? const WideLayout()    // tablet
    : const NarrowLayout(); // phone
}
```

Or `LayoutBuilder` for parent-bounded sizing:

```dart
LayoutBuilder(
  builder: (context, constraints) {
    if (constraints.maxWidth > 600) return const WideContent();
    return const NarrowContent();
  },
)
```

## Common debug overlay

When layout looks wrong, enable visual debugging:

```dart
import 'package:flutter/rendering.dart';

void main() {
  debugPaintSizeEnabled = true;   // shows widget borders
  runApp(const MyApp());
}
```

Or in your running app: press `p` in the Flutter run terminal to toggle "show debug paint."

## Try it yourself

Build a "settings list" screen:

- An `AppBar` titled "Settings"
- A scrollable column with 8 settings rows (each: leading icon, title, trailing arrow)
- Tapping a row shows a `SnackBar` with the row's name

??? success "Solution"
    ```dart
    class SettingsScreen extends StatelessWidget {
      const SettingsScreen({super.key});

      @override
      Widget build(BuildContext context) {
        const items = [
          ('Account', Icons.person),
          ('Notifications', Icons.notifications),
          ('Privacy', Icons.lock),
          ('Display', Icons.brightness_6),
          ('Storage', Icons.storage),
          ('Sounds', Icons.volume_up),
          ('Help', Icons.help),
          ('About', Icons.info),
        ];

        return Scaffold(
          appBar: AppBar(title: const Text('Settings')),
          body: ListView(
            children: [
              for (final (label, icon) in items)
                ListTile(
                  leading: Icon(icon),
                  title: Text(label),
                  trailing: const Icon(Icons.chevron_right),
                  onTap: () => ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('$label tapped')),
                  ),
                ),
            ],
          ),
        );
      }
    }
    ```

[← Previous](03-widgets-intro.md){ .md-button } [Next: Stateful Widgets →](05-stateful-widgets.md){ .md-button }
