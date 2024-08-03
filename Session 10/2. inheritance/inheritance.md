# Inheritance in Kotlin

Inheritance is a fundamental concept in object-oriented programming that allows a class to inherit properties and behaviors from another class. In Kotlin, you can create a new class by inheriting from an existing class using the `:` symbol followed by the name of the superclass.

When a class inherits from another class, it is called a subclass or derived class, and the class it inherits from is called the superclass or base class. The subclass inherits all the non-private properties and functions of the superclass, allowing you to reuse and extend the functionality of the existing class.

Here's an example to illustrate inheritance in Kotlin:

```kotlin
open class Animal(val name: String) {
    fun eat() {
        println("$name is eating.")
    }
}

class Dog(name: String) : Animal(name) {
    fun bark() {
        println("$name is barking.")
    }
}

fun main() {
    val dog = Dog("Buddy")
    dog.eat() // Output: Buddy is eating.
    dog.bark() // Output: Buddy is barking.
}
```

In this example, we have a superclass called `Animal` with a property `name` and a function `eat()`. The `Dog` class is a subclass of `Animal` and inherits the `name` property and `eat()` function. Additionally, the `Dog` class defines its own function `bark()`

In the `main()` function, we create an instance of the `Dog` class called `dog`. We can call both the inherited `eat()` function and the `bark()` function on the `dog` object.

By using inheritance, we can create a hierarchy of classes that share common properties and behaviors, while allowing each subclass to have its own unique characteristics
