public class exercise_3 {
    public static void main(String[] args) {
        int a = 100, b = 100, c = 100, d = 100;
        int p = a++; // p = 100, a = 101
        int r = c--; // r = 100, c = 99
        int q = ++b; // q = 101, b = 101
        int s = --d; // s = 99, d = 99

        System.out.println("p: " + p + ", a: " + a);
        System.out.println("r: " + r + ", c: " + c);
        System.out.println("q: " + q + ", b: " + b);
        System.out.println("s: " + s + ", d: " + d);
    }
}
