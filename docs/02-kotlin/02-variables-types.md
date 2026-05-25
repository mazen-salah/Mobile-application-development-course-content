# Variables & Types

## val vs var

Kotlin has two ways to declare variables:

```kotlin
val name = "Mazen"   // immutable — can't reassign
var age = 25         // mutable — can reassign

name = "Sara"        // COMPILE ERROR
age = 26             // OK
```

**Default to `val`.** Reach for `var` only when you genuinely need reassignment. Immutability prevents bugs.

## Type inference

Kotlin figures out the type:

```kotlin
val name = "Mazen"   // inferred as String
val age = 25         // inferred as Int
val pi = 3.14        // inferred as Double
```

You can be explicit when you want:

```kotlin
val name: String = "Mazen"
val age: Int = 25
val temp: Float = 23.5f
```

## Primitive types (no primitives, actually)

Kotlin has no Java-style primitives. Everything is an object. The compiler optimizes to primitives at the bytecode level — same performance, cleaner mental model.

| Kotlin | Java equivalent | Example |
|---|---|---|
| `Byte` | `byte` | `val b: Byte = 100` |
| `Short` | `short` | `val s: Short = 5000` |
| `Int` | `int` | `val i = 42` |
| `Long` | `long` | `val l = 5_000_000_000L` |
| `Float` | `float` | `val f = 3.14f` |
| `Double` | `double` | `val d = 3.14` |
| `Char` | `char` | `val c = 'A'` |
| `Boolean` | `boolean` | `val b = true` |

## String

```kotlin
val name = "Mazen"
val length = name.length          // 5 — property, not method!
val upper = name.uppercase()      // "MAZEN"
```

### String templates (massive win over Java)

```kotlin
val name = "Mazen"
val age = 25

// Java would force you to do:
// "Hello, " + name + ". You are " + age + " years old."

// Kotlin:
val msg = "Hello, $name. You are $age years old."

// Expressions need braces:
val info = "Next year you'll be ${age + 1}"
```

### Multi-line strings

```kotlin
val poem = """
    Roses are red
    Violets are blue
    Kotlin is concise
    Java needs help too
""".trimIndent()
```

## Numbers & operations

Same operators as Java: `+ - * / %`. No surprises.

```kotlin
val a = 17
val b = 5
println(a / b)       // 3 (integer division)
println(a.toDouble() / b)  // 3.4
println(a % b)       // 2
```

### No implicit conversions

Kotlin won't silently widen `Int` to `Long`:

```kotlin
val a: Int = 5
val b: Long = a        // COMPILE ERROR
val c: Long = a.toLong()  // OK
```

This forces you to be explicit — annoying once, then a non-issue forever.

## Constants

`const val` for compile-time constants (top-level or in `companion object`):

```kotlin
const val MAX_RETRIES = 3
const val APP_NAME = "My App"
```

Use these for true constants. For runtime-computed values that don't change, regular `val` is fine.

## Try it yourself

Rewrite this Java in Kotlin:

```java
public class Greeting {
    public static void main(String[] args) {
        String name = "Mazen";
        int age = 25;
        double height = 1.78;
        final double PI = 3.14159;

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + "m");
        System.out.printf("PI ≈ %.2f%n", PI);
    }
}
```

??? success "Solution"
    ```kotlin
    const val PI = 3.14159

    fun main() {
        val name = "Mazen"
        val age = 25
        val height = 1.78

        println("Name: $name")
        println("Age: $age")
        println("Height: ${height}m")
        println("PI ≈ ${"%.2f".format(PI)}")
    }
    ```

[← Previous](01-getting-started.md){ .md-button } [Next: Null Safety →](03-null-safety.md){ .md-button }
