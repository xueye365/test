package src.leetcode.easy;

/**
 * 字节跳动
 *
 * 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1029/
 */
public class MaxSubArray {


    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    /**
     * 动态规划
     * 若当前所指元素之前的和加上当前元素哪个大留哪个
     * 再和最大的做比较
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }


}
