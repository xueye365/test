package src.leetcode.bytesBeat;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态规划
 * 打家劫舍2
 * https://leetcode-cn.com/problems/house-robber-ii/
 */

public class HouseRobber2 {


    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,3,2}));
    }

    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax1 = nums[0];
        int nextMax1 = nums[1];
        int temp1;
        for (int i = 2; i < nums.length; i++) {
            temp1 = nums[i] + preMax1;
            preMax1 = Math.max(nextMax1, preMax1);
            nextMax1 = temp1;
        }

        int preMax2 = nums[nums.length - 1];
        int nextMax2 = nums[0];
        int temp2;
        for (int i = 1; i < nums.length - 1; i++) {
            temp2 = nums[i] + preMax2;
            preMax2 = Math.max(nextMax2, preMax2);
            nextMax2 = temp2;
        }

        return Math.max(Math.max(preMax1, nextMax1), Math.max(preMax2, nextMax2));
    }



}
