# Abstraction in Kotlin

Abstraction is a fundamental concept in object-oriented programming that allows you to define abstract classes and methods. In Kotlin, you can use the `abstract` keyword to create an abstract class.

An abstract class cannot be instantiated directly, but it can be subclassed. Subclasses of an abstract class must provide implementations for all the abstract methods defined in the abstract class.

Here's an example that demonstrates abstraction in Kotlin:

```kotlin
abstract class Employee(val name: String) {
    fun sayMyName() {
        println(name)
    }
}

class Manager(name: String) : Employee(name) {
    // Additional implementation specific to the Manager class
}

fun main() {
    val manager = Manager("Mazen")
    manager.sayMyName()
}
```

In this example, we have an abstract class called `Employee` with a single property `name` and a method `sayMyName()`. The `sayMyName()` method simply prints the name of the employee.

The `Manager` class is a subclass of `Employee` and it inherits the `name` property and the `sayMyName()` method. Since the `Manager` class does not provide any additional implementation, it inherits the behavior defined in the `Employee` class.

In the `main()` function, we create an instance of the `Manager` class with the name "Mazen" and call the `sayMyName()` method. This will print "Mazen" to the console.

Abstraction allows you to define common behavior in an abstract class and provide specific implementations in its subclasses. It helps in organizing and structuring your code, making it more maintainable and extensible.
