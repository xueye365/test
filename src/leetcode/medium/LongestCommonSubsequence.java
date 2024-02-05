package src.leetcode.medium;



/**
 *
 * 最长公共子序列
 *
 * https://leetcode.cn/problems/longest-common-subsequence/
 *
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        char[] text1Char = text1.toCharArray();
        char[] text2Char = text2.toCharArray();
        short[][] dp = new short[length1 + 1][length2 + 1];
        short max = 0;
        for (short i = 1; i <= length1; i++) {
            for (short j = 1; j <= length2; j++) {
                if (text1Char[i - 1] == text2Char[j - 1]) {
                    dp[i][j] = (short) (dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = (short)Math.max(dp[i][j - 1] , dp[i - 1][j]);
                }
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }




}