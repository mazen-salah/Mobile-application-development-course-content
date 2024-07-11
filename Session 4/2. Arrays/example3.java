public class example3 {
    public static void main(String[] args) {
        String[] names = { "John", "Doe", "Jane", "Doe", "Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson" };
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("Johnson")) {
                System.out.println("Found at index: " + i);
            }
        }
    }
}
