package src.leetcode.easy;

/**
 * 字节跳动
 *
 * 买卖股票的最佳时机
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode-cn.com/explore/interview/card/bytedance/246/dynamic-programming-or-greedy/1042/
 */
public class MaxProfit {


    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{7,1,5,3,6,4}));
    }

    /**
     * 暴力法
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    /**
     * 一次遍历
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int maxResult = 0;
        int minPoint = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (minPoint > prices[i]) {
                minPoint = prices[i];
            } else if (maxResult < prices[i] - minPoint) {
                maxResult = prices[i] - minPoint;
            }
        }
        return maxResult;
    }


}
