# Methods in Java

In Java, methods are a fundamental building block of programs. They allow you to encapsulate a block of code and execute it whenever needed. Methods provide modularity, reusability, and maintainability to your code.

## Syntax

The syntax for defining a method in Java is as follows:

```java
accessModifier returnType methodName(parameterList) {
    // method body
    // code to be executed
    // return statement (if applicable)
}
```

- `accessModifier`: Specifies the visibility of the method (e.g., `public`, `private`, `protected`).
- `returnType`: Specifies the type of value the method returns (`void` if no return value).
- `methodName`: Name of the method.
- `parameterList`: List of input parameters (optional).

## Method Types

There are two main types of methods in Java:

1. **Void methods**: These methods do not return any value. They are used for performing actions or operations without returning a result.

2. **Non-void methods**: These methods return a value of a specific type. They are used when you need to perform an operation and return a result.

## Method Overloading

Java supports method overloading, which allows you to define multiple methods with the same name but different parameter lists. The compiler determines which method to call based on the arguments passed.

## Method Invocation

To invoke a method, you need to call it by its name followed by parentheses. If the method has parameters, you need to pass the appropriate arguments within the parentheses.

## Example

Here's an example of a method in Java:

```java
public class MyClass {
    public static void main(String[] args) {
        // Calling the greet method
        greet("John");
    }

    // Method definition
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
```

In this example, the `greet` method takes a `String` parameter `name` and prints a greeting message.
