package src.leetcode.easy;



/**
 *
 * 剑指offer
 * 数组中重复的数字
 *
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 */
public class FindRepeatNumber {

    public static void main(String[] args) {
        System.out.println(findRepeatNumber(new int[]{3, 1, 2, 3}));
    }


    public static int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    swap(nums, i, nums[i]);
                }
            }
        }
        return 0;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



}