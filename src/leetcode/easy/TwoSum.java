package src.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/
 *
 * 两数之和
 *
 */
public class TwoSum {


    public static void main(String[] args) {
        int[] a = new int[]{2,7,11,15};
        int[] ints = twoSum(a, 9);
        System.out.println(ints[0] + " " + ints[1]);
    }



    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (result.containsKey(nums[i])) {
                return new int[]{i, result.get(nums[i])};
            } else {
                result.put(target - nums[i], i);
            }
        }
        return new int[]{};
    }


}
