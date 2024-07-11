# for each

The `for each` loop in Java is used to iterate over elements in an array or a collection. It provides a simplified syntax for iterating through each element without the need for an explicit index. Here's an example:

```java
int[] numbers = {1, 2, 3, 4, 5};

// Using for each loop to iterate over the array
for (int num : numbers) {
    System.out.println(num);
}
```

In this example, the `for each` loop iterates over each element in the `numbers` array and prints it to the console.

Here's another example using a collection:

```java
List<String> fruits = new ArrayList<>();
fruits.add("Apple");
fruits.add("Banana");
fruits.add("Orange");

// Using for each loop to iterate over the collection
for (String fruit : fruits) {
    System.out.println(fruit);
}
```

In this case, the `for each` loop iterates over each element in the `fruits` collection and prints it to the console.

The `for each` loop is a convenient way to iterate over elements in an array or a collection without the need for manual indexing. It simplifies the code and makes it more readable.