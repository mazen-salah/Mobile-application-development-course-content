# Polymorphism

Polymorphism is a fundamental concept in object-oriented programming, including Java. It allows objects of different classes to be treated as objects of a common superclass or interface. This means that a variable of a superclass type can refer to an object of any subclass that extends or implements that superclass or interface.

In Java, polymorphism is achieved through method overriding and method overloading.

Method overriding occurs when a subclass provides its own implementation of a method that is already defined in its superclass. The subclass method must have the same name, return type, and parameters as the superclass method. When you call the overridden method on an object, the subclass implementation is executed instead of the superclass implementation. This allows you to define specialized behavior for specific subclasses while still treating them as instances of the superclass.

Here's an example:

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Dog();
        Animal animal2 = new Cat();

        animal1.makeSound(); // Output: Dog barks
        animal2.makeSound(); // Output: Cat meows
    }
}
```

In this example, the `Animal` class has a `makeSound()` method. The `Dog` and `Cat` classes extend the `Animal` class and provide their own implementations of the `makeSound()` method. When we create objects of `Dog` and `Cat` and assign them to variables of type `Animal`, we can call the `makeSound()` method on those variables. The actual implementation of the method that gets executed depends on the type of the object, allowing us to achieve polymorphic behavior.

Method overloading, on the other hand, allows multiple methods with the same name but different parameters to coexist in a class. The appropriate method is chosen based on the number, types, and order of the arguments passed to it. Method overloading is not directly related to polymorphism, but it is often used in conjunction with polymorphism to provide different ways of interacting with objects.
