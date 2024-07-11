public class example {
    public static void main(String[] args) {
        // year calendar
        for (int i = 1; i <= 12; i++) {
            System.out.println("Month: " + i);
            int days = 0;
            switch (i) {
                case 1:
                    days = 31;
                    break;
                case 2:
                    days = 28;
                    break;
                case 3:
                    days = 31;
                    break;
                case 4:
                    days = 30;
                    break;
                case 5:
                    days = 31;
                    break;
                case 6:
                    days = 30;
                    break;
                case 7:
                    days = 31;
                    break;
                case 8:
                    days = 31;
                    break;
                case 9:
                    days = 30;
                    break;
                case 10:
                    days = 31;
                    break;
                case 11:
                    days = 30;
                    break;
                case 12:
                    days = 31;
                    break;
            }

            for (int j = 1; j <= days; j++) {
                System.out.println("Day: " + j);
            }
            
        }
    }
}
