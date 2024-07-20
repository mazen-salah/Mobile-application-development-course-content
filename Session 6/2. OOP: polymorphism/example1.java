public class example1 {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];

        employees[0] = new Developer();
        employees[1] = new Manager();

        employees[0].name = "John";
        employees[0].title = "Developer";
        employees[0].address = "123 Main St";
        employees[0].salary = 100000;
        ((Developer) employees[0]).programmingLanguage = "Java";
        
        employees[1].name = "Jane";
        employees[1].title = "Manager";
        employees[1].address = "456 Main St";
        employees[1].salary = 120000;
        ((Manager) employees[1]).department = "Engineering";

        for (Employee employee : employees) {
            employee.printData();
            System.out.println();
        }
    }
}

class Employee {
    String name;
    String title;
    String address;
    double salary;

    void printData() {
        System.out.println("Name: " + name);
        System.out.println("Title: " + title);
        System.out.println("Address: " + address);
        System.out.println("Salary: " + salary);
    }
}

class Developer extends Employee {

    String programmingLanguage;

    @Override
    void printData() {
        super.printData();
        System.out.println("Programming Language: " + programmingLanguage);
    }
}

class Manager extends Employee {

    String department;

    @Override
    void printData() {
        super.printData();
        System.out.println("Department: " + department);
    }
}
