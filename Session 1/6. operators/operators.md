In Java, there are several arithmetic operators that you can use to perform mathematical operations. Here's an explanation of each arithmetic operator:

1. Addition (+): The addition operator is used to add two numbers together. For example, `int sum = 5 + 3;` will assign the value 8 to the variable `sum`.

2. Subtraction (-): The subtraction operator is used to subtract one number from another. For example, `int difference = 10 - 5;` will assign the value 5 to the variable `difference`.

3. Multiplication (*): The multiplication operator is used to multiply two numbers together. For example, `int product = 4 * 2;` will assign the value 8 to the variable `product`.

4. Division (/): The division operator is used to divide one number by another. For example, `int quotient = 10 / 2;` will assign the value 5 to the variable `quotient`. Note that if you divide two integers, the result will be an integer. If you want a decimal result, you can use floating-point numbers (e.g., `double`).

5. Modulo (%): The modulo operator is used to find the remainder of a division operation. For example, `int remainder = 10 % 3;` will assign the value 1 to the variable `remainder`. In this case, 10 divided by 3 equals 3 with a remainder of 1.

Now, let's talk about the increment and decrement operators:

1. Increment (++): The increment operator is used to increase the value of a variable by 1. There are two forms of the increment operator: pre-increment and post-increment. Pre-increment (`++variable`) increments the value of the variable before using it, while post-increment (`variable++`) increments the value of the variable after using it. For example:
   ```java
   int x = 5;
   int y = ++x; // y will be 6, x will be 6
   ```

2. Decrement (--): The decrement operator is used to decrease the value of a variable by 1. Similar to the increment operator, there are pre-decrement (`--variable`) and post-decrement (`variable--`) forms. For example:
   ```java
   int a = 10;
   int b = a--; // b will be 10, a will be 9
   ```

Finally, let's discuss the rules of operator priority, also known as operator precedence:

1. Parentheses: Operations inside parentheses are evaluated first.

2. Increment and decrement: Pre-increment and pre-decrement operators are evaluated next.

3. Multiplication, division, and modulo: These operators have higher precedence than addition and subtraction.

4. Addition and subtraction: These operators have lower precedence than multiplication, division, and modulo.

It's important to note that if you have multiple operators with the same precedence, the evaluation order is determined by the associativity of the operators. For example, in the expression `a + b - c`, the addition and subtraction operators have the same precedence, but they are evaluated from left to right.