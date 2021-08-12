package src.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指offer
 *
 * 和为s的两个数字
 * https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 *
 */
public class JianzhiTwoSum {


    public static void main(String[] args) {
        int[] isConnected = new int[]{10,26,30,31,47,60};
        int[] ints = twoSum2(isConnected, 40);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /**
     * 数据无序的时候使用
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map map = new HashMap();
        for (int num : nums) {
            int i = target - num;
            if (map.containsKey(i)) {
                return new int[]{num, i};
            } else {
                map.put(num, i);
            }
        }
        return null;
    }


    /**
     * 数据有序的时候用双指针
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[] { nums[i], nums[j] };
            }
        }
        return new int[0];
    }

}
