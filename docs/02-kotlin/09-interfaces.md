# Interfaces

An **interface** is a contract — it declares "any class that implements me must provide these methods/properties." A class can implement **many** interfaces (unlike inheritance, which is single).

## Basics

```kotlin
interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() {
        println("Button clicked")
    }
}

class Image : Clickable {
    override fun click() {
        println("Image tapped")
    }
}

val items: List<Clickable> = listOf(Button(), Image())
items.forEach { it.click() }
// Button clicked
// Image tapped
```

## Default implementations

Unlike old Java, Kotlin interfaces can have method bodies:

```kotlin
interface Clickable {
    fun click()                            // abstract — must implement

    fun showOff() {                        // default — optional override
        println("I'm clickable!")
    }
}

class Button : Clickable {
    override fun click() {
        println("Clicked")
    }
    // showOff() not overridden — uses default
}
```

This is huge for evolving APIs without breaking existing implementations.

## Properties in interfaces

Interfaces can declare properties (but not store state):

```kotlin
interface Named {
    val name: String              // abstract property
    val nameLength: Int get() = name.length  // has getter, no field
}

class Person(override val name: String) : Named
```

Implementing classes provide the field; the interface just demands the contract.

## Multiple interfaces

```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable")
}

interface Focusable {
    fun focus()
    fun showOff() = println("I'm focusable")
}

class Button : Clickable, Focusable {
    override fun click() { println("clicked") }
    override fun focus() { println("focused") }

    // Both interfaces have showOff() — must override and resolve
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
```

When two interfaces have the same default, the implementing class must override and explicitly choose (or combine).

## When interface vs abstract class?

| Question | Use |
|---|---|
| Do I need to share state? | Abstract class |
| Will subclasses use multiple unrelated capabilities? | Interfaces (composition) |
| Do I need a constructor? | Abstract class |
| Is this a "capability" not a "type"? (e.g. Comparable, Iterable) | Interface |

Rule of thumb: **default to interfaces.** Use abstract classes when you genuinely need shared state.

## Sealed interfaces & classes (preview)

`sealed` is a stronger version of `abstract`: it restricts subclassing to a known set, enabling exhaustive `when` checks. Hugely useful for representing finite state.

```kotlin
sealed interface NetworkResult<out T>
data class Success<T>(val data: T) : NetworkResult<T>
data class Error(val message: String) : NetworkResult<Nothing>
object Loading : NetworkResult<Nothing>

fun render(result: NetworkResult<String>) = when (result) {
    is Success -> println(result.data)
    is Error -> println("Error: ${result.message}")
    Loading -> println("Loading...")
    // No `else` needed — compiler knows these are all the cases
}
```

You'll see this constantly in Module 3 (Android) and Module 4 (Flutter).

## Try it yourself

Build:

- `interface Drawable { fun draw() }`
- `interface Resizable { fun resize(factor: Double) }`
- `class Circle(var radius: Double) : Drawable, Resizable` — draws "circle r=R" and resizes by multiplying radius
- `class Square(var side: Double) : Drawable, Resizable` — draws "square s=S" and resizes by multiplying side
- In `main`, put both in `List<Drawable>` and draw, then `List<Resizable>` and resize

??? success "Solution"
    ```kotlin
    interface Drawable {
        fun draw()
    }

    interface Resizable {
        fun resize(factor: Double)
    }

    class Circle(var radius: Double) : Drawable, Resizable {
        override fun draw() = println("circle r=$radius")
        override fun resize(factor: Double) { radius *= factor }
    }

    class Square(var side: Double) : Drawable, Resizable {
        override fun draw() = println("square s=$side")
        override fun resize(factor: Double) { side *= factor }
    }

    fun main() {
        val items = listOf(Circle(5.0), Square(4.0))

        (items as List<Drawable>).forEach { it.draw() }
        // circle r=5.0
        // square s=4.0

        (items as List<Resizable>).forEach { it.resize(2.0) }
        items.forEach { (it as Drawable).draw() }
        // circle r=10.0
        // square s=8.0
    }
    ```

[← Previous](08-inheritance.md){ .md-button } [Next: Abstract Classes →](10-abstract-classes.md){ .md-button }
