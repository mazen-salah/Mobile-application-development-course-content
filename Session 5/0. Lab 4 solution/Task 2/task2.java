import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String[] words = input.split(" ");
        for (String word : words) {
            System.out.println(word);
        }

        scanner.close();
    }
}
