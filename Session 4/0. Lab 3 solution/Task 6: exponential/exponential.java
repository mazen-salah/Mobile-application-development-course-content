import java.util.Scanner;

public class exponential {
     public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int x = sc.nextInt();
            int exp = 1;
            for (int i = 1; i <= n; i++) {
                exp *= x;
            }
            System.out.println(exp);
            sc.close();
     }
}
