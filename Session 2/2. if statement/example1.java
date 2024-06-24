import java.util.Scanner;

public class example1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a number: ");
        int x = sc.nextInt();

        if (x > 5) {
            System.out.println("Success!");
        } 

        sc.close();
    }
}
