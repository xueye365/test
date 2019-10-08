package leetcode;

/**
 * 最长回文字符串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcddcbaaaaa"));
    }


    public static String longestPalindrome(String s) {

        char[] a = s.toCharArray();
        int start = 0;
        int end = 0;
        int len = 0;
        for (int i = 0; i < a.length; i++) {
            int len1 = longestPalindrome2(a, i, i);
            int len2 = longestPalindrome2(a, i, i + 1);
            len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int longestPalindrome2(char[] a, int left, int right) {
        while (left >= 0 && right <= a.length - 1 && a[left] == a[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }


}
