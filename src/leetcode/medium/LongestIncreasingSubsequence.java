package leetcode.medium;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 *  最长递增子序列
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{0,1,0,3,2,3}));
    }


    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] temp = new int[nums.length];
        temp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            temp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    temp[i] = Math.max(temp[j] + 1, temp[i]);
                }
            }
        }
        int max = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }

}
