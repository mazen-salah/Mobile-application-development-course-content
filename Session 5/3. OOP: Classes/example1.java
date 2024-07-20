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
