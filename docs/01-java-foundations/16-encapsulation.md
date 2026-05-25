# Encapsulation

**Encapsulation** means hiding an object's internal state and forcing all access to go through controlled methods. It's the fourth pillar of OOP (along with inheritance, polymorphism, abstraction).

## The problem with public fields

```java
public class BankAccount {
    public double balance;
}

BankAccount a = new BankAccount();
a.balance = -1000000;   // legal, but probably bad
```

There's no protection — anyone can set `balance` to anything. Including invalid values.

## Access modifiers

Java has four:

| Modifier | Same class | Same package | Subclasses | Anywhere |
|---|---|---|---|---|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| *(no modifier)* — "package-private" | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

**Rule of thumb:** make fields `private` and methods `public`. Open things up only when you have a reason.

## Encapsulated version

```java
public class BankAccount {
    private double balance;
    private String owner;

    public BankAccount(String owner, double initialDeposit) {
        if (initialDeposit < 0) throw new IllegalArgumentException("No negative deposit");
        this.owner = owner;
        this.balance = initialDeposit;
    }

    // "Getter" — read access
    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    // "Setter" with validation
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
    }
}
```

Now `balance` is impossible to corrupt from outside.

```java
BankAccount a = new BankAccount("Mazen", 1000);
a.balance = -999;          // COMPILE ERROR — balance is private
a.deposit(500);            // OK → 1500
a.withdraw(2000);          // throws IllegalStateException
System.out.println(a.getBalance());  // 1500
```

## Getters and setters (the IDE will generate them)

The pattern is so common that every Java IDE generates them with one keyboard shortcut:

- **IntelliJ:** `Alt+Insert` → Getters and Setters
- **VS Code:** right-click → Source Action → Generate Getters/Setters
- **Eclipse:** right-click → Source → Generate Getters and Setters

But know when NOT to generate them. A field with no setter is **immutable from outside** — and immutable data is much easier to reason about.

## "Just generate getters and setters for every field" is wrong

That defeats encapsulation. Ask yourself for each field:

- Should outside code be able to **read** this? → add a getter
- Should outside code be able to **write** this directly? → add a setter, possibly with validation
- Often the answer is "neither directly" — expose an action instead (`deposit()` is better than `setBalance()`)

## Constants

Encapsulation also helps with constants:

```java
public class Math2 {
    public static final double GOLDEN_RATIO = 1.618033988749895;
}

System.out.println(Math2.GOLDEN_RATIO);
Math2.GOLDEN_RATIO = 2;   // COMPILE ERROR — final
```

`final` prevents reassignment; `public static` exposes it via the class name without needing an instance.

## Worked example — a simple cart with validation

```java
public class Cart {
    private final String userId;
    private double total;
    private int itemCount;

    public Cart(String userId) {
        this.userId = userId;
        this.total = 0;
        this.itemCount = 0;
    }

    public void addItem(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (itemCount >= 100) throw new IllegalStateException("Cart is full");
        total += price;
        itemCount++;
    }

    public void clear() {
        total = 0;
        itemCount = 0;
    }

    // Getters only — no direct setters
    public String getUserId() { return userId; }
    public double getTotal()  { return total; }
    public int getItemCount() { return itemCount; }
}
```

Notice `userId` is `final` — it's set once in the constructor and can never change. Strong guarantee.

## The JavaBean convention

A class is called a "JavaBean" if it:

- Is `public`
- Has a no-arg constructor
- Has `private` fields
- Exposes each field via `getX()` and `setX()`

Lots of Android (and especially Spring) code expects JavaBeans. You'll see this pattern constantly.

## Try it yourself

Refactor your `Book` class from lesson 12 to:

- Make all fields `private` and `final`
- Add getters for each
- Validate inputs in the constructor (pages > 0, price >= 0)

??? success "Solution"
    ```java
    public class Book {
        private final String title;
        private final String author;
        private final int pages;
        private final double price;

        public Book(String title, String author, int pages, double price) {
            if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
            if (pages <= 0) throw new IllegalArgumentException("Pages must be > 0");
            if (price < 0)  throw new IllegalArgumentException("Price cannot be negative");
            this.title = title;
            this.author = author;
            this.pages = pages;
            this.price = price;
        }

        public String getTitle()  { return title; }
        public String getAuthor() { return author; }
        public int getPages()     { return pages; }
        public double getPrice()  { return price; }
    }
    ```

[← Previous: Abstraction](15-oop-abstraction.md){ .md-button } [Next: Collections →](17-collections.md){ .md-button }
