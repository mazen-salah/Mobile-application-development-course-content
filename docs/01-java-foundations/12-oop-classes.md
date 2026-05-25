# OOP — Classes & Objects

Everything mobile development touches is object-oriented. **This is the most important lesson in the entire module.** Re-read it until it clicks.

## The mental model

A **class** is a blueprint. An **object** is a house built from that blueprint.

```
Class:  Phone (the design)
Objects: Pixel 8, iPhone 15, Galaxy S24 (specific phones)
```

A class describes:

- **Fields** — the data each object holds (brand, model, price)
- **Methods** — what each object can do (ring, power on, take a photo)

## Defining a class

```java title="Phone.java"
public class Phone {

    // Fields (also called attributes or properties)
    String brand;
    String model;
    double price;
    boolean isOn;

    // Method
    public void powerOn() {
        isOn = true;
        System.out.println(brand + " " + model + " is now on");
    }

    public void powerOff() {
        isOn = false;
        System.out.println(brand + " " + model + " is now off");
    }

    public void describe() {
        System.out.println(brand + " " + model + " — $" + price);
    }
}
```

## Creating objects

```java title="PhoneDemo.java"
public class PhoneDemo {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        p1.brand = "Google";
        p1.model = "Pixel 8";
        p1.price = 699.0;

        Phone p2 = new Phone();
        p2.brand = "Apple";
        p2.model = "iPhone 15";
        p2.price = 999.0;

        p1.describe();   // Google Pixel 8 — $699.0
        p2.describe();   // Apple iPhone 15 — $999.0

        p1.powerOn();    // Google Pixel 8 is now on
    }
}
```

`new Phone()` creates a fresh object in memory. `p1` and `p2` are independent — changing `p1.brand` doesn't affect `p2`.

## Constructors

Setting fields one by one is tedious. **Constructors** let you initialize an object at creation time:

```java
public class Phone {
    String brand;
    String model;
    double price;

    // Constructor — same name as class, no return type
    public Phone(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public void describe() {
        System.out.println(brand + " " + model + " — $" + price);
    }
}
```

`this.brand = brand` means "set this object's `brand` field to the parameter `brand`." `this` refers to the current object.

```java
Phone p = new Phone("Google", "Pixel 8", 699.0);
p.describe();  // Google Pixel 8 — $699.0
```

## Multiple constructors (overloading)

```java
public class Phone {
    String brand;
    String model;
    double price;

    public Phone(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Phone(String brand, String model) {
        this(brand, model, 0.0);  // delegate to the other constructor
    }

    public Phone() {
        this("Unknown", "Unknown", 0.0);
    }
}
```

## static vs instance

| Kind | Belongs to | How to call |
|---|---|---|
| Instance field/method | Each object separately | `phone.describe()` |
| Static field/method | The class itself | `Phone.maxBattery` |

```java
public class Phone {
    static int totalPhonesCreated = 0;  // shared across all phones

    String brand;

    public Phone(String brand) {
        this.brand = brand;
        totalPhonesCreated++;
    }
}

new Phone("Google");
new Phone("Apple");
System.out.println(Phone.totalPhonesCreated);  // 2
```

`main` is `static` because it's called by the JVM without any object — there's no instance to call it on.

## Full worked example — a shopping cart

```java title="Cart.java"
public class Cart {
    String[] items = new String[10];
    double[] prices = new double[10];
    int count = 0;

    public void add(String item, double price) {
        if (count >= 10) {
            System.out.println("Cart full");
            return;
        }
        items[count] = item;
        prices[count] = price;
        count++;
    }

    public double total() {
        double sum = 0;
        for (int i = 0; i < count; i++) sum += prices[i];
        return sum;
    }

    public void printReceipt() {
        for (int i = 0; i < count; i++) {
            System.out.printf("%-15s %.2f%n", items[i], prices[i]);
        }
        System.out.println("------------------");
        System.out.printf("%-15s %.2f%n", "TOTAL", total());
    }

    public static void main(String[] args) {
        Cart c = new Cart();
        c.add("Headphones", 49.99);
        c.add("Charger", 19.99);
        c.add("Case", 9.99);
        c.printReceipt();
    }
}
```

## Try it yourself

Build a `Book` class with `title`, `author`, `pages`, and `price`. Give it:

- A constructor taking all four
- A method `isLong()` returning true if pages > 300
- A method `describe()` printing a one-line summary
- A `main` that creates three books and prints their summaries

??? success "Solution"
    ```java
    public class Book {
        String title;
        String author;
        int pages;
        double price;

        public Book(String title, String author, int pages, double price) {
            this.title = title;
            this.author = author;
            this.pages = pages;
            this.price = price;
        }

        public boolean isLong() {
            return pages > 300;
        }

        public void describe() {
            System.out.printf("'%s' by %s — %d pages, $%.2f%n",
                title, author, pages, price);
        }

        public static void main(String[] args) {
            Book[] books = {
                new Book("Clean Code", "Bob Martin", 464, 35.0),
                new Book("Atomic Habits", "James Clear", 320, 18.0),
                new Book("The Lean Startup", "Eric Ries", 336, 25.0),
            };
            for (Book b : books) b.describe();
        }
    }
    ```

[← Previous: Methods](11-methods.md){ .md-button } [Next: Inheritance →](13-oop-inheritance.md){ .md-button }
