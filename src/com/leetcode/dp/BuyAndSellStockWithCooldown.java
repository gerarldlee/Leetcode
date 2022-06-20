package com.leetcode.dp;

public class BuyAndSellStockWithCooldown {

    public int maxProfitDfs(int[] prices) {
        int[][] memo = new int[prices.length][1];
        return dfs(0, prices, memo, 1);
    }

    private int dfs(int index, int[] prices, int[][] memo, int buyingOrSelling) {
        if (index >= prices.length) return 0;
        if (memo[index][0] > 0) return memo[index][0];

        int cooldownProfit = dfs(index + 1, prices, memo, buyingOrSelling);
        if (buyingOrSelling > 0) {
            int buyProfit = dfs(index + 1, prices, memo, buyingOrSelling) - prices[index];
            memo[index][0] = Math.max(buyProfit, cooldownProfit);
        }
        else {
            int sellProfit = dfs(index + 2, prices, memo, 0) + prices[index];
            memo[index][0] = Math.max(sellProfit, cooldownProfit);
        }
        return memo[index][0];
    }



    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int buy[] = new int[prices.length];
        int sell[] = new int[prices.length];

        buy[0] = -prices[0]; // buy the first entry, resulting in negative income
        buy[1] = Math.max(buy[0], sell[0] - prices[1]); // minimize the loss, by comparing todays sell vs yday buy
        sell[1] = Math.max(0, buy[0] + prices[1]);    // max profit = max(0, sell the previous bought)

        for(int i = 2; i < prices.length; i++) {

            // recurrence relation for buy
            // buy[i] = minimze(prev buy, or previous sell + coolldown + today's buy)
            buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);

            // recurrence relation for sell (max profit)
            // sell[i] = maximze(previous sell, or today's sell)
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }

        return sell[prices.length-1];
    }

    public static void main(String[] a) {
        BuyAndSellStockWithCooldown b = new BuyAndSellStockWithCooldown();
        System.out.println(b.maxProfit(new int[] {1,2,3,0,2}));
    }
}
