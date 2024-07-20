public class example1 {
    public static void main(String[] args) {
        Developer developer = new Developer();
        developer.name = "John";
        developer.title = "Software Developer";
        developer.salary = 1000;
        developer.experience = 5;
        developer.emPloyeeData();
    }
}

class Employee {
    String name;
    String title;
    double salary;

    void emPloyeeData() {
        System.out.println("Name: " + name);
        System.out.println("Title: " + title);
        System.out.println("Salary: " + salary);
    }
}

class Developer extends Employee {
    int experience;

    @Override
    void emPloyeeData() {
        super.emPloyeeData();
        System.out.println("Experience: " + experience);
    }

}