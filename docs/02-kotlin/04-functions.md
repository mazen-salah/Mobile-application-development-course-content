# Functions

Functions in Kotlin are first-class — concise to declare, flexible to use.

## Declaration

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

- `fun` keyword
- parameter list with **type annotations required**
- `: Int` is the return type
- `return` to return a value

Call it:

```kotlin
val result = sum(3, 5)
```

## Expression-body functions (single line)

When the body is a single expression, drop the braces and `return`:

```kotlin
fun sum(a: Int, b: Int): Int = a + b

fun square(n: Int) = n * n   // return type inferred as Int
fun greet(name: String) = "Hello, $name"
```

This is the most common style in Kotlin.

## Unit — the void

A function that returns nothing has return type `Unit` (analogous to Java's `void`):

```kotlin
fun greet(name: String): Unit {
    println("Hello, $name")
}

// Unit is optional — both are equivalent:
fun greet2(name: String) {
    println("Hello, $name")
}
```

## Default parameters (huge win over Java)

```kotlin
fun greet(name: String, greeting: String = "Hello", excited: Boolean = false): String {
    val punct = if (excited) "!" else "."
    return "$greeting, $name$punct"
}

greet("Mazen")                                  // "Hello, Mazen."
greet("Mazen", "Hi")                            // "Hi, Mazen."
greet("Mazen", excited = true)                  // "Hello, Mazen!"
greet("Mazen", greeting = "Hey", excited = true) // "Hey, Mazen!"
```

In Java you'd need 8 overloads to cover all combinations. In Kotlin: one function.

## Named arguments

For readability with many parameters:

```kotlin
fun createUser(
    name: String,
    age: Int,
    isAdmin: Boolean = false,
    isVerified: Boolean = false
) { ... }

createUser(name = "Mazen", age = 25, isVerified = true)
```

Compare to a Java call like `createUser("Mazen", 25, false, true)` — way more readable.

## Vararg

Accept any number of arguments:

```kotlin
fun sum(vararg numbers: Int): Int {
    var total = 0
    for (n in numbers) total += n
    return total
}

sum(1, 2, 3)            // 6
sum(1, 2, 3, 4, 5)      // 15
sum()                   // 0

val list = intArrayOf(10, 20, 30)
sum(*list)              // 60 — spread operator
```

## Top-level functions

Unlike Java, Kotlin lets you declare functions outside any class:

```kotlin title="MathUtils.kt"
fun square(n: Int) = n * n
fun cube(n: Int) = n * n * n
```

Call from anywhere: `square(5)`. No utility class needed.

## Single-expression function with type inference

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
```

The return type is inferred from the expression. Use this style when the type is obvious.

## Functions as values

Functions are first-class — you can store them in variables and pass them around:

```kotlin
val add: (Int, Int) -> Int = { a, b -> a + b }
println(add(3, 5))   // 8

fun apply(a: Int, b: Int, op: (Int, Int) -> Int): Int = op(a, b)

apply(3, 5, add)                      // 8
apply(3, 5) { x, y -> x * y }         // 15 — lambda syntax
apply(3, 5, ::sum)                     // function reference
```

The last argument can be a lambda outside the parens — covered more in lesson 12.

## Try it yourself

Convert this Java to idiomatic Kotlin:

```java
public class Calc {
    public static int add(int a, int b) { return a + b; }
    public static int subtract(int a, int b) { return a - b; }
    public static int multiply(int a, int b) { return a * b; }
    public static int divide(int a, int b) {
        if (b == 0) return 0;
        return a / b;
    }
}
```

??? success "Solution"
    ```kotlin
    fun add(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b
    fun divide(a: Int, b: Int) = if (b == 0) 0 else a / b
    ```

[← Previous](03-null-safety.md){ .md-button } [Next: Control Flow →](05-control-flow.md){ .md-button }
