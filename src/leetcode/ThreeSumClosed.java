package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 最接近的三数之和
 *
 * https://leetcode-cn.com/explore/interview/card/tencent/221/array-and-strings/900/
 *
 */
public class ThreeSumClosed {

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{1,1,1,0}, -100));
    }
    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 2) {
            return 0;
        }
        int result = nums[0] + nums[1] + nums[2];
        int distance = Math.abs(result - target);
        for (int x = 0; x <= nums.length - 3; x++) {
            for (int y = x + 1; y <= nums.length - 2; y++) {
                for (int z = y + 1; z <= nums.length - 1; z++) {
                    int sum = nums[x] + nums[y] + nums[z];
                    if (sum == target) {
                        return sum;
                    }
                    if (distance > Math.abs(target - sum)) {
                        distance = Math.abs(target - sum);
                        result = sum;
                    }
                }
            }
        }
        return result;
    }

}
