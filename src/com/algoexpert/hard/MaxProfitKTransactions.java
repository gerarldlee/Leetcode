package com.algoexpert.hard;

import java.util.Arrays;

/*
You are given an array of positive integers representing the prices of a single stock on various days.
Each index represents different days. You are given k integer, which represents the number of transactions
you are allowed to make.

Write a function that returns the max profit that you can make by buying and selling stocks with k tx.

You can only hold one tranction at a time. You cannot buy another share if you are still holding a transaction.

Sample: 
prices = [5,11,3,50,60,90]
k = 2
Output: 93 
Explanation: Buy 5, Sell 11, Buy 3, sell 90

*/
public class MaxProfitKTransactions {

    public int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) return 0;
        int[][] profits = new int[k+1][prices.length];
        int max = Integer.MIN_VALUE;
        for (int i=1; i < prices.length; i++) {
            max = Math.max(max,  profits[0][i-1] - prices[i-1]);
            profits[1][i] = Math.max(profits[1][i-1], max+prices[i]);
        }
        max = Integer.MIN_VALUE;
        for (int i=1; i < prices.length; i++) {
            max = Math.max(max,  profits[1][i-1] - prices[i-1]);
            profits[2][i] = Math.max(profits[2][i-1], max+prices[i]);
        }
        System.out.println(Arrays.toString(profits[0]));
        System.out.println(Arrays.toString(profits[1]));
        System.out.println(Arrays.toString(profits[2]));
        return profits[k][prices.length-1];
    }

    public static void main(String[] a) {
        MaxProfitKTransactions m = new MaxProfitKTransactions();
        int[] prices = new int[] {5,11,3,50,60,90};
        int k = 2;
        System.out.println(m.maxProfitWithKTransactions(prices, k));
    }
}