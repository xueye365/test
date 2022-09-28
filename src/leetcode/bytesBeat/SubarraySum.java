package src.leetcode.bytesBeat;


import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * 和为 K 的子数组
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 *
 *
 */

public class SubarraySum {



    public static void main(String[] args) {

        System.out.println(subarraySum(new int[]{-1, -1, 1}, 0));

    }

    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            if (start == end) {
                sum = nums[start];
                if (sum == k) {
                    count++;
                    end++;
                    start++;
                } else if (sum < k) {
                    end++;
                    sum+=nums[end];
                } else if (sum > k) {
                    start++;
                    end++;
                    if (start < nums.length) {
                        sum = nums[start];
                    }
                }
            } else if (start < end) {
                if (sum == k) {
                    count++;
                    end++;
                    start++;
                } else if (sum < k) {
                    end++;
                    sum+=nums[end];
                } else if (sum > k) {
                    sum = sum - nums[start];
                    start++;
                }
            }
            if ((start > nums.length - 1 || end > nums.length - 1)) {
                break;
            }
        }
        return count;
    }

}
