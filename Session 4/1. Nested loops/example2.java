public class example2 {
    public static void main(String[] args) {
        //inverted pyramid
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println("");
        }
    }
}
