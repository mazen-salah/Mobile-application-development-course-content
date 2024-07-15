import java.util.Scanner;

public class example1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get two words from the user
        System.out.print("Enter the first word: ");
        String word1 = scanner.next();
        System.out.print("Enter the second word: ");
        String word2 = scanner.next();

        // Concatenate the words with "hello java"
        String sentence = "hello " + word1 + " " + word2;

        // Delete "java" from the sentence
        sentence = sentence.replace("java", "");

        // Check if the sentence contains "java"
        boolean containsJava = sentence.contains("java");

        // Get the length of the sentence
        int length = sentence.length();

        // Get the character at index 3
        char charAtIndex3 = sentence.charAt(3);

        // Print the results
        System.out.println("Modified sentence: " + sentence);
        System.out.println("Contains 'java': " + containsJava);
        System.out.println("Length of sentence: " + length);
        System.out.println("Character at index 3: " + charAtIndex3);
        
        scanner.close();
    }

}
