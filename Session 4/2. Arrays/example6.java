public class example6 {
    public static void main(String[] args) {
        int[] numbers = { 12, 34, 56, 78, 90, 32, 54, 76, 98, 10 };
        int inverted[] = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            inverted[numbers.length - i - 1] = numbers[i];
        }

        System.out.print("Inverted array: ");
        for (int i = 0; i < inverted.length; i++) {
            System.out.print(inverted[i] + " ");
        }
        System.out.println();
    }
}
