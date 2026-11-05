public class PerfectNumber {
    static boolean isPerfect(int n) {
        if (n <= 1) return false;
        long sum = 1;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) sum += i;
        }
        return sum == n;
    }

    public static void main(String[] args) {
        System.out.println("Perfect numbers within 1000:");
        for (int n = 2; n <= 1000; n++) {
            if (isPerfect(n)) System.out.println(n);
        }
    }
}
