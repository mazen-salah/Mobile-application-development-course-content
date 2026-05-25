# Exceptions

Things go wrong. The network drops, the file is missing, the user enters "abc" when you expected a number. **Exceptions** are Java's mechanism for handling these without your program crashing.

## The crash

```java
public class Crash {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(arr[10]);   // ArrayIndexOutOfBoundsException
        System.out.println("Done");    // never runs
    }
}
```

Without handling, the JVM prints a stack trace and the program dies.

## try-catch

```java
public class Safe {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds: " + e.getMessage());
        }
        System.out.println("Done");   // runs!
    }
}
```

Control flow:

1. Try the code in `try { }`
2. If an exception of the matching type is thrown, jump to `catch`
3. Continue after the `try-catch` block

## Multiple catches

```java
try {
    String s = null;
    System.out.println(s.length());
    int x = Integer.parseInt("abc");
} catch (NullPointerException e) {
    System.out.println("Was null");
} catch (NumberFormatException e) {
    System.out.println("Bad number");
} catch (Exception e) {
    System.out.println("Something else: " + e);
}
```

Catches are checked top-down. **Order matters** — put specific exceptions before general ones.

## Multi-catch (Java 7+)

```java
try {
    // ...
} catch (NullPointerException | NumberFormatException e) {
    System.out.println("Bad input: " + e);
}
```

## finally

Runs **always** — whether the try succeeded, threw, or returned:

```java
FileInputStream f = null;
try {
    f = new FileInputStream("data.txt");
    // ... use file
} catch (IOException e) {
    System.out.println("Read failed");
} finally {
    if (f != null) f.close();   // always close the file
}
```

Use `finally` for cleanup that must always happen.

## try-with-resources (cleaner)

For things that implement `AutoCloseable` (files, sockets, DB connections), let Java close them automatically:

```java
try (FileInputStream f = new FileInputStream("data.txt")) {
    // ... use file
} catch (IOException e) {
    System.out.println("Read failed");
}
// f.close() is called automatically — no finally needed
```

This is the modern idiom. Use it whenever possible.

## Checked vs unchecked exceptions

| Type | Extends | Must catch or declare? | Examples |
|---|---|---|---|
| **Checked** | `Exception` | Yes — compiler enforces | `IOException`, `SQLException` |
| **Unchecked** | `RuntimeException` | No | `NullPointerException`, `IllegalArgumentException` |

```java
// Checked — must handle:
public void readFile() throws IOException {  // declare
    // ...
}

// or
public void readFile() {
    try { /* ... */ }
    catch (IOException e) { /* ... */ }
}

// Unchecked — handle optionally:
public void divide(int a, int b) {
    return a / b;   // ArithmeticException if b==0, no `throws` required
}
```

## throw — raising your own

```java
public class BankAccount {
    private double balance;

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }
}
```

Use Java's built-in exceptions when they fit:

- `IllegalArgumentException` — wrong input
- `IllegalStateException` — object is in the wrong state for this operation
- `UnsupportedOperationException` — this method isn't implemented for this case
- `NullPointerException` — for an obvious reason (Java's `Objects.requireNonNull(x, "x")` throws this)

## Custom exceptions

When the built-ins don't fit, define your own:

```java
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(double requested, double available) {
        super("Requested $" + requested + " but only $" + available + " available");
    }
}

// usage
if (amount > balance) {
    throw new InsufficientFundsException(amount, balance);
}
```

## Best practices

- **Never catch `Exception` to silence it.** Either handle it meaningfully or let it propagate.
- **Don't use exceptions for control flow.** Exceptions are slow and obscure intent. If you can check `if (x != null)`, do that instead of `try { x.foo(); } catch (NPE e) {}`.
- **Log enough context.** `catch (IOException e) { log(e); }` is fine, but `catch (IOException e) {}` (empty catch) hides bugs forever.
- **Fail fast.** Throw at the boundary where the bad input enters your code, not 10 stack frames deep.

## Try it yourself

Write a method `int safeParse(String s)` that:

- Returns the parsed integer if `s` is a valid number
- Returns `-1` if parsing fails
- Does NOT crash the program

Test it with `"42"`, `"abc"`, `""`, `null`.

??? success "Solution"
    ```java
    public class SafeParser {
        public static int safeParse(String s) {
            if (s == null) return -1;
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        public static void main(String[] args) {
            System.out.println(safeParse("42"));    // 42
            System.out.println(safeParse("abc"));   // -1
            System.out.println(safeParse(""));      // -1
            System.out.println(safeParse(null));    // -1
        }
    }
    ```

[← Previous: Collections](17-collections.md){ .md-button } [Next: Labs →](labs.md){ .md-button }
