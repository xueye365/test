package src.leetcode.medium;



/**
 *
 * 动态规划
 * 跳跃游戏
 *
 * https://leetcode-cn.com/problems/jump-game/
 *
 */

public class JumpGame {


    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2,0,0}));
    }

    public static boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        int max = 0;
        for (int i = 0; i < nums.length && max >= i; i++) {
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }





}
