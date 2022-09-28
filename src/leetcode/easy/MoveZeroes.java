package src.leetcode.easy;



/**
 *
 * 移动零
 *
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 */

public class MoveZeroes {



    public static void main(String[] args) {
        moveZeroes(new int[]{0,1,0,3,12});
    }

    public static void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }

}
