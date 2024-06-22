In Java, there are several arithmetic operators that you can use to perform mathematical operations. Here's an explanation of each arithmetic operator:

1. Addition (+): The addition operator is used to add two numbers together. For example, `int sum = 5 + 3;` will assign the value 8 to the variable `sum`.

2. Subtraction (-): The subtraction operator is used to subtract one number from another. For example, `int difference = 10 - 5;` will assign the value 5 to the variable `difference`.

3. Multiplication (*): The multiplication operator is used to multiply two numbers together. For example, `int product = 4 * 2;` will assign the value 8 to the variable `product`.

4. Division (/): The division operator is used to divide one number by another. For example, `int quotient = 10 / 2;` will assign the value 5 to the variable `quotient`. Note that if you divide two integers, the result will be an integer. If you want a decimal result, you can use floating-point numbers (e.g., `double`).

5. Modulo (%): The modulo operator is used to find the remainder of a division operation. For example, `int remainder = 10 % 3;` will assign the value 1 to the variable `remainder`. In this case, 10 divided by 3 equals 3 with a remainder of 1.

Now, let's talk about the increment and decrement operators:

1. Increment (++): The increment operator is used to increase the value of a variable by 1. There are two forms of the increment operator: pre-increment and post-increment. Pre-increment (`++variable`) increments the value of the variable before using it, while post-increment (`variable++`) increments the value of the variable after using it.

2. Decrement (--): The decrement operator is used to decrease the value of a variable by 1. Similar to the increment operator, there are pre-decrement (`--variable`) and post-decrement (`variable--`) forms.

Finally, let's discuss the rules of operator priority, also known as operator precedence:

1. Parentheses: Operations inside parentheses are evaluated first.

2. Increment and decrement: Pre-increment and pre-decrement operators are evaluated next.

3. Multiplication, division, and modulo: These operators have higher precedence than addition and subtraction.

4. Addition and subtraction: These operators have lower precedence than multiplication, division, and modulo.

It's important to note that if you have multiple operators with the same precedence, the evaluation order is determined by the associativity of the operators. For example, in the expression `a + b - c`, the addition and subtraction operators have the same precedence, but they are evaluated from left to right.

Now, let's look at some examples to further illustrate the usage of arithmetic operators in Java:

Example 1: Addition
```java
int num1 = 5;
int num2 = 3;
int sum = num1 + num2; // sum will be 8
```

Example 2: Subtraction
```java
int num1 = 10;
int num2 = 5;
int difference = num1 - num2; // difference will be 5
```

Example 3: Multiplication
```java
int num1 = 4;
int num2 = 2;
int product = num1 * num2; // product will be 8
```

Example 4: Division
```java
int num1 = 10;
int num2 = 2;
int quotient = num1 / num2; // quotient will be 5
```

Example 5: Modulo
```java
int num1 = 10;
int num2 = 3;
int remainder = num1 % num2; // remainder will be 1
```

Example 6: Increment
```java
int x = 5;
int y = ++x; // y will be 6, x will be 6
```

Example 7: Decrement
```java
int a = 10;
int b = a--; // b will be 10, a will be 9
```

These examples demonstrate the basic usage of arithmetic operators in Java. Remember to consider operator precedence and associativity when working with multiple operators in an expression.

Now, let's look at some examples to further illustrate the usage of operator priority in Java:

Example 1: Parentheses
```java
int result = (5 + 3) * 2; // result will be 16
```
In this example, the addition operation inside the parentheses is evaluated first, resulting in 8. Then, the multiplication operation is performed, resulting in the final value of 16.

Example 2: Increment and decrement
```java
int x = 5;
int y = ++x * 2; // y will be 12, x will be 6
```
In this example, the pre-increment operator is evaluated first, increasing the value of `x` to 6. Then, the multiplication operation is performed, resulting in the final value of 12 for `y`.

Example 3: Multiplication, division, and modulo
```java
int result = 10 / 2 * 3 % 4; // result will be 1
```
In this example, the division operation is evaluated first, resulting in 5. Then, the multiplication operation is performed, resulting in 15. Finally, the modulo operation is performed, resulting in the final value of 1.

Example 4: Addition and subtraction
```java
int result = 10 - 5 + 2; // result will be 7
```
In this example, the subtraction operation is evaluated first, resulting in 5. Then, the addition operation is performed, resulting in the final value of 7.

These examples demonstrate how the operator priority affects the evaluation order of expressions in Java. Remember to consider the priority and associativity of operators when working with complex expressions.
