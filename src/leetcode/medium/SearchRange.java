package src.leetcode.medium;



/**
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 */

public class SearchRange {



    public static void main(String[] args) {
        int[] ints = searchRange(new int[]{1}, 1);
        System.out.println(ints[0] + " " + ints[1]);
    }

    private  static int[] result = new int[2];

    public static int[] searchRange(int[] nums, int target) {
        result[0] = -1;
        result[1] = -1;
        searchRange(nums, target, 0, nums.length - 1);
        return result;
    }


    public static void searchRange(int[] nums, int target, int start, int end) {
        if (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (nums[mid] == target) {
                int midMin = mid;
                while (midMin >= 0 && nums[midMin] == target) {
                    result[0] = midMin;
                    midMin--;
                }
                int midMax = mid;
                while (midMax <= nums.length - 1 && nums[midMax] == target) {
                    result[1] = midMax;
                    midMax++;
                }
            } else if (nums[mid] > target) {
                searchRange(nums, target, start, mid - 1);
            } else {
                searchRange(nums, target, mid + 1, end);
            }
        }
    }


}
