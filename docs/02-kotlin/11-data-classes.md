# Data Classes

If your class exists primarily to hold data (a POJO in Java terms), Kotlin gives you a one-keyword shortcut: `data class`.

## The one-liner

```kotlin
data class User(val id: Int, val name: String, val email: String)
```

That single line generates:

- Primary constructor + properties
- `equals()` — compares all properties
- `hashCode()` — derived from properties
- `toString()` — `"User(id=1, name=Mazen, email=mazen@example.com)"`
- `copy()` — clone with optional changes
- `componentN()` functions — for destructuring

## Equality

```kotlin
val u1 = User(1, "Mazen", "mazen@example.com")
val u2 = User(1, "Mazen", "mazen@example.com")

println(u1 == u2)    // true — data class compares by value
println(u1 === u2)   // false — different instances in memory

// For a regular class, == would be false unless you wrote equals()
```

`==` in Kotlin calls `equals()`. `===` is reference equality (rarely needed).

## copy()

```kotlin
val u = User(1, "Mazen", "mazen@example.com")
val updated = u.copy(email = "new@example.com")

println(updated)   // User(id=1, name=Mazen, email=new@example.com)
println(u)         // User(id=1, name=Mazen, email=mazen@example.com) — unchanged
```

This is the **immutable update** pattern — `u` is never modified. Used heavily in state management (Redux, BLoC).

## Destructuring

```kotlin
val u = User(1, "Mazen", "mazen@example.com")
val (id, name, email) = u   // unpacks into three variables

// Useful in loops:
val users = listOf(
    User(1, "Mazen", "m@x.com"),
    User(2, "Sara", "s@x.com")
)
for ((id, name, _) in users) {
    println("$id → $name")
}
```

`_` skips a component you don't need.

## When NOT to use data class

Data classes are designed for **immutable data containers**. Avoid:

- Classes with complex behavior (use regular `class`)
- Classes that inherit from non-data classes
- Classes where equality should be reference-based

## Comparison to Java

```java
// Java — 30+ lines of boilerplate
public final class User {
    private final int id;
    private final String name;
    private final String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User u = (User) o;
        return id == u.id && Objects.equals(name, u.name) && Objects.equals(email, u.email);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, email); }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}

// Kotlin — 1 line:
// data class User(val id: Int, val name: String, val email: String)
```

(Java 14+ has `record` which gets closer, but Kotlin's `data class` is still cleaner and more flexible.)

## Real-world example — modeling a domain

```kotlin
data class Address(
    val street: String,
    val city: String,
    val country: String
)

data class Customer(
    val id: Int,
    val name: String,
    val email: String,
    val billingAddress: Address,
    val shippingAddress: Address? = null
)

data class Order(
    val id: Int,
    val customer: Customer,
    val items: List<LineItem>,
    val total: Double,
    val status: OrderStatus = OrderStatus.PENDING
)

data class LineItem(
    val productId: Int,
    val quantity: Int,
    val unitPrice: Double
) {
    val subtotal: Double get() = quantity * unitPrice
}

enum class OrderStatus { PENDING, SHIPPED, DELIVERED, CANCELLED }
```

This is the heart of any backend or app — modeling the domain as immutable data.

## Try it yourself

Model a music library with data classes:

- `Artist(name: String, country: String)`
- `Album(title: String, artist: Artist, year: Int)`
- `Song(title: String, album: Album, durationSec: Int)` with a computed `durationFormatted` property like `"3:45"`

Create a few songs and print them. Then create a copy of one song with a different title.

??? success "Solution"
    ```kotlin
    data class Artist(val name: String, val country: String)
    data class Album(val title: String, val artist: Artist, val year: Int)
    data class Song(val title: String, val album: Album, val durationSec: Int) {
        val durationFormatted: String
            get() = "${durationSec / 60}:${(durationSec % 60).toString().padStart(2, '0')}"
    }

    fun main() {
        val artist = Artist("Radiohead", "UK")
        val album = Album("OK Computer", artist, 1997)
        val s1 = Song("Paranoid Android", album, 387)
        val s2 = Song("Karma Police", album, 261)

        println(s1)
        println("Duration: ${s1.durationFormatted}")   // 6:27

        val edit = s1.copy(title = "Paranoid Android (Live)")
        println(edit)
    }
    ```

[← Previous](10-abstract-classes.md){ .md-button } [Next: Lambdas & Collections →](12-lambdas-collections.md){ .md-button }
