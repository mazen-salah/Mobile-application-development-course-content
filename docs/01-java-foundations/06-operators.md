# Operators

Operators are how you combine values. Most are intuitive (`+`, `-`, `*`, `/`) but Java has a few you may not have seen.

## Arithmetic

```java
int sum   = 10 + 5;   // 15
int diff  = 10 - 5;   // 5
int prod  = 10 * 5;   // 50
int quot  = 10 / 5;   // 2
int rem   = 10 % 3;   // 1  (modulo / remainder)

double avg = 10.0 / 3;  // 3.333...
```

`%` (modulo) is your friend — use it to check divisibility (`x % 2 == 0` → even).

## Assignment & shortcut assignment

```java
int x = 10;

x += 5;   // x = x + 5  → 15
x -= 3;   // x = x - 3  → 12
x *= 2;   // x = x * 2  → 24
x /= 4;   // x = x / 4  → 6
x %= 4;   // x = x % 4  → 2
```

## Increment & decrement

```java
int a = 5;
a++;       // a is now 6 (post-increment)
++a;       // a is now 7 (pre-increment)
a--;       // a is now 6
```

Pre vs post matters in expressions:

```java
int i = 5;
int x = i++;   // x = 5, i = 6  (post: assign first, then increment)
int j = 5;
int y = ++j;   // y = 6, j = 6  (pre: increment first, then assign)
```

Stick with post-increment unless you have a reason — pre-increment expressions confuse readers.

## Comparison (returns boolean)

```java
5 == 5    // true
5 != 5    // false
5 > 3     // true
5 < 3     // false
5 >= 5    // true
5 <= 4    // false
```

`==` checks **equality** — *not* assignment. Mixing them up is a classic beginner trap.

!!! warning "String comparison"
    For Strings, use `.equals()` NOT `==`. We'll cover this in the Strings lesson.

## Logical

```java
boolean a = true;
boolean b = false;

a && b    // false  (AND — both must be true)
a || b    // true   (OR  — at least one must be true)
!a        // false  (NOT — flip)
```

**Short-circuit evaluation:** Java stops checking once the result is decided. In `a && b`, if `a` is false, `b` is never evaluated. Useful for null checks:

```java
if (user != null && user.isActive()) {
    // safe — isActive() is only called when user is not null
}
```

## Bitwise (you'll rarely use these)

`&`, `|`, `^`, `~`, `<<`, `>>` operate on individual bits. Useful for hardware drivers, flags, performance hacks. **Most Android dev does not need these** — skim and move on.

## Operator precedence

Multiplication beats addition; comparison beats logical:

```java
int x = 2 + 3 * 4;        // 14, not 20 — * first
boolean ok = 5 > 3 && 2 < 4;  // true — comparison first
```

When in doubt, **add parentheses**. It's free, readable, and rules out ambiguity.

## Worked example

```java title="operators.java"
public class OperatorDemo {
    public static void main(String[] args) {
        int a = 17, b = 5;

        System.out.println("a + b = " + (a + b));   // 22
        System.out.println("a / b = " + (a / b));   // 3 (integer div)
        System.out.println("a % b = " + (a % b));   // 2

        boolean isEven = (a % 2 == 0);
        System.out.println("a is even? " + isEven); // false

        int total = 100;
        total += 20;
        System.out.println("Total: " + total);      // 120
    }
}
```

## Try it yourself

Write a program that:

1. Stores two integers
2. Prints sum, difference, product, quotient, remainder
3. Prints whether the first is larger
4. Prints whether both are positive

??? success "Solution"
    ```java
    public class TwoNumbers {
        public static void main(String[] args) {
            int x = 24, y = 7;

            System.out.println("Sum: " + (x + y));
            System.out.println("Diff: " + (x - y));
            System.out.println("Product: " + (x * y));
            System.out.println("Quotient: " + (x / y));
            System.out.println("Remainder: " + (x % y));
            System.out.println("x > y? " + (x > y));
            System.out.println("Both positive? " + (x > 0 && y > 0));
        }
    }
    ```

[← Previous: Data Types](05-data-types.md){ .md-button } [Next: Conditionals →](07-conditionals.md){ .md-button }
