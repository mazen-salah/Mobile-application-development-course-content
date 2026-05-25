# Output & Hello World

Every programming course starts here. Print "Hello, World!" to the screen. Once that works, you have a complete development environment.

## The program

```java title="HelloWorld.java"
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## Anatomy

| Piece | What it does |
|---|---|
| `public class HelloWorld` | Defines a class named `HelloWorld`. The filename must match: `HelloWorld.java` |
| `public static void main(String[] args)` | The **entry point**. The JVM looks for this exact signature to know where to start |
| `System.out.println(...)` | Print to the console and add a newline at the end |
| `;` (semicolon) | Ends every statement. Forgetting it is the #1 beginner error |

## How to run it

### Option 1 — Command line (recommended for learning)

```bash
javac HelloWorld.java     # compile → produces HelloWorld.class
java HelloWorld           # run (no .class extension!)
```

You should see:
```
Hello, World!
```

### Option 2 — VS Code

1. Install the **Extension Pack for Java**
2. Open the folder containing `HelloWorld.java`
3. Click the ▶ "Run" button above `main`

### Option 3 — IntelliJ IDEA

1. Create a new project, select Java
2. Right-click the file → **Run 'HelloWorld.main()'**

## print vs println

```java
System.out.println("Line 1");   // prints "Line 1" + newline
System.out.print("No newline"); // prints just "No newline"
System.out.println();           // prints empty line
```

## printf — formatted output

```java
String name = "Mazen";
int age = 25;
System.out.printf("Name: %s, Age: %d%n", name, age);
// Name: Mazen, Age: 25
```

`%s` for strings, `%d` for integers, `%f` for floats, `%n` for newline.

## Try it yourself

Write a program that prints your name on one line, your age on another, and your favorite app on a third — using **one `println` per line**.

??? success "Solution"
    ```java
    public class AboutMe {
        public static void main(String[] args) {
            System.out.println("Mazen");
            System.out.println("25");
            System.out.println("WhatsApp");
        }
    }
    ```

## Common mistakes

!!! warning "File name must match class name"
    If your class is `public class Cart`, the file **must** be `Cart.java`. Mismatch → compile error.

!!! warning "Case-sensitive"
    `system.out.println` is different from `System.out.println`. Java is case-sensitive everywhere.

!!! warning "Forgot semicolon"
    Every statement ends with `;`. Compiler error: `';' expected`.

[← Previous: Introduction](01-introduction.md){ .md-button } [Next: Comments →](03-comments.md){ .md-button }
