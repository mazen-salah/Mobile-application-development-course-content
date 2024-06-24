import java.util.Scanner;

public class example4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter N1 and N2: ");
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        if (n1 > n2) {
            System.out.println("N1: " + n1 + "is greater than N2: " + n2);
        } else if (n1 < n2) {
            System.out.println("N1: " + n1 + "is less than N2: " + n2);
        } else {
            System.out.println("N1: " + n1 + "is equal to N2: " + n2);
        }

        sc.close();
    }
}
