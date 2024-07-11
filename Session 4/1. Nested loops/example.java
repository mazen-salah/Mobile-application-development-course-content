public class example {
    public static void main(String[] args) {
        // year calendar
        for (int i = 1; i <= 12; i++) {
            System.out.println("Month: " + i);
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                for (int j = 1; j <= 31; j++) {
                    System.out.println("Day: " + j);
                }
            } else if (i == 4 || i == 6 || i == 9 || i == 11) {
                for (int j = 1; j <= 30; j++) {
                    System.out.println("Day: " + j);
                }
            } else {
                for (int j = 1; j <= 28; j++) {
                    System.out.println("Day: " + j);
                }
            }
        }
    }
}
