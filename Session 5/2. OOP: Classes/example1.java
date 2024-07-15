public class example1 {

}

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }

    Public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Name updated to" + name);
    }

}

class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String make, String model, int year) {
        this.brand = make;
        this.model = model;
        this.year = year;
    }

    public void printDetails() {
        System.out.println("Make: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }

    public void startEngine() {
        System.out.println("Engine started");
    }

    public void stopEngine() {
        System.out.println("Engine stopped");
    }

    public String getBrand() {
        return brand;
    }
}