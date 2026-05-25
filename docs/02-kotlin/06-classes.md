# Classes & Objects

Kotlin classes are much more compact than Java's.

## Minimal class

```kotlin
class Phone(val brand: String, val model: String, var price: Double)
```

That's it. One line replaces Java's class + fields + constructor + getters + setters.

```kotlin
val p = Phone("Google", "Pixel 8", 699.0)
println(p.brand)    // Google      (read via property)
p.price = 599.0     // OK — price is var
p.brand = "Apple"   // COMPILE ERROR — brand is val
```

## What the one-liner actually does

Behind the scenes, Kotlin generates:

- Private fields
- Public getters for each property
- Setters for `var` properties (not for `val`)
- A constructor taking all parameters

You get all of that for free.

## Primary constructor

The parameters on the class header are the **primary constructor**. To run code at construction time, use `init`:

```kotlin
class Phone(val brand: String, val model: String, var price: Double) {
    init {
        require(price >= 0) { "Price cannot be negative" }
        require(brand.isNotBlank()) { "Brand required" }
    }
}
```

`require` throws `IllegalArgumentException` if the condition is false.

## Property bodies

Properties can have custom getters/setters:

```kotlin
class Person(val firstName: String, val lastName: String) {
    val fullName: String
        get() = "$firstName $lastName"
}

val p = Person("Mazen", "Salah")
println(p.fullName)   // "Mazen Salah"
```

`fullName` has no backing field — it's computed every time.

## Secondary constructors

```kotlin
class Phone(val brand: String, val model: String) {
    var price: Double = 0.0

    // secondary constructor — must delegate to primary
    constructor(brand: String, model: String, price: Double) : this(brand, model) {
        this.price = price
    }
}

val p1 = Phone("Pixel", "8")
val p2 = Phone("Pixel", "8 Pro", 999.0)
```

In practice, **default parameters in the primary constructor cover most use cases**:

```kotlin
class Phone(val brand: String, val model: String, var price: Double = 0.0)

val p1 = Phone("Pixel", "8")
val p2 = Phone("Pixel", "8 Pro", 999.0)
```

## Methods

```kotlin
class Phone(val brand: String, val model: String, var price: Double) {
    fun describe() = "$brand $model — \$${"%.2f".format(price)}"

    fun applyDiscount(percent: Double) {
        require(percent in 0.0..100.0)
        price *= (1 - percent / 100)
    }
}

val p = Phone("Pixel", "8", 699.0)
println(p.describe())     // Pixel 8 — $699.00
p.applyDiscount(15.0)
println(p.describe())     // Pixel 8 — $594.15
```

## Visibility modifiers

| Modifier | Visible to |
|---|---|
| `public` (default) | Everyone |
| `internal` | Same module |
| `protected` | Subclasses |
| `private` | Same file (top-level) or same class |

```kotlin
class BankAccount(initialBalance: Double) {
    var balance: Double = initialBalance
        private set   // public read, private write

    fun deposit(amount: Double) {
        require(amount > 0)
        balance += amount
    }
}

val a = BankAccount(100.0)
println(a.balance)   // OK
a.balance = 0        // COMPILE ERROR — setter is private
a.deposit(50.0)      // OK
```

## object — singletons

Kotlin has built-in singletons:

```kotlin
object Logger {
    fun log(message: String) {
        println("[LOG] $message")
    }
}

Logger.log("Started")   // no instantiation — just use it
```

`object Logger` creates exactly one instance, accessible globally.

## companion object — static-like

Kotlin has no `static`. Use `companion object` for class-level state and factories:

```kotlin
class User(val name: String) {
    companion object {
        const val MAX_NAME_LENGTH = 50

        fun fromJson(json: String): User {
            // parse...
            return User("parsed")
        }
    }
}

val u = User.fromJson("...")
println(User.MAX_NAME_LENGTH)
```

Used like static, behaves like a nested object.

## Try it yourself

Build a `Rectangle` class with:

- Properties `width` and `height` (both `var Double`, validated `> 0`)
- Computed property `area`
- Computed property `perimeter`
- Method `isSquare()` returning Boolean
- A `companion object` with a `square(side: Double)` factory

??? success "Solution"
    ```kotlin
    class Rectangle(width: Double, height: Double) {
        var width: Double = width
            set(value) {
                require(value > 0) { "Width must be positive" }
                field = value
            }
        var height: Double = height
            set(value) {
                require(value > 0) { "Height must be positive" }
                field = value
            }

        val area: Double get() = width * height
        val perimeter: Double get() = 2 * (width + height)

        fun isSquare() = width == height

        companion object {
            fun square(side: Double) = Rectangle(side, side)
        }
    }

    fun main() {
        val r = Rectangle(3.0, 4.0)
        println("Area: ${r.area}")              // 12.0
        println("Perimeter: ${r.perimeter}")    // 14.0
        println("Square? ${r.isSquare()}")      // false

        val sq = Rectangle.square(5.0)
        println(sq.isSquare())                  // true
    }
    ```

[← Previous](05-control-flow.md){ .md-button } [Next: Setters & Getters →](07-setters-getters.md){ .md-button }
