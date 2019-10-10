package leetcode.easy;



/**
 * 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 */
public class RemoveElement {

    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{0, 1, 1, 1, 2, 3, 2, 0}, 2));
    }
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }
        return count;
    }


}
