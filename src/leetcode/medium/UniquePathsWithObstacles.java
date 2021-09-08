package src.leetcode.medium;



/**
 *
 * 动态规划
 * 不同路径2
 *
 * https://leetcode-cn.com/problems/unique-paths-ii/
 *
 */

public class UniquePathsWithObstacles {



    public static void main(String[] args) {
        int[][] list = new int[3][3];
        list[0] = new int[]{0,0,0};
        list[1] = new int[]{0,1,0};
        list[2] = new int[]{0,0,0};
//        list[3] = new int[]{10,13,14,17,24};
//        list[4] = new int[]{18,21,23,26,30};
        System.out.println(uniquePathsWithObstacles2(list));
    }


    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] temp = new int[obstacleGrid.length][obstacleGrid[0].length];
        boolean obstacleGridFlag = false;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (!obstacleGridFlag && obstacleGrid[i][0] != 1) {
                temp[i][0] = 1;
            } else {
                obstacleGridFlag = true;
                temp[i][0] = 0;
            }
        }
        obstacleGridFlag = false;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (!obstacleGridFlag && obstacleGrid[0][i] != 1) {
                temp[0][i] = 1;
            } else {
                obstacleGridFlag = true;
                temp[0][i] = 0;
            }
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int i1 = 1; i1 < obstacleGrid[0].length; i1++) {
                if (obstacleGrid[i][i1] != 1) {
                    temp[i][i1] = temp[i - 1][i1] + temp[i][i1 - 1];
                } else {
                    temp[i][i1] = 0;
                }
            }
        }
        return temp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }




    public static int result = 0;

    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        dfs(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, 0, 0);
        return result;
    }


    public static void dfs(int[][] obstacleGrid, int m, int n, int i, int j) {
        if (m <= i && n <= j) {
            result++;
        }
        if (i < m) {
            if (obstacleGrid[i][j] == 0) {
                dfs(obstacleGrid, m, n, i + 1, j);
            }
        }
        if (j < n) {
            if (obstacleGrid[i][j] == 0) {
                dfs(obstacleGrid, m, n, i, j + 1);
            }
        }
    }



}
