# Lambdas & Collections

Kotlin's killer combo: concise lambda syntax + a rich collections API. Once you're fluent here, you'll write half the code for the same result.

## Lambda basics

A lambda is an anonymous function. Syntax: `{ params -> body }`

```kotlin
val square: (Int) -> Int = { x -> x * x }
println(square(5))   // 25

val add: (Int, Int) -> Int = { a, b -> a + b }
println(add(3, 5))   // 8

val greet: (String) -> Unit = { name -> println("Hello, $name") }
greet("Mazen")
```

If a lambda takes one parameter, you can refer to it as `it`:

```kotlin
val square: (Int) -> Int = { it * it }
val isPositive: (Int) -> Boolean = { it > 0 }
```

## Lambdas as the last argument

When a function's last parameter is a lambda, you can move it outside the parens:

```kotlin
fun runTwice(action: () -> Unit) {
    action()
    action()
}

// Three ways to call:
runTwice({ println("Hi") })   // standard
runTwice() { println("Hi") }  // last lambda outside
runTwice { println("Hi") }    // empty parens dropped
```

This is why Kotlin code reads like a DSL — `list.forEach { println(it) }` instead of `list.forEach({ x -> println(x) })`.

## Collections — the essential operations

Kotlin's standard library has rich operations on collections. Here are the ones you'll use 80% of the time.

### forEach — do something with each

```kotlin
val names = listOf("Mazen", "Sara", "Ahmed")
names.forEach { println(it) }
```

### map — transform each

```kotlin
val nums = listOf(1, 2, 3, 4, 5)
val squared = nums.map { it * it }   // [1, 4, 9, 16, 25]

val users = listOf(User(1, "Mazen"), User(2, "Sara"))
val ids = users.map { it.id }        // [1, 2]
val names = users.map { it.name }    // ["Mazen", "Sara"]
```

### filter — keep matching

```kotlin
val nums = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val even = nums.filter { it % 2 == 0 }   // [2, 4, 6, 8, 10]
val large = nums.filter { it > 5 }       // [6, 7, 8, 9, 10]
```

### filter + map chains

```kotlin
val users = listOf(
    User(1, "Mazen", active = true),
    User(2, "Sara", active = false),
    User(3, "Ahmed", active = true)
)
val activeNames = users
    .filter { it.active }
    .map { it.name }
// ["Mazen", "Ahmed"]
```

### find / firstOrNull — get the first match

```kotlin
val u = users.find { it.id == 2 }       // User(2, Sara, false) or null
val first = users.firstOrNull { it.active }
```

### any / all / none

```kotlin
nums.any { it > 5 }     // true
nums.all { it > 0 }     // true
nums.none { it < 0 }    // true
```

### count / sum / average / max / min

```kotlin
nums.count { it > 5 }              // 5
nums.sum()                         // 55
nums.average()                     // 5.5
nums.maxOrNull()                   // 10
nums.minOrNull()                   // 1
nums.sumOf { it * it }             // 385
```

### groupBy — group by key

```kotlin
val byActive = users.groupBy { it.active }
// {true=[User(1, "Mazen", true), User(3, "Ahmed", true)],
//  false=[User(2, "Sara", false)]}
```

### sortedBy

```kotlin
val byName = users.sortedBy { it.name }
val byNameDesc = users.sortedByDescending { it.name }
val byId = users.sortedBy { it.id }
```

### distinct

```kotlin
listOf(1, 2, 2, 3, 3, 3).distinct()    // [1, 2, 3]
users.distinctBy { it.name }
```

### flatMap — flatten nested lists

```kotlin
val matrix = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
matrix.flatten()                       // [1, 2, 3, 4, 5, 6]

val orders = listOf(
    Order(items = listOf("apple", "bread")),
    Order(items = listOf("milk"))
)
val allItems = orders.flatMap { it.items }    // ["apple", "bread", "milk"]
```

### reduce / fold — combine into a single value

```kotlin
nums.reduce { acc, n -> acc + n }                // 55 (sum)
nums.fold(0) { acc, n -> acc + n }               // 55 (sum, with initial)
nums.fold(1) { acc, n -> acc * n }               // 3628800 (product)
```

## Map (the dictionary type)

```kotlin
val ages = mapOf("Mazen" to 25, "Sara" to 30, "Ahmed" to 22)

ages["Sara"]                          // 30
ages.getOrDefault("Bob", 0)           // 0
ages.keys                             // [Mazen, Sara, Ahmed]
ages.values                           // [25, 30, 22]

ages.filterValues { it > 25 }         // {Sara=30}
ages.mapValues { (_, v) -> v + 1 }    // ages incremented
```

## Mutable vs immutable

`listOf`, `mapOf`, `setOf` create **immutable** collections. For mutation, use `mutableListOf`, `mutableMapOf`, `mutableSetOf`:

```kotlin
val list = mutableListOf(1, 2, 3)
list.add(4)
list.removeAt(0)
println(list)   // [2, 3, 4]
```

**Default to immutable.** Mutability is a source of bugs.

## Worked example — analyze sales data

```kotlin
data class Sale(val product: String, val amount: Double, val region: String)

fun main() {
    val sales = listOf(
        Sale("Headphones", 199.99, "MENA"),
        Sale("Charger", 19.99, "EU"),
        Sale("Phone", 999.99, "MENA"),
        Sale("Headphones", 199.99, "EU"),
        Sale("Phone", 999.99, "US"),
        Sale("Case", 29.99, "MENA")
    )

    val total = sales.sumOf { it.amount }
    val byRegion = sales.groupBy { it.region }
        .mapValues { (_, list) -> list.sumOf { it.amount } }
    val topProduct = sales.groupBy { it.product }
        .mapValues { (_, list) -> list.sumOf { it.amount } }
        .maxBy { it.value }

    println("Total: \$$total")
    println("By region: $byRegion")
    println("Top product: ${topProduct.key} (\$${topProduct.value})")
}
```

## Try it yourself

Given a list of `Student(name, grade, subject)`:

1. Average grade across all students
2. Average grade per subject
3. Name of the top student in "Math"
4. List of students who failed (grade < 50), sorted by grade ascending

??? success "Solution"
    ```kotlin
    data class Student(val name: String, val grade: Int, val subject: String)

    fun main() {
        val students = listOf(
            Student("Mazen", 92, "Math"),
            Student("Sara", 45, "Math"),
            Student("Ahmed", 78, "CS"),
            Student("Lina", 88, "CS"),
            Student("Omar", 35, "Math"),
            Student("Yara", 60, "Physics"),
        )

        println("Overall avg: ${students.map { it.grade }.average()}")

        val avgBySubject = students.groupBy { it.subject }
            .mapValues { (_, list) -> list.map { it.grade }.average() }
        println("Avg by subject: $avgBySubject")

        val topMath = students.filter { it.subject == "Math" }.maxBy { it.grade }
        println("Top in Math: ${topMath.name}")

        val failed = students.filter { it.grade < 50 }.sortedBy { it.grade }
        println("Failed: $failed")
    }
    ```

[← Previous](11-data-classes.md){ .md-button } [Next: Labs →](labs.md){ .md-button }
