package com.leetcode.dp;

import java.util.Arrays;

public class CoinChange {

    // time: o(N)
    // space: o(N)
    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];   // minimal coin change of each amount up to amount, including 0
        Arrays.fill(dp, amount+1); // just a filler since we are getting minimum
        dp[0] = 0;  // an amount of 0, will always be 0

        for (int currentAmount=1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {    // iterate through possible coins to fill
                if (coin <= currentAmount) {  // makes no sense for the coins > current amount
                    int prevMinimumCoinChange = currentAmount - coin;
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[prevMinimumCoinChange] + 1);  // the current coin is always +1
                }
            }
        }

        if (dp[amount] > amount)
            return -1;
        else
            return dp[amount];
    }

    public static void main(String[] a) {
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(new int[] {1,2,5}, 11));
        System.out.println(c.coinChange(new int[]{186,419,83,408}, 6249));
    }
}
