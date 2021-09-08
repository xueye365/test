package src.leetcode.medium;



/**
 *
 * 动态规划
 * 最小路径和
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 */

public class MinPathSum {



    public static void main(String[] args) {
        int[][] list = new int[2][3];
        list[0] = new int[]{1,2,3};
        list[1] = new int[]{4,5,6};
//        list[3] = new int[]{10,13,14,17,24};
//        list[4] = new int[]{18,21,23,26,30};
        System.out.println(minPathSum(list));
    }

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] temp = new int[m][n];
        temp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            temp[i][0] = grid[i][0] + temp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            temp[0][i] = grid[0][i] + temp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int i1 = 1; i1 < n; i1++) {
                int min = Math.min(temp[i - 1][i1], temp[i][i1 - 1]);
                temp[i][i1] = min + grid[i][i1];
            }
        }
        return temp[m - 1][n - 1];
    }


}
