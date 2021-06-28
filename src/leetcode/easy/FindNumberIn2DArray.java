package src.leetcode.easy;



/**
 *
 * 剑指offer
 * 二维数组中的查找
 *
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 */
public class FindNumberIn2DArray {

    public static void main(String[] args) {

//        int[][] list = new int[5][5];
//        list[0] = new int[]{1,4,7,11,15};
//        list[1] = new int[]{2,5,8,12,19};
//        list[2] = new int[]{3,6,9,16,22};
//        list[3] = new int[]{10,13,14,17,24};
//        list[4] = new int[]{18,21,23,26,30};
        int[][] list = new int[1][2];
        list[0] = new int[]{-1,3};
        boolean numberIn2DArray = findNumberIn2DArray(list, -1);
        System.out.println(numberIn2DArray);
    }


    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        int column = 0;
        if (row > 0) {
            column = matrix[0].length;
        }
        for (int i = 0, j = column - 1; i < row && j >= 0;) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

}