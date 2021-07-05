package src.leetcode.easy;


import java.util.Arrays;

/**
 *
 * 顺时针打印矩阵
 *
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 */
public class ShunShiZhenDaYinJuZhenLcof {


    public static void main(String[] args) {
//
//        int[][] list = new int[3][4];
//        list[0] = new int[]{1,2,3,4};
//        list[1] = new int[]{5,6,7,8};
//        list[2] = new int[]{9,10,11,12};


        int[][] list = new int[3][4];
        list[0] = new int[]{1,2,3};
        list[1] = new int[]{5,6,7};
        list[2] = new int[]{9,10,11};

        System.out.println(Arrays.asList(spiralOrder(list)));
    }

    /**
     * 我自己的解法
     * @param matrix
     * @return
     */
    public static int[] spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int column = 0;
        if (row > 0) {
            column = matrix[0].length;
        } else {
            return new int[0];
        }
        int[] result = new int[row * column];
        boolean[] rowVisited = new boolean[row];
        boolean[] columnVisited = new boolean[column];
        boolean rowDirection = true;
        boolean columnDirection = true;
        int rowCount = 0;
        int columnCount = 0;


        for (int i = 0; i < row * column;) {
            if (rowDirection) {
                for (int j = 0; j < columnVisited.length; j++) {
                    if (!columnVisited[j]) {
                        result[i] = matrix[rowCount][j];
                        columnCount = j;
                        i++;
                    }
                }
                rowVisited[rowCount] = true;
                rowDirection = false;
            } else {
                for (int j = columnVisited.length - 1; j >= 0; j--) {
                    if (!columnVisited[j]) {
                        result[i] = matrix[rowCount][j];
                        columnCount = j;
                        i++;
                    }
                }
                rowVisited[rowCount] = true;
                rowDirection = true;
            }
            if (columnDirection) {
                for (int j = 0; j < rowVisited.length; j++) {
                    if (!rowVisited[j]) {
                        result[i] = matrix[j][columnCount];
                        rowCount = j;
                        i++;
                    }
                }
                columnVisited[columnCount] = true;
                columnDirection = false;
            } else {
                for (int j = rowVisited.length - 1; j >= 0; j--) {
                    if (!rowVisited[j]) {
                        result[i] = matrix[j][columnCount];
                        rowCount = j;
                        i++;
                    }
                }
                columnVisited[columnCount] = true;
                columnDirection = true;
            }
        }
        return result;
    }


    /**
     * 官方解答
     * @param matrix
     * @return
     */
    public int[] spiralOrder2(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;
    }







}