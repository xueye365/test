package src.leetcode.medium;


/**
 *
 * 动态规划
 *
 * 打家劫舍
 *
 * https://leetcode-cn.com/problems/house-robber/
 *
 *
 */

public class HouseRobber {


    public static void main(String[] args) {
        System.out.println(rob2(new int[]{2,7,9,3,1}));
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        temp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            int maxTemp = 0;
            for (int i1 = 0; i1 < i - 1; i1++) {
                maxTemp = Math.max(maxTemp, temp[i1]);
            }
            temp[i] = nums[i] + maxTemp;
        }
        int max = 0;
        for (int i = 0; i < temp.length; i++) {
            max = Math.max(max, temp[i]);
        }
        return max;
    }


    /**
     * 动态规划，只记录两个最大状态
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax = nums[0];
        int nextMax = nums[1];
        int temp;
        for (int i = 2; i < nums.length; i++) {
            temp = nums[i] + preMax;
            preMax = Math.max(nextMax, preMax);
            nextMax = temp;
        }
        return Math.max(preMax, nextMax);
    }

}
