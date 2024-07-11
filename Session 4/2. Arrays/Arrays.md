# Arrays in Java

In Java, an array is a data structure that allows you to store multiple values of the same type in a single variable. It provides a convenient way to work with collections of data.

## Declaring an Array

To declare an array in Java, you need to specify the type of elements it will hold, followed by the name of the array and square brackets `[]`. For example:

```java
int[] numbers; // declares an array of integers
String[] names; // declares an array of strings
```

## Initializing an Array

After declaring an array, you can initialize it with values using the `new` keyword. The size of the array is specified within the square brackets. For example:

```java
int[] numbers = new int[5]; // initializes an array of integers with a size of 5
String[] names = new String[3]; // initializes an array of strings with a size of 3
```

## Accessing Array Elements

Array elements are accessed using their index, which starts from 0. To access an element, you need to specify the array name followed by the index within square brackets. For example:

```java
int[] numbers = {1, 2, 3, 4, 5};
System.out.println(numbers[0]); // prints the first element (1)
System.out.println(numbers[2]); // prints the third element (3)
```

## Array Length

The length of an array can be obtained using the `length` property. For example:

```java
int[] numbers = {1, 2, 3, 4, 5};
System.out.println(numbers.length); // prints the length of the array (5)
```

## Iterating Over an Array

You can use a loop, such as a `for` loop, to iterate over the elements of an array. For example:

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

## Conclusion

Arrays are a fundamental data structure in Java that allow you to store and manipulate collections of values. Understanding how to declare, initialize, access, and iterate over arrays is essential for Java developers.
