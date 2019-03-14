package src.leetcode;

/**
 * 盛最多水的容器
 *
 * https://leetcode-cn.com/explore/interview/card/tencent/221/array-and-strings/903/
 *
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea1(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    /**
     * 我自己写的
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            for (int i1 = i + 1; i1 < height.length; i1++) {
                int min = Math.min(height[i], height[i1]);
                int volume = Math.abs(i - i1) * min;
                result = Math.max(volume, result);
            }
        }
        return result;
    }


    /**
     * 优化版
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int result = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            result = Math.max(Math.abs(i - j) * Math.min(height[i], height[j]), result);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

}
