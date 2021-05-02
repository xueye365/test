package src.leetcode.easy;

/**
 *
 * 搜索插入位置
 *
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));
    }


    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i <= nums.length; i++) {
            if (i == nums.length) {
                return i;
            }
            if (nums[i] == target) {
                return i;
            } else if (nums[i] < target) {
                continue;
            } else {
                return i;
            }
        }
        return -1;
    }



}
