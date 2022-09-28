package src.leetcode.bytesBeat;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 找到所有数组中消失的数字
 *
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 */

public class FindDisappearedNumbers {



    public static void main(String[] args) {
        findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
    }
//    4,3,5,11,13,2,10,1

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            int less = nums[i] % nums.length;
            if (less == i + 1) {
                continue;
            } else {
                nums[less - 1] = nums[less - 1] + less;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % nums.length != i - 1) {
                result.add(i - 1);
            }
        }
        return result;
    }

}
