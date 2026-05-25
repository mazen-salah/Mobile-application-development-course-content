# OOP — Polymorphism

**Polymorphism** ("many forms") means a single reference type can refer to objects of different concrete classes — and call the right method automatically. It's how you write code that works with future classes you haven't written yet.

## The setup

```java
public class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Cow extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Moo");
    }
}
```

## Now watch this

```java
Animal[] zoo = { new Dog(), new Cat(), new Cow(), new Dog() };

for (Animal a : zoo) {
    a.makeSound();
}
// Woof
// Meow
// Moo
// Woof
```

The variable `a` is typed as `Animal`, but the JVM looks at the **actual object** at runtime and calls the right method. That's polymorphism.

## Why this is huge

The for-loop **doesn't care** what kinds of animals are in the array. You can add `Snake`, `Bird`, `Elephant` later without touching the loop:

```java
public class Snake extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Hiss");
    }
}
```

Just add `new Snake()` to the zoo. The same loop runs.

This is the **Open/Closed Principle** — code should be *open* to extension (new types) but *closed* to modification (you didn't change existing code).

## Upcasting and downcasting

```java
Animal a = new Dog();   // upcast — automatic, always safe
// a.makeSound() → "Woof" (polymorphism kicks in)
// a.bark()      → COMPILE ERROR — Animal has no bark()

Dog d = (Dog) a;        // downcast — explicit, can fail at runtime
d.bark();               // works
```

Downcasting is risky:

```java
Animal a = new Cat();
Dog d = (Dog) a;        // compiles, but throws ClassCastException at runtime
```

To check safely, use `instanceof`:

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.bark();
}

// Modern Java 16+ — pattern matching:
if (a instanceof Dog d) {
    d.bark();
}
```

## Real-world usage

You'll see polymorphism everywhere in Android and Flutter:

- A `RecyclerView.Adapter` returns different `ViewHolder` subclasses depending on item type
- A `View` reference might point to `Button`, `TextView`, `ImageView` — all subclasses of `View`
- In Flutter, every UI element is a `Widget` — buttons, text, lists are all subclasses

## Method overriding rules

When overriding:

- Same method name
- Same parameter list
- Return type must be the same or a subtype
- Access must be the same or wider (not narrower)
- Cannot throw broader checked exceptions

```java
@Override
public String makeSound() { ... }  // change return type to String
```

The `@Override` annotation makes the compiler check all these for you. **Always use it.**

## Static vs dynamic dispatch

| Type | When the method is chosen | Example |
|---|---|---|
| **Static** | At compile time | Overloaded methods (`sum(int)` vs `sum(double)`) |
| **Dynamic** | At runtime, based on object's actual type | Overridden methods (polymorphism) |

This is why polymorphism feels magical — the JVM does the lookup *as the program runs*, not when it's compiled.

## Worked example — a drawing app

```java
public abstract class Shape {
    public abstract double area();
}

public class Circle extends Shape {
    double radius;
    public Circle(double r) { this.radius = r; }
    @Override
    public double area() { return Math.PI * radius * radius; }
}

public class Square extends Shape {
    double side;
    public Square(double s) { this.side = s; }
    @Override
    public double area() { return side * side; }
}

public class Triangle extends Shape {
    double base, height;
    public Triangle(double b, double h) { this.base = b; this.height = h; }
    @Override
    public double area() { return 0.5 * base * height; }
}

public class App {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(5),
            new Square(4),
            new Triangle(3, 6)
        };

        double totalArea = 0;
        for (Shape s : shapes) {
            totalArea += s.area();  // calls the right area() automatically
        }
        System.out.println("Total area: " + totalArea);
    }
}
```

## Try it yourself

Create a `BankAccount` base class with method `monthlyInterest()` returning 0. Create `SavingsAccount`, `CheckingAccount`, `CDAccount` subclasses, each overriding `monthlyInterest()` with their own formula. Then in `main`, create an array of mixed accounts and print each one's interest using polymorphism.

??? success "Solution"
    ```java
    public class BankAccount {
        double balance;
        public BankAccount(double b) { balance = b; }
        public double monthlyInterest() { return 0; }
    }

    public class SavingsAccount extends BankAccount {
        public SavingsAccount(double b) { super(b); }
        @Override public double monthlyInterest() { return balance * 0.003; }
    }

    public class CheckingAccount extends BankAccount {
        public CheckingAccount(double b) { super(b); }
        @Override public double monthlyInterest() { return balance * 0.001; }
    }

    public class CDAccount extends BankAccount {
        public CDAccount(double b) { super(b); }
        @Override public double monthlyInterest() { return balance * 0.005; }
    }

    public class Bank {
        public static void main(String[] args) {
            BankAccount[] accounts = {
                new SavingsAccount(10000),
                new CheckingAccount(2500),
                new CDAccount(50000),
            };
            for (BankAccount a : accounts) {
                System.out.printf("Interest: $%.2f%n", a.monthlyInterest());
            }
        }
    }
    ```

[← Previous: Inheritance](13-oop-inheritance.md){ .md-button } [Next: Abstraction →](15-oop-abstraction.md){ .md-button }
