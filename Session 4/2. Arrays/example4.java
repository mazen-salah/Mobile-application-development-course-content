public class example4 {
    public static void main(String[] args) {
        int[] numbers = { 12, 34, 56, 78, 90, 32, 54, 76, 98, 10 };
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.print("Sorted array: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]+ " ");
        }
        System.out.println();
    }
}
