package src.leetcode.bytesBeat;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 字节跳动
 *
 * 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * https://leetcode-cn.com/explore/interview/card/bytedance/243/array-and-sorting/1047/
 *
 */
public class TrappingRainWater {


    public static void main(String[] args) {
        int[] isConnected = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(isConnected));
    }


    /**
     * 动态规划
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int[] leftHigh = new int[height.length];
        leftHigh[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftHigh[i] = Math.max(height[i], leftHigh[i - 1]);
        }
        int[] rightHigh = new int[height.length];
        rightHigh[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightHigh[i] = Math.max(height[i], rightHigh[i + 1]);
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(rightHigh[i], leftHigh[i]) - height[i];
        }
        return result;
    }


    /**
     * 单调栈
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }





}
