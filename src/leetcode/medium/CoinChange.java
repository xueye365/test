package src.leetcode.medium;


import java.util.Arrays;

/**
 *
 * 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/
 *
 */
public class CoinChange {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5, 7, 10}, 14));
    }


    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int i1 = 0; i1 < coins.length; i1++) {
                if (coins[i1] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[i1]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


}
