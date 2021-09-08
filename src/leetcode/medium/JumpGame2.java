package src.leetcode.medium;



/**
 *
 * 动态规划
 * 跳跃游戏2
 *
 * https://leetcode-cn.com/problems/jump-game-ii/
 *
 */

public class JumpGame2 {


    public static void main(String[] args) {
        System.out.println(jump(new int[]{2,3,1,1,4}));
//        System.out.println(jump(new int[]{1,1,1,1,1}));
    }

    public static int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int maxPosition = nums[0];  // 每次能跳到的最大的地方
        int end = nums[0];          // 上一次跳结束的地方，准备开始下一次跳
        int step = 1;
        for (int i = 1; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }





}
