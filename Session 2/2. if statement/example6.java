import java.util.Scanner;

public class example6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Double marks = sc.nextDouble();
        boolean pass = false;
        char grade ;

        if (marks >= 50) {
            pass = true;
            if (marks >= 90) {
                grade = 'A';
            } else if (marks >= 80) {
                grade = 'B';
            } else if (marks >= 70) {
                grade = 'C';
            } else if (marks >= 60) {
                grade = 'D';
            } else {
                grade = 'E';
            }
        } else {
            grade = 'F';
        }

        if (pass) {
            System.out.println("Congratulations! You passed with grade " + grade);
        } else {
            System.out.println("Sorry, you failed.");
        }

        sc.close();
    }
}
