# Inheritance

Kotlin's inheritance is closer to Java's than you'd think — with one important twist: **classes are final by default**.

## final by default

In Java, every class can be extended unless you mark it `final`. In Kotlin, the opposite — you must explicitly mark a class as `open` to allow extension:

```kotlin
class Animal(val name: String)   // final — cannot be extended

class Dog(name: String) : Animal(name)   // COMPILE ERROR
```

To enable inheritance:

```kotlin
open class Animal(val name: String) {
    open fun makeSound() {
        println("Some sound")
    }
}

class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("Woof")
    }
}
```

Methods must also be `open` for subclasses to override them. The compiler will yell at you if you forget.

Why? **Inheritance is often misused.** Forcing the author to opt in prevents accidental fragile hierarchies.

## Single colon for both extends and implements

Kotlin uses `:` for inheriting from a class AND implementing interfaces — separated by commas:

```kotlin
class Manager(name: String) : Person(name), Comparable<Manager>, Printable
```

(Person is the parent class, Comparable and Printable are interfaces.)

## Constructor delegation

You must call the parent constructor:

```kotlin
open class Vehicle(val brand: String)

class Car(brand: String, val model: String) : Vehicle(brand)
```

If the parent has a secondary constructor:

```kotlin
open class Vehicle {
    constructor(brand: String) { /* ... */ }
}

class Car : Vehicle {
    constructor(brand: String, model: String) : super(brand)
}
```

## override and super

```kotlin
open class Animal(val name: String) {
    open fun greet() = "Hello, I'm $name"
}

class Dog(name: String) : Animal(name) {
    override fun greet(): String {
        val parentGreeting = super.greet()
        return "$parentGreeting and I'm a dog"
    }
}

println(Dog("Rex").greet())
// "Hello, I'm Rex and I'm a dog"
```

`super.x()` calls the parent's implementation. Used when you want to add behavior to, not replace, the inherited method.

## Properties can be overridden too

```kotlin
open class Animal {
    open val sound: String = "Some sound"
}

class Cat : Animal() {
    override val sound: String = "Meow"
}

class Dog : Animal() {
    override val sound: String
        get() = "Woof"
}
```

## Preventing further extension

Even on an `open` class, you can lock down a specific method:

```kotlin
open class Animal {
    open fun greet() = "Hi"
    final fun species() = "Animal"   // can't override
}

class Dog : Animal() {
    override fun greet() = "Woof"      // OK
    override fun species() = "Dog"     // COMPILE ERROR — final
}
```

Useful when you want to allow some customization but lock the rest.

## abstract classes

Same as Java — can't be instantiated, may have abstract members:

```kotlin
abstract class Shape {
    abstract val area: Double
    abstract fun describe(): String

    fun report() = "${describe()} has area $area"
}

class Circle(val radius: Double) : Shape() {
    override val area: Double get() = Math.PI * radius * radius
    override fun describe() = "Circle of radius $radius"
}

println(Circle(5.0).report())
// "Circle of radius 5.0 has area 78.539..."
```

`abstract` implies `open` — you don't need both.

## When inheritance is the wrong tool

If you find yourself with deep hierarchies (3+ levels) or "I want most of this class but slightly different," **prefer composition**:

```kotlin
// Inheritance — fragile:
open class Animal { fun eat() { ... } }
open class Mammal : Animal() { fun walk() { ... } }
open class Dog : Mammal() { fun bark() { ... } }

// Composition — flexible:
class Dog(private val eater: Eater, private val walker: Walker) {
    fun eat() = eater.eat()
    fun walk() = walker.walk()
    fun bark() { ... }
}
```

We'll cover composition more in Module 3 — every Android `Activity` is a composition of `ViewModel`, `Repository`, `LifecycleOwner`, etc.

## Try it yourself

Build:

- `open class Shape` with `open val area: Double` (default 0)
- `Circle(radius: Double) : Shape()` overriding area
- `Square(side: Double) : Shape()` overriding area
- `Rectangle(width: Double, height: Double) : Shape()` overriding area

Then put them in a list and print each one's area.

??? success "Solution"
    ```kotlin
    open class Shape {
        open val area: Double = 0.0
    }

    class Circle(val radius: Double) : Shape() {
        override val area: Double get() = Math.PI * radius * radius
    }

    class Square(val side: Double) : Shape() {
        override val area: Double get() = side * side
    }

    class Rectangle(val w: Double, val h: Double) : Shape() {
        override val area: Double get() = w * h
    }

    fun main() {
        val shapes = listOf(Circle(3.0), Square(4.0), Rectangle(2.0, 5.0))
        for (s in shapes) {
            println("${s::class.simpleName} area: ${"%.2f".format(s.area)}")
        }
    }
    ```

[← Previous](07-setters-getters.md){ .md-button } [Next: Interfaces →](09-interfaces.md){ .md-button }
