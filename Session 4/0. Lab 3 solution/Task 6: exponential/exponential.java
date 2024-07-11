import java.util.Scanner;

public class exponential {
     public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the power you want to raise the number to: ");
            int n = sc.nextInt();
            System.out.println("Enter the number you want to raise to the power of " + n + ": ");
            int x = sc.nextInt();
            int exp = 1;
            for (int i = 1; i <= n; i++) {
                exp *= x;
            }
            System.out.println(exp);
            sc.close();
     }
}
