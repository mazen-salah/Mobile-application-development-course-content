import java.util.Scanner;

public class whileLoop1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        
        while (!input.equals("stop")) {
            System.out.println(input);
            System.out.print("Input: ");
            input = sc.next().toLowerCase();
        }

        sc.close();
    }
}