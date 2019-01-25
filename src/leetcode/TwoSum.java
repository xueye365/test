package leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * https://leetcode-cn.com/problems/two-sum/
 *
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp) && (int) map.get(temp) != i) {
                return new int[]{i, (int) map.get(temp)};
            }
        }
        return null;
    }

}
