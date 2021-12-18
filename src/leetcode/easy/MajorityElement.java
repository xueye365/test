package src.leetcode.easy;


import java.util.HashMap;
import java.util.Map;

/**
 *
 * 多数元素
 *
 * https://leetcode-cn.com/problems/majority-element/
 *
 */
public class MajorityElement {


    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1}));
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length / 2;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) > n) {
                    return nums[i];
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }

}
