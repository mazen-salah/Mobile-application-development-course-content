# Abstraction

In object-oriented programming (OOP), abstraction is a fundamental concept that allows us to create models of real-world entities in our code. It helps us focus on the essential characteristics and behaviors of an object while hiding unnecessary details.

In Java, abstraction is achieved through the use of abstract classes and interfaces. Let's take a closer look at each of them:

1. Abstract Classes:
   - An abstract class is a class that cannot be instantiated directly. It serves as a blueprint for other classes to inherit from.
   - Abstract classes can have both abstract and non-abstract methods.
   - Abstract methods are declared without an implementation and must be overridden by the concrete (non-abstract) subclasses.
   - Abstract classes can also have instance variables and constructors.
   - Abstract classes are useful when you want to provide a common interface and some default behavior for a group of related classes.

Here's an example of an abstract class in Java:

```java
abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract void sound();

    public void sleep() {
        System.out.println("Zzzz...");
    }
}
```

2. Interfaces:
   - An interface is a collection of abstract methods. It defines a contract that implementing classes must adhere to.
   - Interfaces cannot have instance variables or constructors.
   - All methods in an interface are implicitly abstract and public.
   - A class can implement multiple interfaces, allowing for multiple inheritance of behavior.
   - Interfaces are useful when you want to define a common set of methods that multiple unrelated classes can implement.

Here's an example of an interface in Java:

```java
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}
```

By using abstraction, we can create a hierarchy of classes and interfaces that provide a clear structure and organization to our code. It allows us to separate the essential characteristics and behaviors of objects from their specific implementations, promoting code reusability and maintainability.
