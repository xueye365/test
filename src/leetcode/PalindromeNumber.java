package src.leetcode;

/**
 * 整数反转
 *
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
 * 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
 *
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int y = 0;
        while (x > y) {
            y = y * 10 + x % 10;
            x = x / 10;
        }
        if (x == y || x == y / 10) {
            return true;
        } else {
            return false;
        }


    }

}
