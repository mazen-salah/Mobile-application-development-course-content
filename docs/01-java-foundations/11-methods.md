# Methods

A **method** is a named block of code you can call from elsewhere. Methods are how you stop writing the same logic over and over.

## Anatomy

```java
public static int sum(int a, int b) {
    return a + b;
}
```

| Piece | Meaning |
|---|---|
| `public` | Access modifier — who can call this (covered in Encapsulation lesson) |
| `static` | Belongs to the class, not to an instance (covered in OOP lesson) |
| `int` | Return type — the kind of value this method produces |
| `sum` | Method name (camelCase, usually a verb) |
| `(int a, int b)` | Parameters — inputs |
| `return a + b` | The output |

For now treat `public static` as a recipe — we'll unpack it in OOP.

## Calling a method

```java
public class Calculator {
    public static int sum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        int result = sum(3, 5);
        System.out.println(result);  // 8

        System.out.println(sum(10, 20));  // 30 — call inline
    }
}
```

## void — methods that don't return

When a method does work but produces no value, use `void`:

```java
public static void greet(String name) {
    System.out.println("Hello, " + name);
}

// caller:
greet("Mazen");  // prints "Hello, Mazen"
```

## Multiple parameters

```java
public static double area(double width, double height) {
    return width * height;
}

double a = area(5.0, 3.0);  // 15.0
```

Parameter order matters — `area(5, 3)` and `area(3, 5)` give the same answer here, but `divide(10, 2)` and `divide(2, 10)` don't.

## Method overloading

You can define multiple methods with the **same name** as long as the **parameter list differs**:

```java
public static int max(int a, int b) {
    return (a > b) ? a : b;
}

public static double max(double a, double b) {
    return (a > b) ? a : b;
}

public static int max(int a, int b, int c) {
    return max(max(a, b), c);
}

// Java picks the right one based on argument types
max(3, 5);          // → int version
max(3.5, 5.5);      // → double version
max(1, 2, 3);       // → three-arg version
```

## Why methods matter

Three reasons:

1. **Don't Repeat Yourself (DRY)** — if you write the same lines twice, extract them
2. **Naming gives meaning** — `calculateTax(price)` reads better than 8 lines of math inline
3. **Easier to test** — a small function with inputs and outputs is easy to verify

## Local variables

Variables declared inside a method exist only for that method's lifetime:

```java
public static int triple(int x) {
    int result = x * 3;  // local to triple()
    return result;
}
// 'result' is gone after the method returns
```

This is called **scope**. We'll see more scope rules with OOP.

## Worked example — a mini calculator

```java title="Calculator.java"
public class Calculator {

    public static double add(double a, double b)      { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Cannot divide by zero");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println(add(10, 5));       // 15.0
        System.out.println(subtract(10, 5));  // 5.0
        System.out.println(multiply(10, 5));  // 50.0
        System.out.println(divide(10, 5));    // 2.0
        System.out.println(divide(10, 0));    // 0.0 + warning
    }
}
```

## Try it yourself

Write three methods:

1. `boolean isEven(int n)` — returns true if n is even
2. `int factorial(int n)` — computes n!
3. `String repeat(String s, int times)` — returns s concatenated `times` times

Then call each from `main` to verify.

??? success "Solution"
    ```java
    public class Practice {

        public static boolean isEven(int n) {
            return n % 2 == 0;
        }

        public static int factorial(int n) {
            int result = 1;
            for (int i = 2; i <= n; i++) result *= i;
            return result;
        }

        public static String repeat(String s, int times) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) sb.append(s);
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(isEven(4));         // true
            System.out.println(factorial(5));      // 120
            System.out.println(repeat("ha", 3));   // "hahaha"
        }
    }
    ```

[← Previous: Strings](10-strings.md){ .md-button } [Next: OOP — Classes →](12-oop-classes.md){ .md-button }
