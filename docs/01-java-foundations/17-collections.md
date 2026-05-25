# Collections

Arrays are fixed-size and lack convenience methods. **Collections** are dynamic data structures — they grow, shrink, sort, filter. You'll use these constantly in mobile dev.

## The Big Three

| Collection | Stores | Order? | Duplicates? | Typical use |
|---|---|---|---|---|
| `ArrayList<E>` | Anything | Insertion order | Yes | General-purpose list |
| `HashMap<K,V>` | Key → Value pairs | No | Keys unique | Lookups by ID |
| `HashSet<E>` | Anything | No | No | "Have I seen this?" checks |

`<E>` and `<K,V>` are **generics** — placeholders for the type you put in. Examples below.

## ArrayList

```java
import java.util.ArrayList;
import java.util.List;

List<String> names = new ArrayList<>();
names.add("Mazen");
names.add("Sara");
names.add("Ahmed");

System.out.println(names.size());      // 3
System.out.println(names.get(0));      // Mazen
System.out.println(names.contains("Sara"));  // true

names.remove("Ahmed");
names.set(0, "Mazen Tamer");

for (String n : names) {
    System.out.println(n);
}
```

Why `List<String>` not `ArrayList<String>`? Programming to the **interface** (`List`) lets you swap the implementation (`ArrayList`, `LinkedList`) without changing other code.

### Common ArrayList operations

```java
List<Integer> nums = new ArrayList<>();
nums.add(5); nums.add(2); nums.add(8); nums.add(1);

import java.util.Collections;
Collections.sort(nums);              // [1, 2, 5, 8]
Collections.reverse(nums);           // [8, 5, 2, 1]
int max = Collections.max(nums);     // 8
int min = Collections.min(nums);     // 1

nums.clear();                        // empty
boolean empty = nums.isEmpty();      // true
```

## HashMap

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> ages = new HashMap<>();
ages.put("Mazen", 25);
ages.put("Sara", 30);
ages.put("Ahmed", 22);

System.out.println(ages.get("Sara"));        // 30
System.out.println(ages.containsKey("Bob")); // false
System.out.println(ages.size());             // 3

ages.put("Mazen", 26);   // updates (keys are unique)
ages.remove("Ahmed");

// Iterate
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Just keys or just values
for (String name : ages.keySet())   { ... }
for (int age : ages.values())       { ... }
```

`get()` returns `null` for missing keys — guard with `containsKey()` or `getOrDefault()`:

```java
int age = ages.getOrDefault("Unknown", 0);
```

## HashSet

```java
import java.util.HashSet;
import java.util.Set;

Set<String> seen = new HashSet<>();
seen.add("user_1");
seen.add("user_2");
seen.add("user_1");   // ignored — duplicates not stored

System.out.println(seen.size());          // 2
System.out.println(seen.contains("user_1"));  // true
```

Use Sets when you only care **whether** something is present, not in what order or how many times.

## Picking the right one

```
Need to keep order, allow duplicates, access by index? → ArrayList
Need fast lookup by some key?                          → HashMap
Need to check "is this in the set?" quickly?           → HashSet
```

## Time complexity (quick reference)

| Operation | ArrayList | HashMap | HashSet |
|---|---|---|---|
| Add to end | O(1) | O(1) | O(1) |
| Get by index | O(1) | — | — |
| Get by key | — | O(1) | — |
| Contains | O(n) | O(1) | O(1) |
| Remove | O(n) | O(1) | O(1) |

You don't need to memorize this, but know that **lookup in a list is slow** for large data — use HashMap if you'll be searching.

## Worked example — vote counter

```java
import java.util.*;

public class VoteCounter {
    public static void main(String[] args) {
        String[] votes = {
            "pizza", "burger", "pizza", "salad",
            "pizza", "burger", "tacos", "salad", "pizza"
        };

        Map<String, Integer> tally = new HashMap<>();
        for (String vote : votes) {
            tally.put(vote, tally.getOrDefault(vote, 0) + 1);
        }

        // Find winner
        String winner = null;
        int max = 0;
        for (Map.Entry<String, Integer> e : tally.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                winner = e.getKey();
            }
        }

        System.out.println("Tally: " + tally);
        System.out.println("Winner: " + winner + " with " + max + " votes");
    }
}
```

## Generics

`<E>` is a **type parameter**. It lets the compiler check what goes in and out:

```java
List<String> names = new ArrayList<>();
names.add(42);   // COMPILE ERROR — not a String
String n = names.get(0);  // no cast needed
```

Without generics (Java 4 style), `List` could hold anything and `get()` returned `Object`, so you had to cast. Modern Java uses generics everywhere.

## Try it yourself

Build a simple address book:

- Use a `HashMap<String, String>` mapping name → phone number
- Add 5 contacts
- Print them all
- Look up one
- Remove one
- Print final count

??? success "Solution"
    ```java
    import java.util.*;

    public class AddressBook {
        public static void main(String[] args) {
            Map<String, String> book = new HashMap<>();
            book.put("Mazen", "+201010112468");
            book.put("Sara",  "+201234567890");
            book.put("Ahmed", "+201111222333");
            book.put("Lina",  "+201555666777");
            book.put("Omar",  "+201888999000");

            for (Map.Entry<String, String> e : book.entrySet()) {
                System.out.println(e.getKey() + " — " + e.getValue());
            }

            System.out.println("Sara's number: " + book.get("Sara"));

            book.remove("Omar");
            System.out.println("Final count: " + book.size());  // 4
        }
    }
    ```

[← Previous: Encapsulation](16-encapsulation.md){ .md-button } [Next: Exceptions →](18-exceptions.md){ .md-button }
