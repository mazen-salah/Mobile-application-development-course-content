import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the first array: ");
        int size1 = scanner.nextInt();

        int[] array1 = new int[size1];
        System.out.println("Enter the elements of the first array:");
        for (int i = 0; i < size1; i++) {
            array1[i] = scanner.nextInt();
        }

        System.out.print("Enter the size of the second array: ");
        int size2 = scanner.nextInt();

        int[] array2 = new int[size2];
        System.out.println("Enter the elements of the second array:");
        for (int i = 0; i < size2; i++) {
            array2[i] = scanner.nextInt();
        }

        int[] concatenatedArray = new int[size1 + size2];
        System.arraycopy(array1, 0, concatenatedArray, 0, size1);
        System.arraycopy(array2, 0, concatenatedArray, size1, size2);

        System.out.println("Concatenated array:");
        for (int i = 0; i < concatenatedArray.length; i++) {
            System.out.print(concatenatedArray[i] + " ");
        }
        scanner.close();
    }
}
