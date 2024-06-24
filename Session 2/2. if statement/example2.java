import java.util.Scanner;

public class example2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double degree = sc.nextDouble();

        if (degree >= 50) {
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }

        sc.close();
    }
}
