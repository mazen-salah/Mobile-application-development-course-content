# Conditionals

Conditionals are how programs make decisions. Java gives you four mechanisms: `if`, `if-else`, `switch`, and the **ternary operator**.

## if

```java
int age = 20;

if (age >= 18) {
    System.out.println("Adult");
}
```

The condition inside `()` must be a `boolean`. Braces `{}` are optional for single statements but **always use them** — they prevent a class of bugs.

## if-else

```java
int score = 75;

if (score >= 50) {
    System.out.println("Passed");
} else {
    System.out.println("Failed");
}
```

## if-else if-else (chained)

```java
int score = 85;

if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else if (score >= 70) {
    System.out.println("C");
} else if (score >= 60) {
    System.out.println("D");
} else {
    System.out.println("F");
}
```

Order matters — Java evaluates top to bottom and stops at the first match.

## Nested if

```java
boolean isLoggedIn = true;
boolean isAdmin = true;

if (isLoggedIn) {
    if (isAdmin) {
        System.out.println("Admin panel");
    } else {
        System.out.println("User dashboard");
    }
} else {
    System.out.println("Please log in");
}
```

Avoid going more than 2 levels deep — extract logic into methods (covered in lesson 11) when it gets gnarly.

## Ternary operator

A one-line if-else for assignments:

```java
int age = 20;
String status = (age >= 18) ? "Adult" : "Minor";
// equivalent to:
// String status;
// if (age >= 18) { status = "Adult"; } else { status = "Minor"; }
```

Use ternary for **simple assignments only**. If the branches are complex, use full if-else.

## switch

When you're checking one variable against many values, `switch` is cleaner than a long if-else chain:

```java
int day = 3;
String name;

switch (day) {
    case 1: name = "Monday"; break;
    case 2: name = "Tuesday"; break;
    case 3: name = "Wednesday"; break;
    case 4: name = "Thursday"; break;
    case 5: name = "Friday"; break;
    case 6: name = "Saturday"; break;
    case 7: name = "Sunday"; break;
    default: name = "Unknown"; break;
}

System.out.println(name);  // Wednesday
```

**Always include `break`** at the end of each case, or the next case will "fall through" (this is occasionally desired but usually a bug).

### Modern switch (Java 14+)

```java
int day = 3;
String name = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 3 -> "Wednesday";
    case 4 -> "Thursday";
    case 5 -> "Friday";
    case 6, 7 -> "Weekend";
    default -> "Unknown";
};
```

Cleaner — no `break`, no fall-through, returns a value directly.

## Comparing strings

**Never** use `==` to compare strings:

```java
String a = "hello";
String b = "hello";

if (a == b)           // ❌ wrong — compares references
if (a.equals(b))      // ✅ correct — compares content
if (a.equalsIgnoreCase("HELLO"))  // ✅ ignore case
```

We'll cover why in the Strings lesson.

## Try it yourself

Write a program that takes an age and prints:

- "Free" if age < 5
- "Child price" if age 5–12
- "Student price" if age 13–22
- "Adult price" if age 23–64
- "Senior price" if age ≥ 65

??? success "Solution"
    ```java
    public class TicketPrice {
        public static void main(String[] args) {
            int age = 17;

            if (age < 5) {
                System.out.println("Free");
            } else if (age <= 12) {
                System.out.println("Child price");
            } else if (age <= 22) {
                System.out.println("Student price");
            } else if (age <= 64) {
                System.out.println("Adult price");
            } else {
                System.out.println("Senior price");
            }
        }
    }
    ```

[← Previous: Operators](06-operators.md){ .md-button } [Next: Loops →](08-loops.md){ .md-button }
