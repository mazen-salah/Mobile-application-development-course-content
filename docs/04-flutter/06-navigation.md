# Navigation

Flutter navigation has two flavors: the imperative **Navigator 1.0** (push/pop) and the declarative **Navigator 2.0** (routes as state). For 90% of apps the imperative API plus **`go_router`** is the right choice.

## Push / Pop — the basics

```dart
// Navigate to a new screen
Navigator.of(context).push(
  MaterialPageRoute(builder: (_) => const DetailScreen()),
);

// Come back
Navigator.of(context).pop();
```

The stack of screens is called the **navigation stack**. Push adds to top; pop removes top.

## Passing data forward

```dart
class DetailScreen extends StatelessWidget {
  final int userId;
  const DetailScreen({required this.userId, super.key});

  @override
  Widget build(BuildContext context) => Text('User $userId');
}

// caller:
Navigator.of(context).push(
  MaterialPageRoute(builder: (_) => DetailScreen(userId: 42)),
);
```

## Getting data back

```dart
final selected = await Navigator.of(context).push<String>(
  MaterialPageRoute(builder: (_) => const PickerScreen()),
);

if (selected != null) {
  print('User picked $selected');
}

// In PickerScreen:
Navigator.of(context).pop('apple');
```

`push<T>` returns a `Future<T?>` that resolves when the screen pops.

## Named routes (for small apps)

```dart
MaterialApp(
  initialRoute: '/',
  routes: {
    '/': (_) => const HomeScreen(),
    '/detail': (_) => const DetailScreen(),
    '/settings': (_) => const SettingsScreen(),
  },
)

// usage:
Navigator.of(context).pushNamed('/detail');
```

Limitation: passing arguments is clunky and not type-safe. For anything beyond a tiny app, use `go_router`.

## go_router — the modern recommendation

```bash
flutter pub add go_router
```

```dart
final _router = GoRouter(
  initialLocation: '/',
  routes: [
    GoRoute(path: '/', builder: (_, __) => const HomeScreen()),
    GoRoute(
      path: '/user/:id',
      builder: (_, state) {
        final id = int.parse(state.pathParameters['id']!);
        return DetailScreen(userId: id);
      },
    ),
    GoRoute(path: '/settings', builder: (_, __) => const SettingsScreen()),
  ],
);

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(routerConfig: _router);
  }
}

// usage from anywhere:
context.go('/user/42');         // replace
context.push('/settings');      // push
context.pop();                  // back
```

Why go_router:

- **Deep links** work out of the box
- **URL-based** (great for web)
- **Type-safe arguments** via path/query params
- **Nested navigation** for tabs + sub-pages
- Maintained by the Flutter team

## Tabs with persistent state

```dart
final _router = GoRouter(
  initialLocation: '/home',
  routes: [
    StatefulShellRoute.indexedStack(
      builder: (context, state, navigationShell) {
        return ScaffoldWithBottomNav(navigationShell: navigationShell);
      },
      branches: [
        StatefulShellBranch(routes: [
          GoRoute(path: '/home', builder: (_, __) => const HomeTab()),
        ]),
        StatefulShellBranch(routes: [
          GoRoute(path: '/search', builder: (_, __) => const SearchTab()),
        ]),
        StatefulShellBranch(routes: [
          GoRoute(path: '/profile', builder: (_, __) => const ProfileTab()),
        ]),
      ],
    ),
  ],
);
```

Each tab keeps its own navigation stack — switching tabs doesn't lose state.

## Dialogs and bottom sheets

Modal screens you don't want to push onto the stack:

```dart
// Dialog
showDialog(
  context: context,
  builder: (_) => AlertDialog(
    title: const Text('Delete?'),
    content: const Text('This cannot be undone.'),
    actions: [
      TextButton(onPressed: () => Navigator.pop(context, false), child: const Text('Cancel')),
      TextButton(onPressed: () => Navigator.pop(context, true), child: const Text('Delete')),
    ],
  ),
);

// Bottom sheet
showModalBottomSheet(
  context: context,
  isScrollControlled: true,
  builder: (_) => Padding(
    padding: EdgeInsets.only(bottom: MediaQuery.of(context).viewInsets.bottom),
    child: const NewTodoForm(),
  ),
);
```

Both return a `Future` that resolves when dismissed — same pattern as `push`.

## Diagram

```mermaid
flowchart LR
    A[/] --> B[/user/:id]
    A --> C[/settings]
    B --> D[/user/:id/edit]
    C --> D
```

## Try it yourself

Build a 3-screen flow with go_router:

1. `/` → list of products (hard-coded list of 5)
2. `/product/:id` → product detail
3. `/product/:id/buy` → confirmation screen with a "Done" button that pops back to `/`

[← Previous](05-stateful-widgets.md){ .md-button } [Next: State Management →](07-state-management.md){ .md-button }
