# Nested loops

nested loops in Java allow you to iterate over multiple levels of data structures, such as nested arrays or matrices. They provide a way to perform repetitive tasks for each combination of elements in the nested structures.

The basic syntax of a nested for loop in Java is as follows:

```java
for (initialization; condition; increment) {
    // Outer loop code
    
    for (initialization; condition; increment) {
        // Inner loop code
    }
}
```

Here's a breakdown of each part:

- Initialization: This is where you initialize the loop control variable(s). It is typically used to set the initial values of the loop counters.

- Condition: This is the condition that is evaluated before each iteration of the loop. If the condition is true, the loop continues; otherwise, it terminates.

- Increment: This is where you update the loop control variable(s) after each iteration. It is usually used to increment or decrement the loop counters.

- Outer loop code: This is the code that will be executed for each iteration of the outer loop.

- Inner loop code: This is the code that will be executed for each iteration of the inner loop. It is nested inside the outer loop.

Here's an example to illustrate how nested for loops work:

```java
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.println("i: " + i + ", j: " + j);
    }
}
```

In this example, we have an outer loop that iterates from 1 to 3, and an inner loop that also iterates from 1 to 3. The `System.out.println` statement inside the inner loop prints the values of `i` and `j` for each combination. The output will be:

```
i: 1, j: 1
i: 1, j: 2
i: 1, j: 3
i: 2, j: 1
i: 2, j: 2
i: 2, j: 3
i: 3, j: 1
i: 3, j: 2
i: 3, j: 3
```

As you can see, the inner loop is executed completely for each iteration of the outer loop, resulting in all possible combinations of `i` and `j` being printed.

Nested for loops are commonly used when working with multi-dimensional arrays or when you need to perform operations on each element of a nested data structure. They provide a powerful way to handle complex iterations and computations.
