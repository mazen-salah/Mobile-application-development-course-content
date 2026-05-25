# Control Flow

Kotlin's conditionals are **expressions** (return values), not just statements. This unlocks a lot of clean code.

## if as expression

```kotlin
val age = 20

// Statement style (Java-like):
val status: String
if (age >= 18) status = "Adult" else status = "Minor"

// Expression style (idiomatic Kotlin):
val status = if (age >= 18) "Adult" else "Minor"
```

With a block, the last expression is the value:

```kotlin
val status = if (age >= 18) {
    println("Verifying...")
    "Adult"
} else {
    "Minor"
}
```

## when — supercharged switch

`when` replaces Java's `switch` — and it's much more flexible:

```kotlin
val day = 3
val name = when (day) {
    1 -> "Monday"
    2 -> "Tuesday"
    3 -> "Wednesday"
    4 -> "Thursday"
    5 -> "Friday"
    6, 7 -> "Weekend"
    else -> "Unknown"
}
```

### Without an argument — replaces if-else chains

```kotlin
val score = 85
val grade = when {
    score >= 90 -> "A"
    score >= 80 -> "B"
    score >= 70 -> "C"
    score >= 60 -> "D"
    else -> "F"
}
```

### Type checks

```kotlin
fun describe(obj: Any): String = when (obj) {
    is Int -> "Int with value $obj"
    is String -> "String of length ${obj.length}"
    is List<*> -> "List with ${obj.size} elements"
    null -> "Null"
    else -> "Something else"
}
```

The `is` check both verifies the type and smart-casts it.

### Ranges

```kotlin
val temperature = 22
val feels = when (temperature) {
    in -100..0 -> "Freezing"
    in 1..15 -> "Cold"
    in 16..25 -> "Pleasant"
    in 26..40 -> "Hot"
    else -> "Extreme"
}
```

## for loop

Iterates over anything that exposes an `iterator()`:

```kotlin
for (i in 1..5) println(i)         // 1, 2, 3, 4, 5
for (i in 5 downTo 1) println(i)   // 5, 4, 3, 2, 1
for (i in 0 until 10 step 2) println(i)  // 0, 2, 4, 6, 8

val names = listOf("Mazen", "Sara", "Ahmed")
for (name in names) println(name)

for ((i, name) in names.withIndex()) {
    println("$i: $name")
}
```

Note: `1..5` is **inclusive** on both ends. `0 until 10` is exclusive on the right (same as `0..9`).

## while / do-while

Same as Java:

```kotlin
var i = 0
while (i < 5) {
    println(i)
    i++
}

var input: String
do {
    input = readLine() ?: ""
} while (input.isBlank())
```

## break and continue

```kotlin
for (i in 1..10) {
    if (i == 5) break
    println(i)
}
// prints 1 2 3 4

for (i in 1..10) {
    if (i % 2 == 0) continue
    println(i)
}
// prints 1 3 5 7 9
```

### Labeled break

For nested loops:

```kotlin
outer@ for (i in 1..3) {
    for (j in 1..3) {
        if (i * j > 4) break@outer
        println("$i × $j = ${i * j}")
    }
}
```

## Try as expression

`try` also returns a value:

```kotlin
val result = try {
    "42".toInt()
} catch (e: NumberFormatException) {
    -1
}
```

## Try it yourself

Write a `categorize(temp: Int): String` function using `when` that returns:

- "Below freezing" if < 0
- "Cold" if 0..10
- "Mild" if 11..20
- "Warm" if 21..30
- "Hot" if 31..40
- "Extreme" otherwise

Then loop over `intArrayOf(-5, 0, 12, 22, 35, 50)` and print each with its category.

??? success "Solution"
    ```kotlin
    fun categorize(temp: Int) = when {
        temp < 0 -> "Below freezing"
        temp in 0..10 -> "Cold"
        temp in 11..20 -> "Mild"
        temp in 21..30 -> "Warm"
        temp in 31..40 -> "Hot"
        else -> "Extreme"
    }

    fun main() {
        for (t in intArrayOf(-5, 0, 12, 22, 35, 50)) {
            println("$t°C → ${categorize(t)}")
        }
    }
    ```

[← Previous](04-functions.md){ .md-button } [Next: Classes →](06-classes.md){ .md-button }
