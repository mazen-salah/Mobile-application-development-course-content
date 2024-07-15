public class example1 {

}

/**
 * The Employee class represents an employee with a name, age, and salary.
 */
class Employee {
    private String name;
    private int age;
    private double salary;

    /**
     * Constructs an Employee object with the specified name, age, and salary.
     *
     * @param name   the name of the employee
     * @param age    the age of the employee
     * @param salary the salary of the employee
     */
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    /**
     * Prints the details of the employee.
     */
    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name the new name of the employee
     */
    public void setName(String name) {
        this.name = name;
        System.out.println("Name updated to " + name);
    }
}

/**
 * Represents a car with a specific brand, model, and year.
 */
class Car {
    private String brand;
    private String model;
    private int year;

    /**
     * Constructs a car object with the given brand, model, and year.
     * 
     * @param brand the brand of the car
     * @param model the model of the car
     * @param year the year of the car
     */
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    /**
     * Prints the details of the car, including the brand, model, and year.
     */
    public void printDetails() {
        System.out.println("Make: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }

    /**
     * Starts the car's engine.
     */
    public void startEngine() {
        System.out.println("Engine started");
    }

    /**
     * Stops the car's engine.
     */
    public void stopEngine() {
        System.out.println("Engine stopped");
    }

    /**
     * Returns the brand of the car.
     * 
     * @return the brand of the car
     */
    public String getBrand() {
        return brand;
    }
}