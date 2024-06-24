import java.util.Scanner;

public class example5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your degree: ");
        Double degree = sc.nextDouble();
        char grade;

        if (degree >= 90) {
            grade = 'A';
        } else if (degree >= 80) {
            grade = 'B';
        } else if (degree >= 70) {
            grade = 'C';
        } else if (degree >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Grade: " + grade);

        sc.close();
    }

}
