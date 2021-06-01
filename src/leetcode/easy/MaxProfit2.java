package src.leetcode.easy;

/**
 * 字节跳动
 *
 * 买卖股票的最佳时机II
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1043/
 */
public class MaxProfit2 {


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit(new int[]{1,2,3,4,5}));
        System.out.println(maxProfit(new int[]{7,6,4,3,1}));
    }

    /**
     * 一次遍历
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        int last = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (last < prices[i]) {
                result += prices[i] - last;
            }
            last = prices[i];
        }
        return result;
    }


}
