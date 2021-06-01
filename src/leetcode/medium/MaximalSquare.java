package src.leetcode.medium;

/**
 * 字节跳动
 *
 * 最大正方形
 *
 * https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode-solution/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1028/
 */
public class MaximalSquare {


    public static void main(String[] args) {
        int[][] matrix = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        char[][] matrix1 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix2 = {{'0','1'},{'1','0'}};
        System.out.println(maximalSquare(matrix2));
    }

    public static int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int row = matrix.length;
        int column = matrix[0].length;

        int[][] dp = new int[row][column];
        for (int i = 0; i < column; i++) {
            dp[0][i] = matrix[0][i] - '0';
            maxSide = Math.max(maxSide, dp[0][i]);
        }
        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxSide = Math.max(maxSide, dp[i][0]);
        }

        for (int i = 1; i < row; i++) {
            for (int i1 = 1; i1 < column; i1++) {
                if (matrix[i][i1] == '1') {
                    dp[i][i1] = Math.min(Math.min(dp[i-1][i1], dp[i][i1-1]), dp[i-1][i1-1]) + 1;
                }
                maxSide = Math.max(maxSide, dp[i][i1]);
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 官方暴力解法
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }



}
