# Object-Oriented Programming (OOP)

Object-Oriented Programming (OOP) is a programming paradigm that focuses on organizing code into objects, which are instances of classes. Java is a popular programming language that fully supports OOP principles.

In Java, a class is a blueprint or template that defines the structure and behavior of objects. It serves as a blueprint for creating multiple objects with similar characteristics. A class encapsulates data (attributes) and behavior (methods) that define the object's state and actions.

To create an object from a class, you use the `new` keyword followed by the class name and parentheses. This process is called instantiation. Once an object is created, it has its own set of attributes and can perform actions defined by the class.

Here's an example of a simple class in Java:

```java
public class Car {
    // Attributes
    String brand;
    String color;
    int year;

    // Methods
    public void startEngine() {
        System.out.println("Engine started!");
    }

    public void accelerate() {
        System.out.println("Car is accelerating!");
    }
}
```

In this example, we have a `Car` class with three attributes (`brand`, `color`, and `year`) and two methods (`startEngine()` and `accelerate()`).

To create an object of the `Car` class, we can do the following:

```java
Car myCar = new Car();
```

Here, `myCar` is an object of the `Car` class. We use the `new` keyword to allocate memory for the object and initialize it. Now, `myCar` has its own set of attributes (`brand`, `color`, and `year`) and can perform actions (`startEngine()` and `accelerate()`) defined by the `Car` class.

We can access the attributes and methods of an object using the dot (`.`) operator. For example:

```java
myCar.brand = "Toyota";
myCar.color = "Red";
myCar.year = 2022;

myCar.startEngine();
myCar.accelerate();
```

In this code snippet, we set the values of the `brand`, `color`, and `year` attributes of `myCar`. Then, we call the `startEngine()` and `accelerate()` methods on `myCar`.

Classes and objects are fundamental concepts in OOP. They allow us to create modular and reusable code by encapsulating data and behavior into objects. By defining classes and creating objects, we can model real-world entities and interact with them in our programs.