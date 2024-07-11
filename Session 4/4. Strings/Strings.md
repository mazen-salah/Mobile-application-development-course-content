# Strings in Java

In Java, a string is a sequence of characters. It is represented by the `String` class and is widely used in programming. Here are some important aspects of strings in Java:

## Creating Strings

Strings can be created in Java using the following methods:

- Using string literals: `String str = "Hello, World!";`
- Using the `new` keyword: `String str = new String("Hello, World!");`

## String Length

To find the length of a string, you can use the `length()` method. It returns the number of characters in the string. For example:

```java
String str = "Hello, World!";
int length = str.length(); // length is 13
```

## String Concatenation

String concatenation is the process of combining two or more strings into a single string. In Java, you can concatenate strings using the `+` operator or the `concat()` method. For example:

```java
String str1 = "Hello";
String str2 = "World";
String result = str1 + " " + str2; // result is "Hello World"

// Using the concat() method
String result = str1.concat(" ").concat(str2); // result is "Hello World"
```

## Comparing Strings

In Java, you can compare two strings for equality using the `equals()` method. It returns `true` if the strings are equal, and `false` otherwise. For example:

```java
String str1 = "Hello";
String str2 = "Hello";
boolean isEqual = str1.equals(str2); // isEqual is true
```

## Checking if a String is Empty

To check if a string is empty, you can use the `isEmpty()` method. It returns `true` if the string has a length of 0, and `false` otherwise. For example:

```java
String str = "";
boolean isEmpty = str.isEmpty(); // isEmpty is true
```
## charAt(index)

The `charAt(index)` method in Java returns the character at the specified index in a string. The index starts from 0, so the first character is at index 0, the second character is at index 1, and so on. If the index is out of range, it will throw an `IndexOutOfBoundsException`. Here's an example:

```java
String str = "Hello, World!";
char character = str.charAt(7); // character is 'W'
```

## substring(startIndex, endIndex)

The `substring(startIndex, endIndex)` method in Java returns a new string that is a substring of the original string. It starts from the `startIndex` and ends at the `endIndex - 1`. If the `endIndex` is not specified, it will return the substring from `startIndex` to the end of the string. Here's an example:

```java
String str = "Hello, World!";
String substring = str.substring(7, 12); // substring is "World"
```

## toLowerCase() and toUpperCase()

The `toLowerCase()` method in Java converts all characters in a string to lowercase, while the `toUpperCase()` method converts them to uppercase. These methods return a new string with the converted characters. Here are some examples:

```java
String str = "Hello, World!";
String lowercase = str.toLowerCase(); // lowercase is "hello, world!"
String uppercase = str.toUpperCase(); // uppercase is "HELLO, WORLD!"
```

## trim()

The `trim()` method in Java removes leading and trailing whitespace from a string. It returns a new string with the whitespace removed. Here's an example:

```java
String str = "   Hello, World!   ";
String trimmed = str.trim(); // trimmed is "Hello, World!"
```

These are just a few examples of the many methods available in the `String` class for manipulating strings.

These are just a few examples of the many methods available in the `String` class.
