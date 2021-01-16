package leetcode.medium;

/**
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1017/
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * 搜索旋转排序数组
 *
 */
public class ArrayAndSorting {

    public static void main(String[] args) {
//        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
//        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));
//        System.out.println(search(new int[]{4,5,6,7,8,1,2,3}, 8));
        System.out.println(search(new int[]{1,3}, 3));
    }

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            if (mid == start || mid == end) {
                return -1;
            }
            if (nums[mid] > target) {
                if (nums[mid] < nums[start] && nums[mid] < nums[end]) {
                    // 起始点在中点前面
                    end = mid;
                } else {
                    // 起始点在中点后面
                    if (nums[start] > target) {
                        // 目标在后半段
                        start = mid;
                    } else {
                        // 目标在前半段
                        end = mid;
                    }
                }
            } else {
                if (nums[mid] > nums[start] && nums[mid] > nums[end]) {
                    // 起始点在中点后面
                    start = mid;
                } else {
                    // 起始点在中点前面
                    if (nums[end] > target) {
                        // 目标在后半段
                        start = mid;
                    } else {
                        // 目标在前半段
                        end = mid;
                    }
                }
            }
        }
        return -1;
    }

}
