package src.leetcode.easy;

/**
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1035/
 *
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 *
 * 最长连续递增序列
 *
 */
public class FindLengthOfLCIS {


    public static void main(String[] args) {
        System.out.println(findLengthOfLCIS(new int[]{1,3,5,7}));
    }

    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                temp++;
            } else {
                if (max < temp) {
                    max = temp;
                }
                temp = 1;
            }
        }
        if (max < temp) {
            max = temp;
        }
        return max;
    }

}
