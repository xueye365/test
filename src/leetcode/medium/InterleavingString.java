package src.leetcode.medium;



/**
 *
 * 动态规划
 *
 * 交错字符串
 *
 * https://leetcode-cn.com/problems/interleaving-string/
 *
 *
 */

public class InterleavingString {



    public static void main(String[] args) {
//        System.out.println(isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave2("", "abc", "ab"));
    }


    /**
     * 深度递归
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int i = 0, j = 0, m = s1.length(), n = s2.length();
        return dfs(i, j, m, n, s3.length(), s1, s2, s3);
    }

    public static boolean dfs(int i, int j, int m, int n, int length, String s1, String s2, String s3) {
        if (i == m && j == n && i + j == length) {
            return true;
        }
        if (i < m && i + j < length && s1.charAt(i) == s3.charAt(i + j)) {
            if (dfs(i + 1, j, m, n, length, s1, s2, s3)) return true;
        }
        if (j < n && i + j < length && s2.charAt(j) == s3.charAt(i + j)) {
            if (dfs(i, j + 1, m, n, length, s1, s2, s3)) return true;
        }
        return false;
    }



    /**
     * 动态规划
     */
    public static boolean isInterleave2(String s1, String s2, String s3) {
        int m = s1.length() + 1, n = s2.length() + 1, length = s3.length();
        boolean[][] flag = new boolean[m][n];
        flag[0][0] = true;
        for (int i = 1; i < m; i++) {
            flag[i][0] = flag[i - 1][0] && i - 1 < length && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i < n; i++) {
            flag[0][i] = flag[0][i - 1] && i - 1 < length && s2.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i < m; i++) {
            for (int i1 = 1; i1 < n; i1++) {
                if (flag[i - 1][i1]) {
                    flag[i][i1] = flag[i - 1][i1] && i + i1 - 1 < length && s1.charAt(i - 1) == s3.charAt(i + i1 - 1);
                } else if (flag[i][i1 - 1]) {
                    flag[i][i1] = flag[i][i1 - 1] && i + i1 - 1 < length && s2.charAt(i1 - 1) == s3.charAt(i + i1 - 1);
                } else {
                    flag[i][i1] = false;
                }
            }
        }
        return s1.length() + s2.length() == s3.length() && flag[m - 1][n - 1];
    }



}
