# Setters and Getters in Kotlin

In Kotlin, setters and getters are automatically generated for public properties. However, you can also customize the behavior of these accessors by defining them explicitly.

Let's take a look at an example:

```kotlin
class Employee() {
    var age: Int = 0
        get() = field * 2
        set(value) {
            field = value
        }
}

fun main() {
    val employee = Employee()
    employee.age = 30
    println(employee.age)
}
```

In this example, we have a `Employee` class with a property called `age`. The `age` property is defined as an `Int` and has an initial value of 0.

The `get()` accessor is defined for the `age` property, which returns the value of `field` (the backing field) multiplied by 2. This means that whenever we access the `age` property, it will return the value of `field` multiplied by 2.

The `set()` accessor is also defined for the `age` property, which sets the value of `field` to the provided `value`. This means that whenever we assign a value to the `age` property, it will update the value of `field` accordingly.

In the `main()` function, we create an instance of the `Employee` class and assign a value of 30 to the `age` property using the setter. Finally, we print the value of the `age` property using the getter.

When we run this code, it will output `60`, which is the result of multiplying the assigned value by 2.

By defining custom setters and getters, you can add additional logic or transformations to your properties, providing more control over their behavior.
