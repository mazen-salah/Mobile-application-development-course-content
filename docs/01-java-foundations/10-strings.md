# Strings

Strings are everywhere in mobile apps — usernames, messages, JSON, button labels. Java's `String` class has dozens of useful methods.

## Creating Strings

```java
String a = "Hello";
String b = new String("Hello");
```

Use the literal form (`"Hello"`) — it's shorter and lets Java reuse strings from a cache called the **string pool**.

## Concatenation

```java
String first = "Mazen";
String last = "Salah";
String full = first + " " + last;        // "Mazen Salah"
String full2 = first.concat(" ").concat(last);  // same

int age = 25;
String summary = first + " is " + age + " years old";
// Java auto-converts age to string
```

For building long strings in a loop, use `StringBuilder` instead — it's much faster.

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i).append(",");
}
String result = sb.toString();
```

## Comparison — use .equals(), not ==

```java
String a = "hello";
String b = "hello";
String c = new String("hello");

a == b              // true  (string pool — same reference)
a == c              // false (different object)
a.equals(b)         // true  (content comparison)
a.equals(c)         // true
a.equalsIgnoreCase("HELLO")  // true
```

**Always use `.equals()`** to compare string content. `==` checks reference equality and will burn you.

## Common methods

```java
String s = "Mobile App Development";

s.length();              // 22
s.charAt(0);             // 'M'
s.toUpperCase();         // "MOBILE APP DEVELOPMENT"
s.toLowerCase();         // "mobile app development"
s.indexOf("App");        // 7
s.indexOf("Foo");        // -1 (not found)
s.contains("App");       // true
s.startsWith("Mobile");  // true
s.endsWith("ment");      // true

s.substring(7);          // "App Development"
s.substring(7, 10);      // "App"

s.replace("App", "Game"); // "Mobile Game Development"
s.trim();                // removes leading/trailing whitespace

s.split(" ");            // ["Mobile", "App", "Development"]
```

## Strings are immutable

Once created, a String can never be changed. Methods like `toUpperCase()` return a **new** string:

```java
String s = "hello";
s.toUpperCase();         // returns "HELLO" but doesn't change s
System.out.println(s);   // still "hello"

s = s.toUpperCase();     // reassign — NOW s is "HELLO"
```

This is why building strings in a loop with `+=` is slow — every concatenation creates a new String. Use `StringBuilder`.

## Conversion

```java
// String → int
String num = "42";
int n = Integer.parseInt(num);          // 42

// String → double
double d = Double.parseDouble("3.14");  // 3.14

// int → String
String s = String.valueOf(42);          // "42"
String s2 = Integer.toString(42);       // "42"

// Anything → String (concatenation trick)
String s3 = "" + 42;                    // "42"
```

`parseInt("abc")` throws `NumberFormatException`. We'll handle that in the Exceptions lesson.

## String formatting

```java
String name = "Mazen";
int age = 25;
double height = 1.78;

String s = String.format("%s is %d years old, %.2fm tall", name, age, height);
// "Mazen is 25 years old, 1.78m tall"
```

## Try it yourself

Write a program that takes a sentence and prints:

1. Its length
2. The number of words (hint: `split` on spaces)
3. The same sentence in reverse word order
4. Whether it contains "java" (case-insensitive)

??? success "Solution"
    ```java
    public class StringPractice {
        public static void main(String[] args) {
            String sentence = "Learning Java is the foundation of Android";

            System.out.println("Length: " + sentence.length());

            String[] words = sentence.split(" ");
            System.out.println("Word count: " + words.length);

            StringBuilder reversed = new StringBuilder();
            for (int i = words.length - 1; i >= 0; i--) {
                reversed.append(words[i]).append(" ");
            }
            System.out.println("Reversed: " + reversed.toString().trim());

            System.out.println("Has 'java'? " + sentence.toLowerCase().contains("java"));
        }
    }
    ```

[← Previous: Arrays](09-arrays.md){ .md-button } [Next: Methods →](11-methods.md){ .md-button }
