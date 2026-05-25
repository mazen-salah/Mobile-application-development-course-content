# Setters & Getters

In Java you write getters and setters explicitly. In Kotlin, every property has them generated automatically — but you can customize them.

## The default

```kotlin
class Person {
    var name: String = ""
    val age: Int = 0
}
```

This generates:

- `name`: public getter + public setter
- `age`: public getter only (val)

You access them as if they were direct fields:

```kotlin
val p = Person()
p.name = "Mazen"          // calls setter
println(p.name)           // calls getter
```

## Custom getter

```kotlin
class Person(val firstName: String, val lastName: String) {
    val fullName: String
        get() = "$firstName $lastName"

    val initials: String
        get() = "${firstName.first()}${lastName.first()}"
}

val p = Person("Mazen", "Salah")
println(p.fullName)    // Mazen Salah
println(p.initials)    // MS
```

`fullName` and `initials` are **computed properties** — no backing field, calculated on each read.

## Custom setter

```kotlin
class User {
    var email: String = ""
        set(value) {
            require(value.contains("@")) { "Invalid email" }
            field = value.lowercase()
        }
}

val u = User()
u.email = "Mazen@SummationWorks.com"
println(u.email)     // mazen@summationworks.com

u.email = "not-an-email"  // throws IllegalArgumentException
```

The special identifier `field` refers to the backing storage. You can't use `email = value` inside the setter — that would recurse infinitely.

## Private setter

```kotlin
class Counter {
    var count: Int = 0
        private set

    fun increment() { count++ }
    fun reset()     { count = 0 }
}

val c = Counter()
println(c.count)   // OK — public getter
c.count = 99       // COMPILE ERROR — setter is private
c.increment()      // OK
```

Great pattern: expose **read access** to outside code but force mutations to go through methods that enforce invariants.

## Lazy properties

For expensive computations that you only want to do once, on first access:

```kotlin
class Profile(val userId: String) {
    val avatar: String by lazy {
        println("Loading avatar from network...")
        downloadAvatar(userId)
    }
}

val p = Profile("user123")
// avatar not loaded yet
println(p.avatar)   // prints "Loading..." then the URL
println(p.avatar)   // doesn't reload — value is cached
```

`by lazy` is one of Kotlin's **delegated properties**. Common in Android for things like ViewModel and view bindings.

## lateinit

For properties that *can't* be initialized at construction but will be set before use:

```kotlin
class TestSuite {
    lateinit var db: Database

    fun setup() {
        db = Database.connect()
    }

    fun runTests() {
        db.query("...")   // works because setup() ran first
    }
}
```

Access before initialization throws `UninitializedPropertyAccessException`.

Use `lateinit` sparingly — it's a workaround for cases where the type system doesn't help. Many Android frameworks use it for things like `binding` in Fragments.

## Try it yourself

Refactor this Java to use idiomatic Kotlin properties:

```java
public class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.stock = 0;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public boolean isInStock() { return stock > 0; }

    public void restock(int qty) {
        if (qty <= 0) throw new IllegalArgumentException();
        stock += qty;
    }
}
```

??? success "Solution"
    ```kotlin
    class Product(val name: String, val price: Double) {
        var stock: Int = 0
            private set

        val isInStock: Boolean
            get() = stock > 0

        fun restock(qty: Int) {
            require(qty > 0)
            stock += qty
        }
    }
    ```

    20 lines of Java → 9 lines of Kotlin. Same behavior, all the same guarantees.

[← Previous](06-classes.md){ .md-button } [Next: Inheritance →](08-inheritance.md){ .md-button }
