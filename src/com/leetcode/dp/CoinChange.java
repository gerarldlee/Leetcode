package com.leetcode.dp;

import java.util.Arrays;

public class CoinChange {

    // time: o(N)
    // space: o(N)
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];   // minimal coin change of each amount up to amount, including 0
        Arrays.fill(dp, Integer.MAX_VALUE); // just a filler since we are getting minimum
        dp[0] = 0;  // an amount of 0, will always be 0

        for (int currentAmount=1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {    // iterate through possible coins to fill
                if (coin <= currentAmount) {  // makes no sense for the coins > current amount
                    int prevMinimumCoinChange = currentAmount - coin;
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[prevMinimumCoinChange] + 1);  // the current coin is always +1
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] a) {
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(new int[] {1,2,5}, 11));
    }
}
