# Getting Started with Kotlin

Kotlin is a modern, statically-typed language that runs on the JVM. JetBrains created it, Google adopted it for Android, and today it powers a huge portion of new mobile, server, and multiplatform apps.

## Why Kotlin

| Java | Kotlin |
|---|---|
| Verbose | Concise (often 30–60% less code) |
| NullPointerException is everywhere | Null safety in the type system |
| Lots of boilerplate | Data classes, smart casts, properties |
| Lambdas grafted on in Java 8 | First-class lambdas everywhere |

Kotlin is **100% interoperable with Java** — you can mix both in one project. Every Android library is callable from either.

## Install IntelliJ IDEA

1. Download [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) (free)
2. Install and launch
3. **New Project** → Kotlin → JVM (Gradle, IntelliJ build, your pick)
4. Wait for indexing to finish

For Android development you'll use Android Studio (covered in Module 3), which is essentially IntelliJ + Android tooling.

## Your first Kotlin file

```kotlin title="Main.kt"
fun main() {
    println("Hello, Kotlin!")
}
```

Compare to Java:

```java title="Main.java"
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

- No class wrapper needed for top-level functions
- `fun` instead of `public static void`
- No semicolons (optional, almost no one uses them)
- `println` is a top-level function

## Run it

In IntelliJ, click the ▶ in the gutter next to `fun main()`. You should see:

```
Hello, Kotlin!
```

From the command line:

```bash
kotlinc Main.kt -include-runtime -d main.jar
java -jar main.jar
```

In practice you'll always use the IDE.

## Kotlin REPL

For experimenting with small expressions: **Tools → Kotlin → REPL** in IntelliJ.

```
>>> val x = 5
>>> val y = 10
>>> x + y
res2: kotlin.Int = 15
```

Great for testing language features without spinning up a full project.

## What's coming

This module covers Kotlin language features. We're not building UIs yet — that's Module 3 (Android Native, with Kotlin) and Module 4 (Flutter, with Dart).

[← Module overview](index.md){ .md-button } [Next: Variables & Types →](02-variables-types.md){ .md-button .md-button--primary }
