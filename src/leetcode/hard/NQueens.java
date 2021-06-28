package src.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * n皇后
 *
 * https://leetcode-cn.com/problems/n-queens/
 *
 */
public class NQueens {


    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);
        System.out.println(lists);
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        solveNQueen(0, n, new int[n], results);
        return results;
    }

    public static void solveNQueen(int row, int n, int[] result, List<List<String>> results) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for (int i1 = 0; i1 < n; i1++) {
                    if (i1 == result[i]) {
                        s.append("Q");
                    } else {
                        s.append(".");
                    }
                }
                list.add(s.toString());
            }
            results.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (checkout(row, i, n, result)) {
                result[row] = i;
                solveNQueen(row + 1, n, result, results);
            }
        }

    }


    public static boolean checkout(int row, int column, int n, int[] result) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0 ; i--) {
            if (column == result[i]) {
                return false;
            }
            if (leftUp >= 0 && leftUp == result[i]) {
                return false;
            }
            if (rightUp < n && rightUp == result[i]) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }
}
