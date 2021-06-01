package src.leetcode.bytesBeat;



/**
 * 字节跳动
 *
 * 俄罗斯套娃信封问题
 *
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1031/
 *
 */
public class Triangle {


    public static void main(String[] args) {
        int[][] ints = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(ints));
    }


    /**
     * 宽度递增，高度递减来排序，这是为了防止计算最长子序列长度时对相同宽度不同高度的信封计算多次。
     * 后面的问题可以简化成求一维数组最长子序列长度。
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {


        return 0;

    }

}
