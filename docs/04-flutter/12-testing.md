# Testing

Three layers, fastest to slowest:

| Type | Tests | Speed |
|---|---|---|
| **Unit** | Pure Dart logic, no Flutter | ms |
| **Widget** | A widget in isolation | tens of ms |
| **Integration** | Full app on emulator/device | seconds |

Aim for many unit tests, fewer widget tests, very few integration tests.

## Unit tests

Live in `test/` directory.

```dart title="test/calc_test.dart"
import 'package:flutter_test/flutter_test.dart';
import 'package:my_app/calc.dart';

void main() {
  group('Calculator', () {
    test('adds correctly', () {
      expect(add(2, 3), 5);
    });

    test('handles negatives', () {
      expect(add(-1, -2), -3);
    });

    test('throws on null', () {
      expect(() => add(null, 1), throwsA(isA<TypeError>()));
    });
  });
}
```

Run: `flutter test`. Or in your IDE — green ▶ icons next to each test.

## Widget tests

Test a single widget in isolation:

```dart
import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  testWidgets('Counter increments', (tester) async {
    await tester.pumpWidget(const MaterialApp(home: Counter()));

    expect(find.text('0'), findsOneWidget);

    await tester.tap(find.byIcon(Icons.add));
    await tester.pump();          // one frame
    // for async: await tester.pumpAndSettle();

    expect(find.text('1'), findsOneWidget);
  });
}
```

Key APIs:

- `pumpWidget(...)` — mount a widget tree
- `pump()` — advance one frame
- `pumpAndSettle()` — pump until no more frames are scheduled (use for animations/async)
- `find.text('...')`, `find.byType(Button)`, `find.byKey(Key('save'))` — locate widgets
- `tap`, `enterText`, `drag`, `scrollUntilVisible` — interactions
- `expect(finder, findsOneWidget)` — assertions

## Testing a Bloc

```bash
flutter pub add --dev bloc_test
```

```dart
blocTest<CounterCubit, int>(
  'emits [1, 2, 3] when incremented three times',
  build: () => CounterCubit(),
  act: (cubit) {
    cubit.increment();
    cubit.increment();
    cubit.increment();
  },
  expect: () => [1, 2, 3],
);
```

`blocTest` is by far the cleanest way to test stateful logic.

## Mocking dependencies

For a Bloc that depends on a repository:

```bash
flutter pub add --dev mocktail
```

```dart
class MockRepo extends Mock implements TodoRepository {}

blocTest<TodosCubit, TodosState>(
  'emits Loaded when load succeeds',
  setUp: () {
    when(() => repo.fetchAll()).thenAnswer((_) async => [
      Todo(id: 1, title: 'A', done: false),
    ]);
  },
  build: () => TodosCubit(repo),
  act: (cubit) => cubit.load(),
  expect: () => [
    TodosLoading(),
    isA<TodosLoaded>(),
  ],
);
```

## Golden tests (visual regression)

```dart
testWidgets('Profile card looks right', (tester) async {
  await tester.pumpWidget(MaterialApp(home: ProfileCard(user: testUser)));
  await expectLater(
    find.byType(ProfileCard),
    matchesGoldenFile('profile_card.png'),
  );
});
```

First run: generates the PNG. Subsequent runs: compares — fails if a single pixel changed. Useful for catching accidental UI changes during refactors. Run with `flutter test --update-goldens` after intentional design changes.

## Integration tests

Run the actual app on a device/emulator and drive it from outside.

```dart title="integration_test/app_test.dart"
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import 'package:my_app/main.dart' as app;

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  testWidgets('Add and complete a todo', (tester) async {
    app.main();
    await tester.pumpAndSettle();

    await tester.enterText(find.byKey(const Key('newTodoField')), 'Buy bread');
    await tester.tap(find.byIcon(Icons.add));
    await tester.pumpAndSettle();

    expect(find.text('Buy bread'), findsOneWidget);

    await tester.tap(find.byType(Checkbox).first);
    await tester.pumpAndSettle();

    expect(find.text('1 done'), findsOneWidget);
  });
}
```

Run: `flutter test integration_test/`.

## Coverage

```bash
flutter test --coverage
```

Generates `coverage/lcov.info`. View HTML report:

```bash
genhtml coverage/lcov.info -o coverage/html
open coverage/html/index.html
```

(Or upload to Codecov / Coveralls via CI.)

## What to test

| Layer | Priority |
|---|---|
| Pure logic, formulas, parsers | ⭐⭐⭐⭐⭐ |
| Blocs / Cubits | ⭐⭐⭐⭐⭐ |
| Repositories (with mocked API/DB) | ⭐⭐⭐⭐ |
| Widget tests for key screens | ⭐⭐⭐ |
| Integration tests for critical flows | ⭐⭐ |
| Golden tests for design-critical screens | ⭐⭐ |

**Don't test every widget. Test what would break the user's day if it broke.**

## CI

GitHub Actions example:

```yaml title=".github/workflows/test.yml"
name: Test
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: subosito/flutter-action@v2
        with: { channel: stable }
      - run: flutter pub get
      - run: flutter test --coverage
```

## Try it yourself

For your Todo app:

1. Write unit tests for any pure logic (date formatting, sorting)
2. Write a `blocTest` for your TodoCubit covering: add, toggle, delete
3. Write one widget test: render `TodoScreen`, add an item via UI, assert it appears

[← Previous](11-firebase.md){ .md-button } [Next: Publishing →](13-publishing.md){ .md-button }
