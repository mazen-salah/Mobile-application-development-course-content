# Interfaces in Kotlin

In Kotlin, an interface is a blueprint of a class that defines a set of methods and properties that the implementing class must provide. It allows for the implementation of multiple interfaces, enabling a class to inherit behavior from multiple sources.

Here's an example of using interfaces in Kotlin:

```kotlin
interface Shape {
    var radius: Double
    fun area()
}

class Circle : Shape {
    override var radius: Double = 0.0

    override fun area() {
        println(3.14 * radius * radius)
    }
}

fun main() {
    var circle = Circle()
    circle.radius = 2.0
    circle.area()
}
```

In this example, we have an interface called `Shape` that declares a property `radius` and a method `area()`. The `Circle` class implements the `Shape` interface and provides the required implementation for the `radius` property and the `area()` method.

In the `main()` function, we create an instance of the `Circle` class, set the `radius` to 2.0, and call the `area()` method, which calculates and prints the area of the circle.

By using interfaces, we can define common behavior that can be shared across multiple classes, promoting code reusability and maintaining a consistent structure.
