package src.leetcode.easy;


/**
 *
 * 只出现一次的数字
 *
 * https://leetcode-cn.com/problems/single-number/
 *
 */
public class SingleNumber {


    public static void main(String[] args) {

    }

    public static int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }


}
