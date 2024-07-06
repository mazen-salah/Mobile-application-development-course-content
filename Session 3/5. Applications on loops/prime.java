import java.util.Scanner;

public class prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = sc.nextInt();

        boolean isPrime = true;
        // for (int i = 2; i <= n; i++) {
        // for (int i = 2; i <= n/2; i++) {
        // for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        System.out.print(n + " is ");
        if (isPrime) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
        sc.close();
    }
}
