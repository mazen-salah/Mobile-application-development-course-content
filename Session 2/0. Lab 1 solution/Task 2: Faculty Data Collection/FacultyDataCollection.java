import java.util.Scanner;

public class FacultyDataCollection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String facultyName;
        char lastGrade;
        float lastDegree;
        int subjectsCount;
        boolean passed;

        System.out.println("Enter faculty name: ");
        facultyName = sc.nextLine();
        System.out.println("Faculty name: " + facultyName);

        System.out.println("Enter last grade: ");
        lastGrade = sc.next().charAt(0);
        System.out.println("Last grade: " + lastGrade);

        System.out.println("Enter last degree: ");
        lastDegree = sc.nextFloat();
        System.out.println("Last degree: " + lastDegree);

        System.out.println("Enter number of subjects: ");
        subjectsCount = sc.nextInt();
        System.out.println("Number of subjects: " + subjectsCount);

        System.out.println("Enter passed status: ");
        passed = sc.nextBoolean();
        System.out.println("Passed status: " + passed);

        sc.close();
    }
}