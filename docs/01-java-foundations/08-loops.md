# Loops

Loops let you run the same code multiple times. Java has four: `for`, `while`, `do-while`, and the enhanced `for-each` (covered with Arrays).

## for loop

Use `for` when you know how many times to repeat:

```java
for (int i = 0; i < 5; i++) {
    System.out.println("Iteration " + i);
}
// Prints: Iteration 0, 1, 2, 3, 4
```

Three parts inside `()`:

1. **Initialization** — `int i = 0` runs once before the loop
2. **Condition** — `i < 5` checked before each iteration; loop stops when false
3. **Update** — `i++` runs after each iteration

## while loop

Use `while` when the loop should continue until some condition becomes false:

```java
int count = 0;

while (count < 5) {
    System.out.println("count = " + count);
    count++;
}
```

Forget to increment? **Infinite loop.** Press `Ctrl+C` to kill the program.

## do-while loop

Same as `while`, but runs the body at least once — the check happens *after*:

```java
int input;
do {
    System.out.println("Try again");
    input = getNextInput();
} while (input != 0);
```

Useful for input loops where you have to ask once before knowing whether to stop.

## break and continue

```java
// break — exit the loop immediately
for (int i = 0; i < 100; i++) {
    if (i == 5) break;
    System.out.println(i);
}
// Prints: 0, 1, 2, 3, 4

// continue — skip rest of body, go to next iteration
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue;
    System.out.println(i);
}
// Prints odd numbers: 1, 3, 5, 7, 9
```

## Nested loops

```java
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        System.out.print(i * j + "\t");
    }
    System.out.println();
}
// Output:
// 1  2  3
// 2  4  6
// 3  6  9
```

The inner loop completes fully for each iteration of the outer loop. If the outer runs `n` times and the inner runs `m` times, the body runs `n × m` times. Watch for performance.

## Common patterns

### Sum of 1 to n

```java
int n = 100;
int sum = 0;
for (int i = 1; i <= n; i++) {
    sum += i;
}
System.out.println(sum);  // 5050
```

### Factorial

```java
int n = 5;
int fact = 1;
for (int i = 1; i <= n; i++) {
    fact *= i;
}
System.out.println(fact);  // 120
```

### Multiplication table

```java
int x = 7;
for (int i = 1; i <= 10; i++) {
    System.out.println(x + " × " + i + " = " + (x * i));
}
```

### Print first N primes (with continue)

```java
int n = 10, found = 0, num = 2;
while (found < n) {
    boolean isPrime = true;
    for (int i = 2; i < num; i++) {
        if (num % i == 0) {
            isPrime = false;
            break;
        }
    }
    if (isPrime) {
        System.out.println(num);
        found++;
    }
    num++;
}
```

## Try it yourself

Write a program that prints all even numbers between 1 and 50, **except** multiples of 10.

??? success "Solution"
    ```java
    public class EvenNotMultipleOf10 {
        public static void main(String[] args) {
            for (int i = 1; i <= 50; i++) {
                if (i % 2 != 0) continue;
                if (i % 10 == 0) continue;
                System.out.print(i + " ");
            }
            System.out.println();
            // 2 4 6 8 12 14 16 18 22 24 26 28 32 34 36 38 42 44 46 48
        }
    }
    ```

## Common mistakes

!!! warning "Off-by-one errors"
    `i < 5` runs 5 times (i = 0,1,2,3,4). `i <= 5` runs 6 times. Always sanity-check.

!!! warning "Modifying the loop counter inside the loop"
    Don't change `i` inside a `for` loop body. If you need to skip, use `continue`.

[← Previous: Conditionals](07-conditionals.md){ .md-button } [Next: Arrays →](09-arrays.md){ .md-button }
