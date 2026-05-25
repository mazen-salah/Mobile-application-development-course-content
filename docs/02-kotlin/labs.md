# Module 2 — Labs

Two labs covering Kotlin's idiomatic features.

---

## Lab 1 — Kotlin Refactor

Take your Java cart solution from Module 1 (Lab 4) and rewrite it in idiomatic Kotlin. Use:

- `data class` for Product
- Immutable `List<Product>` instead of arrays
- `val`/`var` correctly
- Computed properties for derived values (e.g. cart total)
- Lambdas for filtering and mapping
- Null safety throughout (no `!!`)

### Deliverable

A `cart.kt` file that runs and prints a receipt, with the same behavior as the Java version but in roughly **half the code**.

??? success "Architecture hint"
    ```kotlin
    data class Product(val id: String, val name: String, val price: Double)

    data class CartItem(val product: Product, val quantity: Int) {
        val subtotal: Double get() = product.price * quantity
    }

    class Cart {
        private val _items = mutableListOf<CartItem>()
        val items: List<CartItem> get() = _items.toList()

        fun add(product: Product, qty: Int = 1) {
            val existing = _items.find { it.product.id == product.id }
            if (existing != null) {
                _items[_items.indexOf(existing)] =
                    existing.copy(quantity = existing.quantity + qty)
            } else {
                _items.add(CartItem(product, qty))
            }
        }

        val total: Double get() = items.sumOf { it.subtotal }

        fun printReceipt() { /* ... */ }
    }
    ```

---

## Lab 2 — Tasks: a domain model

Model a small task-tracking app's domain using only data classes, sealed classes, and pure functions. **No mutable state**, no I/O.

### Required types

```kotlin
data class Task(
    val id: Int,
    val title: String,
    val priority: Priority,
    val status: Status,
    val tags: List<String> = emptyList()
)

enum class Priority { LOW, MEDIUM, HIGH, CRITICAL }

sealed interface Status {
    object Pending : Status
    object InProgress : Status
    data class Completed(val completedAt: String) : Status
    data class Blocked(val reason: String) : Status
}
```

### Required functions

Build these as **pure functions** taking and returning lists (no mutation):

1. `fun activeTasks(tasks: List<Task>): List<Task>` — tasks that are NOT Completed
2. `fun highPriority(tasks: List<Task>): List<Task>` — HIGH or CRITICAL only
3. `fun tasksByTag(tasks: List<Task>, tag: String): List<Task>`
4. `fun groupByStatus(tasks: List<Task>): Map<String, List<Task>>` — keyed by status name
5. `fun blockedReasons(tasks: List<Task>): List<String>` — every "reason" from Blocked tasks
6. `fun summary(tasks: List<Task>): String` — produces something like:
   ```
   12 total tasks: 5 pending, 3 in progress, 2 blocked, 2 completed.
   Top priority: CRITICAL (1)
   ```

### Bonus

- Add `markCompleted(task: Task, at: String): Task` using `.copy()`
- Use `when` with smart-cast on `Status` (compiler enforces exhaustiveness because it's `sealed`)
- Write tests for each function

Submit as a public GitHub repo and link in a [GitHub issue](https://github.com/mazen-salah/Mobile-application-development-course-content/issues/new) with title `[Module 2 Lab] <Your Name>`.

[← Previous: Lambdas & Collections](12-lambdas-collections.md){ .md-button } [Next: Module 3 — Android Native →](../03-android-native/index.md){ .md-button .md-button--primary }
