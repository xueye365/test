package src.leetcode.easy;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 动态规划
 * 杨辉三角2
 *
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 *
 */

public class PascalsTriangleii {



    public static void main(String[] args) {
        System.out.println(getRow(5));
    }



    public static List<Integer> getRow(int rowIndex) {
        List<Integer> last = null;
        List<Integer> thisList = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            thisList.clear();
            for (int i1 = 0; i1 <= i; i1++) {
                if (i1 == 0 || i1 == i) {
                    thisList.add(1);
                } else {
                    thisList.add(last.get(i1) + last.get(i1 - 1));
                }
            }
            last = new ArrayList<>(thisList);
        }
        return last;
    }


}
