package src.leetcode.medium;



/**
 *
 * 动态规划
 * 不同路径
 *
 * https://leetcode-cn.com/problems/unique-paths/
 *
 */

public class UniquePaths {


    private static int result;

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,3));
    }

    // 深度优先遍历
    public static int uniquePaths(int m, int n) {
        dfs(m - 1, n - 1, 0, 0);
        return result;
    }

    public static void dfs(int m, int n, int i, int j) {
        if (m == i && n == j) {
            result++;
        }
        if (i < m) {
            dfs(m, n, i + 1, j);
        }
        if (j < n) {
            dfs(m, n, i, j + 1);
        }
    }


    // 动态规划
    public static int uniquePaths2(int m, int n) {
        int[][] temp = new int[m][n];
        for (int i = 0; i < n; i++) {
            temp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            temp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int i1 = 1; i1 < n; i1++) {
                temp[i][i1] = temp[i - 1][i1] + temp[i][i1 - 1];
            }
        }
        return temp[m - 1][n - 1];
    }


}
