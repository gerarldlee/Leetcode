package com.leetcode.facebook;

public class BestTimeToBuySellStocks {

    // space: O(1), time: O(N)
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for (int i=0; i < prices.length; i++) {
            if (prices[i] < min)	min = prices[i];
            else if (prices[i] >= min) {
                max = Math.max(max, prices[i] - min);
            }
        }
        return max;
    }
}
