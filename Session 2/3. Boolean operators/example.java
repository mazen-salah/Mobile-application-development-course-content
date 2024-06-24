public class example {
    public static void main(String[] args) {
        int x = 5;
        int y = 10;
        boolean result;

        // Example of using boolean operators
        result = (x > 3) && (y < 15); // Logical AND operator
        System.out.println("Result of (x > 3) && (y < 15): " + result);

        result = (x < 2) || (y > 20); // Logical OR operator
        System.out.println("Result of (x < 2) || (y > 20): " + result);

        result = !(x == y); // Logical NOT operator
        System.out.println("Result of !(x == y): " + result);
    }
}
