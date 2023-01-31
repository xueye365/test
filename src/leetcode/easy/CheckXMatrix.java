package src.leetcode.easy;

/**
 * https://leetcode.cn/problems/check-if-matrix-is-x-matrix/submissions/
 *
 * 判断矩阵是否是一个 X 矩阵
 *
 */
public class CheckXMatrix {


    public static void main(String[] args) {
//        int[][] a = new int[][]{{2,0,0,1},{0,3,1,0},{0,5,2,0},{4,0,0,2}};
        int[][] a = new int[][]{{6,0,19},{0,2,0},{13,17,6}};
        System.out.println(checkXMatrix(a));
    }



    public static boolean checkXMatrix(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            int[] a = grid[i];
            for (int i1 = 0, i2 = a.length - 1; i1 < a.length && i2 > 0 && i1 <= i2; i1++, i2--) {
                if (i == i1 || i == i2) {
                    if (grid[i][i1] != 0 && grid[i][i2] != 0) {
                        continue;
                    }
                } else {
                    if (grid[i][i1] == 0 && grid[i][i2] == 0) {
                        continue;
                    }
                }
                return false;
            }
        }
        return true;
    }


}
