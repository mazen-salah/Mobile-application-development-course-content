# Module 1 — Labs

Four labs of increasing difficulty. Each combines several concepts from the module.

---

## Lab 1 — Foundations Bootcamp

Cover variables, operators, conditionals.

### Task 1.1 — Simple Calculator

Write a `Calculator` program that:

- Stores two numbers `a` and `b`
- Computes and prints sum, difference, product, quotient, remainder
- Uses ternary to print "a is larger" or "b is larger"

### Task 1.2 — Faculty Data Collection

Store data about 3 students (name, age, GPA). Print each one, then:

- The average GPA
- The name of the student with the highest GPA
- How many students have GPA >= 3.5

??? success "Solution sketch"
    Use parallel arrays or three separate variables per student (we'll use ArrayList in Lab 4).

---

## Lab 2 — Logic & Loops

Cover loops, nested loops, control flow.

### Task 2.1 — Multiplication Table

Print the multiplication table from 1 to 10 (the 10×10 grid) using nested loops.

### Task 2.2 — Pyramid

Print a pyramid of `*` characters, 10 rows tall:

```
         *
        ***
       *****
      *******
     *********
```

Hint: outer loop for rows, two inner loops — one for spaces, one for stars.

### Task 2.3 — Number Classifier

For numbers 1 to 100, print:

- "FizzBuzz" if divisible by 15
- "Fizz" if divisible by 3
- "Buzz" if divisible by 5
- The number otherwise

---

## Lab 3 — Arrays & Strings

### Task 3.1 — Statistics

Given `int[] grades = {78, 92, 65, 87, 91, 73, 88, 95, 60, 82}`:

1. Print min, max, average
2. Count how many are >= 80
3. Print only the passing grades (>= 60), sorted descending

### Task 3.2 — Word Counter

Given a sentence, output:

- The number of words
- The number of characters (excluding spaces)
- The longest word
- The shortest word
- The same sentence with all vowels removed

### Task 3.3 — Password Validator

Write a method `boolean isStrong(String pw)` returning true only if:

- At least 8 characters
- Contains at least one uppercase letter
- Contains at least one digit
- Contains at least one of `!@#$%^&*`

---

## Lab 4 — OOP Project: Mini-Cart

Build a working shopping cart with proper OOP design.

### Requirements

Create three classes:

1. **`Product`** — `private` fields for `name`, `price`, `stock`. Constructor, getters, and a `purchase(int qty)` method that decrements stock or throws if not enough.
2. **`Cart`** — uses `ArrayList<Product>` and `ArrayList<Integer>` (quantities). Methods: `add(Product, int)`, `remove(int index)`, `total()`, `printReceipt()`.
3. **`Store`** — has a `main` that:
    - Creates at least 5 products
    - Creates a cart
    - Adds 3 items
    - Removes 1
    - Prints the receipt
    - Calls `purchase` on each remaining product to decrement stock

### Bonus

Apply concepts from later lessons:

- Make `Product`'s fields `private final` (encapsulation)
- Add a `PerishableProduct extends Product` with an expiry date (inheritance)
- Add validation that throws `IllegalArgumentException` for bad inputs (exceptions)
- Use a `HashMap<String, Product>` to look up products by SKU (collections)

??? success "Architecture hint"

    ```mermaid
    classDiagram
        Cart "1" *-- "many" Product : contains
        Product <|-- PerishableProduct
        Store ..> Cart : creates
        Store ..> Product : creates
    ```

Submit your solution as a public GitHub repo and link it in a [GitHub issue](https://github.com/mazen-salah/Mobile-application-development-course-content/issues/new) with title `[Lab 4] <Your Name>`.

[← Previous: Exceptions](18-exceptions.md){ .md-button } [Next: Module 2 — Kotlin →](../02-kotlin/index.md){ .md-button .md-button--primary }
