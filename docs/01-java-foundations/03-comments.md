# Comments

Comments are notes for **humans** that the compiler ignores. They let you explain *why* something is written the way it is — not what, since well-named code already says what.

## Three flavors

```java
// Single-line comment — runs to end of line

/* Multi-line comment
   spanning several
   lines */

/**
 * Javadoc comment — used to document classes and methods.
 * Tools like javadoc generate HTML documentation from these.
 * @param name the user's name
 * @return a greeting string
 */
public String greet(String name) {
    return "Hello, " + name;
}
```

## When to comment

**Good comments explain WHY:**

```java
// Stripe charges in the smallest currency unit (cents), so multiply by 100
int amountInCents = dollars * 100;
```

**Bad comments restate WHAT** (the code already says it):

```java
// add 1 to counter
counter = counter + 1;
```

If a reader can derive *what* from the code alone, the comment is noise. Add a comment only when the *why* is non-obvious: a hidden constraint, a tricky bug fix, a workaround.

## TODO / FIXME conventions

Most IDEs highlight these patterns:

```java
// TODO: handle Arabic locale - dates display wrong in RTL
// FIXME: race condition when two users edit simultaneously
// HACK: temporary fix until backend ships the v2 API
```

## Comment out code (temporarily)

Sometimes you want to disable code without deleting it. Select it and press `Ctrl+/` (most IDEs) to toggle line comments.

```java
public static void main(String[] args) {
    System.out.println("This runs");
    // System.out.println("This is disabled");
}
```

But **don't commit commented-out code**. Delete it. Git remembers everything; you don't need a tombstone in the file.

## Try it yourself

Take the `HelloWorld` program from the last lesson and add:

1. A single-line comment above `main` explaining what it does
2. A multi-line comment at the top of the file with your name and the date

??? success "Solution"
    ```java
    /*
     * HelloWorld.java
     * Author: Mazen
     * Date: 2024-01-15
     */
    public class HelloWorld {

        // Entry point — prints a greeting to the console
        public static void main(String[] args) {
            System.out.println("Hello, World!");
        }
    }
    ```

[← Previous: Output](02-output.md){ .md-button } [Next: Naming →](04-naming.md){ .md-button }
