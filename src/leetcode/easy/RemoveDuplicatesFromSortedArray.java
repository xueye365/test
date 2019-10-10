package leetcode.easy;

/**
 * 删除排序数组中的重复项
 *
 * https://leetcode-cn.com/explore/interview/card/tencent/221/array-and-strings/902/
 *
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{}));
    }
    public static int removeDuplicates(int[] nums) {
        int a = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[a]) {
                a++;
                nums[a] = nums[i];
            }
        }
        return a + 1;
    }


}
