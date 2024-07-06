import java.util.Scanner;

public class forLoop2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the first number: ");
        int num1 = sc.nextInt();
        System.out.println("Enter the second number: ");
        int num2 = sc.nextInt();
        System.out.println();

        // Swap the numbers if the second number is smaller than the first number
        if (num2 < num1) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        // If both numbers are equal, print a message and exit the program
        else if (num1 == num2) {
            System.out.println("Both numbers are equal.");
            sc.close();
            return;
        }
        
        // Print the range of numbers between num1 and num2 (inclusive)
        for (int i = num1; i <= num2; i++) {
            System.out.println(i);
        }
        
        sc.close();
    }
}
