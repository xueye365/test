package leetcode;

/**
 * 斐波那契数列
 */
public class Test7 {

    public static void main(String[] args) {
//        System.out.println(Fibonacci(100));
//        System.out.println(Fibonacci(500));
//        System.out.println(Fibonacci(1000));
//        System.out.println(Fibonacci2(100, 1));
//        System.out.println(Fibonacci2(500, 1));
        System.out.println(Fibonacci2(1000,1));
    }

    public static int Fibonacci (int n) {
        if (n == 1) return 1;
        return n * Fibonacci(n - 1);
    }

    public static int Fibonacci2 (int n , int total) {
        if (n == 1) return total;
        return Fibonacci2(n - 1, n * total);
    }
}
