The ternary operator in Java is a concise way to write conditional expressions. It allows you to make decisions based on a condition and assign a value to a variable or perform an action accordingly.

The syntax of the ternary operator is as follows:

```java
variable = (condition) ? value1 : value2;
```

Here's how it works:
- The condition is evaluated first. If the condition is true, the value assigned to `variable` will be `value1`. If the condition is false, the value assigned to `variable` will be `value2`.
- The `?` symbol separates the condition from the two possible values.
- The `:` symbol separates the two possible values.

Let's look at an example to understand it better:

```java
int age = 18;
String message = (age >= 18) ? "You are an adult" : "You are not an adult";
System.out.println(message);
```

In this example, we have a variable `age` with a value of 18. The ternary operator is used to assign a value to the `message` variable based on the age. If the age is greater than or equal to 18, the message will be "You are an adult". Otherwise, the message will be "You are not an adult". In this case, since the age is 18, the output will be "You are an adult".

The ternary operator can also be nested within each other to handle more complex conditions. However, it's important to use it judiciously and maintain code readability.