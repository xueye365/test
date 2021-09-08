package src.leetcode.easy;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 动态规划
 * 杨辉三角
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 */

public class PascalsTriangle {



    public static void main(String[] args) {
        System.out.println(generate2(5));
    }



    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        result.add(temp);
        if (numRows == 1) {
            return result;
        }
        List<Integer> temp2 = new ArrayList<>();
        temp2.add(1);
        temp2.add(1);
        result.add(temp2);
        if (numRows == 2) {
            return result;
        }
        if (numRows > 2) {
            for (int i = 2; i < numRows; i++) {
                List<Integer> last = result.get(i - 1);
                List<Integer> thisList = new ArrayList<>();
                thisList.add(1);
                for (int i1 = 1; i1 <= i - 1; i1++) {
                    thisList.add(last.get(i1) + last.get(i1 - 1));
                }
                thisList.add(1);
                result.add(thisList);
            }
        }
        return result;
    }



    public static List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> thisList = new ArrayList<>();
            for (int i1 = 0; i1 <= i; i1++) {
                if (i1 == 0 || i1 == i) {
                    thisList.add(1);
                } else {
                    thisList.add(result.get(i - 1).get(i1) + result.get(i - 1).get(i1 - 1));
                }
            }
            result.add(thisList);
        }
        return result;
    }


}
