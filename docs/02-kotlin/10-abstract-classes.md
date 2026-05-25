# Abstract Classes

Abstract classes sit between concrete classes and interfaces. They can hold state, have constructors, and declare abstract methods that subclasses must implement.

## Basics

```kotlin
abstract class Shape(val color: String) {
    abstract val area: Double                // must implement
    abstract fun describe(): String          // must implement

    fun colorReport() = "$color shape"       // concrete — inherited
}
```

Cannot instantiate directly:

```kotlin
val s = Shape("red")  // COMPILE ERROR
```

Must subclass:

```kotlin
class Circle(color: String, val radius: Double) : Shape(color) {
    override val area: Double get() = Math.PI * radius * radius
    override fun describe() = "Circle of radius $radius"
}

class Rectangle(color: String, val w: Double, val h: Double) : Shape(color) {
    override val area: Double get() = w * h
    override fun describe() = "Rectangle ${w}×${h}"
}

val shapes: List<Shape> = listOf(
    Circle("red", 5.0),
    Rectangle("blue", 3.0, 4.0)
)
shapes.forEach { println("${it.describe()} - ${it.colorReport()}") }
```

## abstract vs open

| | open class | abstract class |
|---|---|---|
| Can instantiate | Yes | No |
| Can have abstract members | No | Yes |
| Used for | Allow extension | Define a partial template |

If your "base class" makes no sense to instantiate (`Shape`, `PaymentMethod`, `Animal`), make it `abstract`.

## abstract vs interface

| | abstract class | interface |
|---|---|---|
| Has state (fields with backing) | Yes | No (only abstract or computed properties) |
| Has constructor | Yes | No |
| Multiple inheritance | No (one parent) | Yes (many interfaces) |
| Used for | Shared state + behavior | Pure capability |

Rule of thumb: if classes share **state**, use abstract class. If they share only **behavior**, use interface.

## Template method pattern

Abstract classes shine when defining a fixed algorithm with replaceable steps:

```kotlin
abstract class DataExporter {
    fun export(data: List<Map<String, Any>>): String {
        val header = makeHeader()
        val rows = data.joinToString("\n") { row -> formatRow(row) }
        val footer = makeFooter()
        return "$header\n$rows\n$footer"
    }

    protected abstract fun makeHeader(): String
    protected abstract fun formatRow(row: Map<String, Any>): String
    protected open fun makeFooter() = ""
}

class CsvExporter : DataExporter() {
    override fun makeHeader() = "id,name,email"
    override fun formatRow(row: Map<String, Any>) =
        "${row["id"]},${row["name"]},${row["email"]}"
}

class JsonExporter : DataExporter() {
    override fun makeHeader() = "["
    override fun formatRow(row: Map<String, Any>) =
        """  {"id":${row["id"]},"name":"${row["name"]}"},"""
    override fun makeFooter() = "]"
}
```

The `export()` method defines the algorithm; subclasses fill in the variations.

## Try it yourself

Build a payment system:

- `abstract class Payment` with `val amount: Double`, abstract `processChannel(): String`, and a `process()` method that prints `"Processed $amount via {channel}"`
- `CreditCardPayment(amount: Double)` → channel is "Credit Card"
- `ApplePayPayment(amount: Double)` → channel is "Apple Pay"
- `BankTransferPayment(amount: Double)` → channel is "Bank Transfer"

Process a list of 3 different payments.

??? success "Solution"
    ```kotlin
    abstract class Payment(val amount: Double) {
        abstract fun processChannel(): String

        fun process() {
            println("Processed \$$amount via ${processChannel()}")
        }
    }

    class CreditCardPayment(amount: Double) : Payment(amount) {
        override fun processChannel() = "Credit Card"
    }

    class ApplePayPayment(amount: Double) : Payment(amount) {
        override fun processChannel() = "Apple Pay"
    }

    class BankTransferPayment(amount: Double) : Payment(amount) {
        override fun processChannel() = "Bank Transfer"
    }

    fun main() {
        val payments = listOf(
            CreditCardPayment(99.99),
            ApplePayPayment(15.50),
            BankTransferPayment(500.00)
        )
        payments.forEach { it.process() }
    }
    ```

[← Previous](09-interfaces.md){ .md-button } [Next: Data Classes →](11-data-classes.md){ .md-button }
