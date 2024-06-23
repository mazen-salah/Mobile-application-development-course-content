public class exercise_4 {
    public static void main(String[] args) {
        int i = 10;
        int j = 10;

        i++; // i = 11
        j++; // j = 11

        int x = i++; // x = 11, i = 12
        int y = ++j; // y = 12, j = 12
        int k  = i++; // k = 12, i = 13
        int l = ++j; // l = 13, j = 13

        System.out.println("i: " + i + "| j: " + j + "| k: " + k + "| l: " + l + "| x: " + x + "| y: " + y);
    }
}
