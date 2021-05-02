package src.leetcode.easy;

/**
 * 整数反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(reverse(1463847412));
    }

    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            int temp = (x % 10);
            x = x / 10;
            if (Integer.MAX_VALUE/10 < y || (Integer.MAX_VALUE/10 == y && temp > 7)) {
                return 0;
            }
            if (Integer.MIN_VALUE/10 > y || (Integer.MAX_VALUE/10 == y && temp < -8)) {
                return 0;
            }
            y = y * 10 + temp;
        }
        return y;
    }

}
