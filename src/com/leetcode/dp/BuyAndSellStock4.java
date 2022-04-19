package com.leetcode.dp;

public class BuyAndSellStock4 {

    // time: O(k * prices.length)
    // space: O(k * prices.length)
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // we like to compute cumulative max profits per transaction row
        // the first k row is 0, 0 transactions, 0 profits
        int[][] profits = new int[k+1][prices.length];

        for (int j=1; j <= k; j++) {
            int max = Integer.MIN_VALUE;    // local maxima in k row holds the best "buy"

            // we cannot start with 0, as buying the first prices[0] results in 0 profits
            for (int i=1; i < prices.length; i++) {
                // the local maxima is the max profit of the previous k transaction's previous sell
                // minus when you buy the previous prices[i-1]
                max = Math.max(max,  profits[j-1][i-1] - prices[i-1]);
                // the current max profit of k transaction is either the previous profit, or
                // current profit by buying in the local maxima and selling in prices[i]
                profits[j][i] = Math.max(profits[j][i-1], max+prices[i]);
            }
        }
//        for (int[] p : profits) {
//            System.out.println(Arrays.toString(p));
//        }
        return profits[k][prices.length-1];
    }
}
