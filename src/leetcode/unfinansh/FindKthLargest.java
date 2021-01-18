package leetcode.unfinansh;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1018/
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
 *
 * 最长连续序列
 *
 */
public class FindKthLargest {
    public static void main(String[] args) {
//        System.out.println(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println(longestConsecutive(new int[]{9,1,4,7,3,-1,0,5,8,-1,6}));
    }


    public static int longestConsecutive(int[] nums) {
        Set<Integer> temp = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            temp.add(nums[i]);
        }
        int maxLength = 0;

        for (Integer o : temp) {
            int length = 1;
            if (!temp.contains(o - 1)) {
                for (int i = 1; i < temp.size(); i++) {
                    if (temp.contains(o + i)) {
                        length++;
                    } else {
                        break;
                    }
                }
            }
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }

}
