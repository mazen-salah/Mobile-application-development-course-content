# OOP — Abstraction

**Abstraction** is hiding implementation details behind a clean interface. Users of your class shouldn't need to know *how* it works — only *what* it does.

When you tap "Send" on WhatsApp, you don't think about TCP packets, encryption keys, or server load balancers. The app abstracts all of that into a single button. Same idea applies to your classes.

## Two tools for abstraction

1. **Abstract classes** — partial blueprints (this lesson)
2. **Interfaces** — pure contracts (covered in Module 2)

## abstract class

An abstract class can't be instantiated directly. It's meant to be **extended**.

```java
public abstract class Shape {
    String color;

    public Shape(String color) {
        this.color = color;
    }

    // Concrete method — all subclasses inherit this as-is
    public void describe() {
        System.out.println("A " + color + " shape with area " + area());
    }

    // Abstract method — subclasses MUST implement
    public abstract double area();
}
```

```java
new Shape("red");   // COMPILE ERROR — can't instantiate abstract class
```

You can only create objects of **concrete subclasses**:

```java
public class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

Circle c = new Circle("red", 5);
c.describe();   // A red shape with area 78.539...
```

## abstract method

An abstract method is **declared without a body**:

```java
public abstract double area();
```

Every concrete subclass must `@Override` it — the compiler enforces this. If you forget, you get a compile error or your subclass becomes abstract too.

## abstract class vs interface (preview)

| | abstract class | interface |
|---|---|---|
| Can have fields | Yes (state) | Only constants |
| Can have constructors | Yes | No |
| Can have concrete methods | Yes | Yes (default methods, Java 8+) |
| Multiple inheritance | No (one parent) | Yes (many interfaces) |
| Use when... | Subclasses share state/behavior | You're defining a capability |

We'll see interfaces in depth in Module 2 (Kotlin).

## Practical example — a payment system

```java
public abstract class PaymentMethod {
    String accountId;

    public PaymentMethod(String accountId) {
        this.accountId = accountId;
    }

    public final void process(double amount) {
        System.out.println("Processing payment of $" + amount);
        if (charge(amount)) {
            sendReceipt();
        } else {
            System.out.println("Payment failed");
        }
    }

    // Each payment type charges differently
    protected abstract boolean charge(double amount);

    // Default receipt — subclasses can override if needed
    protected void sendReceipt() {
        System.out.println("Receipt emailed for account " + accountId);
    }
}

public class CreditCard extends PaymentMethod {
    public CreditCard(String accountId) { super(accountId); }

    @Override
    protected boolean charge(double amount) {
        // talk to Stripe / Adyen / etc.
        return amount <= 5000;
    }
}

public class ApplePay extends PaymentMethod {
    public ApplePay(String accountId) { super(accountId); }

    @Override
    protected boolean charge(double amount) {
        // talk to Apple's payment SDK
        return true;
    }
}

public class Checkout {
    public static void main(String[] args) {
        PaymentMethod[] methods = {
            new CreditCard("**** 1234"),
            new ApplePay("user@example.com")
        };
        for (PaymentMethod m : methods) {
            m.process(99.99);
        }
    }
}
```

## The "Template Method" pattern

Notice that `process()` in `PaymentMethod` defines the **algorithm**:

1. Log the attempt
2. Try to charge (varies per subclass)
3. Send receipt if successful

This is called the **Template Method** pattern — the abstract class fixes the order of operations, but each subclass fills in the variable parts. You'll see this all over Android (`Activity.onCreate()`, `Service.onStartCommand()`).

## When to choose abstraction

Use an abstract class when:

- Several classes share **state and behavior**, but also have **mandatory variations**
- You want to enforce "subclasses must implement X"
- You want to define a fixed algorithm with replaceable steps

## Try it yourself

Build:

- `abstract class Employee` with field `name` and abstract method `monthlySalary()`
- `Manager extends Employee` — fixed $8000
- `HourlyWorker extends Employee` — `hourlyRate * hoursPerMonth`
- `Salesperson extends Employee` — `baseSalary + 0.10 * monthlySales`

Print monthly salary for one of each in `main`.

??? success "Solution"
    ```java
    public abstract class Employee {
        String name;
        public Employee(String name) { this.name = name; }
        public abstract double monthlySalary();
    }

    public class Manager extends Employee {
        public Manager(String name) { super(name); }
        @Override public double monthlySalary() { return 8000; }
    }

    public class HourlyWorker extends Employee {
        double rate, hours;
        public HourlyWorker(String name, double rate, double hours) {
            super(name); this.rate = rate; this.hours = hours;
        }
        @Override public double monthlySalary() { return rate * hours; }
    }

    public class Salesperson extends Employee {
        double base, sales;
        public Salesperson(String name, double base, double sales) {
            super(name); this.base = base; this.sales = sales;
        }
        @Override public double monthlySalary() { return base + 0.10 * sales; }
    }

    public class Payroll {
        public static void main(String[] args) {
            Employee[] team = {
                new Manager("Alice"),
                new HourlyWorker("Bob", 20, 160),
                new Salesperson("Carol", 2000, 35000),
            };
            for (Employee e : team) {
                System.out.printf("%s: $%.2f%n", e.name, e.monthlySalary());
            }
        }
    }
    ```

[← Previous: Polymorphism](14-oop-polymorphism.md){ .md-button } [Next: Encapsulation →](16-encapsulation.md){ .md-button }
