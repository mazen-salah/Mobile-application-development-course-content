# Arrays

An **array** stores multiple values of the same type in a fixed-size container, accessed by index. Arrays are the foundation of every collection in Java.

## Declaration

```java
int[] scores = new int[5];        // 5 ints, all initialized to 0
String[] names = new String[3];   // 3 nulls

int[] primes = {2, 3, 5, 7, 11};  // inline initialization
String[] cities = {"Cairo", "Alexandria", "Giza"};
```

## Access by index

Arrays are **zero-indexed** — the first element is at position 0:

```java
int[] nums = {10, 20, 30, 40, 50};

System.out.println(nums[0]);          // 10
System.out.println(nums[4]);          // 50
System.out.println(nums[nums.length - 1]);  // 50 (last element)

nums[2] = 99;                         // modify
System.out.println(nums[2]);          // 99
```

Going out of bounds (`nums[5]` here) throws `ArrayIndexOutOfBoundsException` at runtime.

## Length

```java
int[] arr = {1, 2, 3, 4};
System.out.println(arr.length);  // 4
```

Note: `length` is a **field** (no parentheses), not a method. (Strings use `.length()` — annoyingly inconsistent.)

## Iterating

### Classic for loop

```java
int[] scores = {85, 92, 78, 66, 100};

for (int i = 0; i < scores.length; i++) {
    System.out.println("Score " + i + ": " + scores[i]);
}
```

### For-each (cleaner when you don't need the index)

```java
int total = 0;
for (int score : scores) {
    total += score;
}
double avg = (double) total / scores.length;
System.out.println("Average: " + avg);
```

Read `for (int score : scores)` as "for each `int score` in `scores`".

## 2D arrays

An array of arrays:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

System.out.println(matrix[1][2]);  // 6 (row 1, col 2)

for (int row = 0; row < matrix.length; row++) {
    for (int col = 0; col < matrix[row].length; col++) {
        System.out.print(matrix[row][col] + " ");
    }
    System.out.println();
}
```

## Common operations

### Find max

```java
int[] nums = {17, 42, 8, 99, 23};
int max = nums[0];
for (int n : nums) {
    if (n > max) max = n;
}
System.out.println("Max: " + max);  // 99
```

### Reverse in place

```java
int[] arr = {1, 2, 3, 4, 5};
for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}
// arr is now {5, 4, 3, 2, 1}
```

### Pretty-print

```java
import java.util.Arrays;

int[] arr = {1, 2, 3};
System.out.println(Arrays.toString(arr));  // [1, 2, 3]

int[][] m = {{1, 2}, {3, 4}};
System.out.println(Arrays.deepToString(m)); // [[1, 2], [3, 4]]
```

## Arrays are fixed-size

Once you create `new int[5]`, you can't grow it. For a resizable collection, use `ArrayList` (covered in lesson 17).

## Try it yourself

Given `{45, 89, 12, 67, 23, 91, 5}`, find:

1. The smallest value
2. The sum
3. The average
4. How many values are above the average

??? success "Solution"
    ```java
    public class ArrayStats {
        public static void main(String[] args) {
            int[] nums = {45, 89, 12, 67, 23, 91, 5};

            int min = nums[0];
            int sum = 0;
            for (int n : nums) {
                if (n < min) min = n;
                sum += n;
            }
            double avg = (double) sum / nums.length;

            int aboveAvg = 0;
            for (int n : nums) {
                if (n > avg) aboveAvg++;
            }

            System.out.println("Min: " + min);
            System.out.println("Sum: " + sum);
            System.out.println("Avg: " + avg);
            System.out.println("Above avg: " + aboveAvg);
        }
    }
    ```

[← Previous: Loops](08-loops.md){ .md-button } [Next: Strings →](10-strings.md){ .md-button }
