# Dart Essentials

Dart is Flutter's language. If you know Kotlin or Java, you'll feel at home — Dart sits between them syntactically.

## main()

```dart
void main() {
  print('Hello, Dart!');
}
```

That's a runnable Dart file. `main()` is the entry point.

## Variables

```dart
var name = 'Mazen';          // type inferred as String
int age = 25;
double pi = 3.14;
String greeting = 'Hello';
bool isOpen = true;

final email = 'mazen@x.com';   // can't reassign (like Kotlin's val)
const TAX_RATE = 0.15;          // compile-time constant
```

- `var` → mutable, type inferred
- `final` → immutable after assignment, runtime value
- `const` → compile-time constant

Default to `final` over `var` when you can.

## Null safety

Like Kotlin — types are non-nullable by default:

```dart
String name = 'Mazen';
name = null;          // COMPILE ERROR

String? nickname;     // nullable — defaults to null
nickname = 'Maz';
nickname = null;      // OK

// Safe call
int? len = nickname?.length;

// Null-coalescing
String display = nickname ?? 'Stranger';

// Bang operator
int len2 = nickname!.length;   // throws if null
```

## Functions

```dart
int sum(int a, int b) {
  return a + b;
}

// Single-expression (arrow):
int sum2(int a, int b) => a + b;

// Optional named parameters:
String greet({String? name, int age = 0}) {
  return '${name ?? 'Stranger'}, age $age';
}
greet(name: 'Mazen', age: 25);
greet();   // OK — both have defaults/are nullable

// Required named parameters:
String greet2({required String name, int age = 0}) {
  return '$name, $age';
}
greet2(name: 'Mazen');
```

## Strings

```dart
String name = 'Mazen';
String greeting = 'Hello, $name';            // interpolation
String info = 'Next year: ${age + 1}';        // expression in {}

String multi = '''
  multi-line
  string
''';

name.length;
name.toUpperCase();
name.substring(0, 3);
name.contains('zen');
name.split(' ');
```

## Lists

```dart
List<int> numbers = [1, 2, 3, 4, 5];
var fruits = ['apple', 'banana', 'cherry'];

fruits.add('date');
fruits.removeAt(0);
fruits.length;
fruits[2];                       // 'date' (after removal)
fruits.contains('banana');

// Functional ops (very like Kotlin):
var doubled = numbers.map((n) => n * 2).toList();        // [2, 4, 6, 8, 10]
var evens = numbers.where((n) => n % 2 == 0).toList();   // [2, 4]
var sum = numbers.reduce((a, b) => a + b);               // 15
```

## Maps

```dart
Map<String, int> ages = {
  'Mazen': 25,
  'Sara': 30,
};

ages['Ahmed'] = 22;
ages['Sara'];                  // 30
ages.containsKey('Bob');       // false
ages.length;

for (var entry in ages.entries) {
  print('${entry.key}: ${entry.value}');
}
```

## Classes

```dart
class Person {
  final String name;
  int age;

  Person(this.name, this.age);

  // Named constructor
  Person.baby(this.name) : age = 0;

  void greet() => print('Hello, $name');

  String get summary => '$name, age $age';
  set newName(String n) => /* won't compile — name is final */;
}

var p = Person('Mazen', 25);
p.greet();
print(p.summary);
```

`this.name` in the constructor is shorthand for `this.name = name`.

## Inheritance

```dart
class Animal {
  final String name;
  Animal(this.name);

  void greet() => print('I am $name');
}

class Dog extends Animal {
  Dog(super.name);   // forward to parent

  @override
  void greet() {
    super.greet();
    print('and I bark');
  }
}
```

## Abstract classes & interfaces

Dart's classes can be used as either — there's no separate `interface` keyword:

```dart
abstract class Shape {
  double get area;
}

class Circle implements Shape {
  final double radius;
  Circle(this.radius);

  @override
  double get area => 3.14 * radius * radius;
}
```

`extends` inherits behavior; `implements` adopts the contract only (re-implement everything).

## async / await

```dart
Future<User> fetchUser(int id) async {
  await Future.delayed(Duration(seconds: 1));   // simulate latency
  return User(id, 'Mazen');
}

void main() async {
  print('Loading...');
  var user = await fetchUser(1);
  print(user);
}
```

Identical pattern to JavaScript/Kotlin.

## Streams

A `Stream<T>` is a sequence of values over time (like Kotlin's `Flow`):

```dart
Stream<int> counter() async* {
  for (var i = 0; i < 5; i++) {
    await Future.delayed(Duration(seconds: 1));
    yield i;
  }
}

void main() async {
  await for (var n in counter()) {
    print(n);
  }
}
```

You'll see Streams everywhere in Flutter (`StreamBuilder`, BLoC, Firebase queries).

## Collections in widgets

In Dart, list literals can contain `if` and `for`:

```dart
List<Widget> tabs = [
  Tab(text: 'Home'),
  if (isLoggedIn) Tab(text: 'Profile'),
  for (var name in projectNames) Tab(text: name),
];
```

Massively useful in widget trees.

## Try it yourself

Write a small Dart program:

1. `Book` class with `title`, `author`, `pages`
2. `Library` class holding `List<Book>`, methods to add, remove, find by author
3. `main` that creates a library, adds 5 books, prints all titles by a chosen author

??? success "Solution"
    ```dart
    class Book {
      final String title;
      final String author;
      final int pages;
      Book(this.title, this.author, this.pages);

      @override
      String toString() => '$title by $author ($pages p)';
    }

    class Library {
      final List<Book> books = [];

      void add(Book b) => books.add(b);
      void remove(Book b) => books.remove(b);

      List<Book> byAuthor(String author) =>
          books.where((b) => b.author == author).toList();
    }

    void main() {
      var lib = Library()
        ..add(Book('Clean Code', 'R. Martin', 464))
        ..add(Book('Atomic Habits', 'J. Clear', 320))
        ..add(Book('The Pragmatic Programmer', 'A. Hunt', 352))
        ..add(Book('Clean Architecture', 'R. Martin', 432))
        ..add(Book('Refactoring', 'M. Fowler', 448));

      var martinBooks = lib.byAuthor('R. Martin');
      martinBooks.forEach(print);
    }
    ```

[← Previous](01-setup.md){ .md-button } [Next: Widgets Intro →](03-widgets-intro.md){ .md-button }
