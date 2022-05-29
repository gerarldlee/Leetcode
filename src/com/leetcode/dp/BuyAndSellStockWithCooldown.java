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
        int buyDp[] = new int[prices.length];
        int sellDp[] = new int[prices.length];
        int cooldownDp[] = new int[prices.length];

        for(int i = 1; i < prices.length; i++) {

            int buy = prices[i-1];
            int sell = buy + prices[i];
            int cooldown = 0;

        }

        return buyDp[0];
    }

    public static void main(String[] a) {
        BuyAndSellStockWithCooldown b = new BuyAndSellStockWithCooldown();
        System.out.println(b.maxProfitDfs(new int[] {1,2,3,0,2}));
    }
}
