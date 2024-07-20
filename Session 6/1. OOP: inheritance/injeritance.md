# Inheritance

Inheritance is a fundamental concept in object-oriented programming, including Java. It allows you to create new classes based on existing classes, inheriting their properties and behaviors. The existing class is called the "parent" or "superclass," and the new class is called the "child" or "subclass."

In Java, you can achieve inheritance using the `extends` keyword. When a class extends another class, it inherits all the non-private members (fields and methods) of the superclass. This means that the subclass can access and use those members as if they were defined within the subclass itself.

Here's an example to illustrate inheritance in Java:

```java
// Parent class
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class
class Dog extends Animal {
    public Dog(String name) {
        super(name); // Call the constructor of the superclass
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy");
        dog.eat(); // Inherited from Animal class
        dog.bark(); // Defined in Dog class
    }
}
```

In this example, the `Animal` class is the superclass, and the `Dog` class is the subclass. The `Dog` class extends the `Animal` class using the `extends` keyword. As a result, the `Dog` class inherits the `name` field and the `eat()` method from the `Animal` class.

In the `main` method, we create an instance of the `Dog` class and call both the inherited `eat()` method and the `bark()` method, which is specific to the `Dog` class.

Inheritance allows for code reuse, promotes code organization, and enables the creation of class hierarchies. It is an essential concept in object-oriented programming and provides a powerful mechanism for creating relationships between classes
