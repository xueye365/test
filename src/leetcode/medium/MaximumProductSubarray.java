package src.leetcode.medium;



/**
 *
 * 动态规划
 *
 * 乘积最大子数组
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 *
 */

public class MaximumProductSubarray {


    public static void main(String[] args) {
        System.out.println(maxProduct2(new int[]{-4, -3, -2}));
    }

    static int max;

    public static int maxProduct(int[] nums) {
        dfs(nums, 0);
        return max;
    }
    public static void dfs(int[] nums, int i) {
        if (i == nums.length) {
            return;
        }
        int multiplication = nums[i];
        for (int i1 = i + 1; i1 < nums.length; i1++) {
            multiplication = multiplication * nums[i1];
            max = Math.max(max, multiplication);
        }
        dfs(nums, i + 1);
    }


    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static int maxProduct2(int[] nums) {
        int max = nums[0], min = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int mx = max, mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], nums[i] * mn));
            min = Math.min(mn * nums[i], Math.min(nums[i], nums[i] * mx));
            result = Math.max(result, max);
        }
        return result;
    }

}
