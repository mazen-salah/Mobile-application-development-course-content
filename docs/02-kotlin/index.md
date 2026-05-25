# Module 2 — Kotlin

Kotlin is what Android is written in today. Officially endorsed by Google as the **preferred language for Android**, it's also used for server-side, multiplatform, and even web.

If you finished Module 1, you'll find Kotlin feels like Java with all the friction removed.

## What's in this module

| # | Lesson | What you'll learn |
|---|---|---|
| 01 | [Getting Started](01-getting-started.md) | Why Kotlin, installing IntelliJ |
| 02 | [Variables & Types](02-variables-types.md) | val vs var, type inference |
| 03 | [Null Safety](03-null-safety.md) | Kotlin's killer feature |
| 04 | [Functions](04-functions.md) | Default args, named args, expression bodies |
| 05 | [Control Flow](05-control-flow.md) | when, ranges, for-in |
| 06 | [Classes & Objects](06-classes.md) | Primary constructors, init blocks |
| 07 | [Setters & Getters](07-setters-getters.md) | Custom property accessors |
| 08 | [Inheritance](08-inheritance.md) | open, override, super |
| 09 | [Interfaces](09-interfaces.md) | Multiple inheritance via interfaces |
| 10 | [Abstract Classes](10-abstract-classes.md) | When to choose abstract over interface |
| 11 | [Data Classes](11-data-classes.md) | The 1-line POJO replacement |
| 12 | [Lambdas & Collections](12-lambdas-collections.md) | map, filter, reduce |

Then practice in **[Labs](labs.md)**.

## Prerequisites

- Module 1 (Java Foundations) — or solid Java/C# background
- IntelliJ IDEA Community Edition installed
- 15-20 hours

## Java → Kotlin cheat sheet

| Java | Kotlin |
|---|---|
| `int x = 5;` | `val x = 5` |
| `String s = null;` (NPE risk) | `var s: String? = null` (safe) |
| `class Person { ... }` + 30 boilerplate lines | `data class Person(val name: String, val age: Int)` |
| `if (x == null) { ... }` | `x?.let { ... }` |
| `try { ... } catch (Exception e) { ... }` | `runCatching { ... }.onFailure { ... }` |

[Begin lesson 1 →](01-getting-started.md){ .md-button .md-button--primary }
