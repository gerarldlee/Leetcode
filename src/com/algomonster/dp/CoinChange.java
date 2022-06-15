package com.algomonster.dp;

import java.util.Arrays;
import java.util.List;

public class CoinChange {

    /*
    The recurrence relation is:
    the fewest coin change of a given amount
    change[i] = fewest coin of the previous change + fewest coin of the remaining amount
     */

    public static int coinChange(List<Integer> coins, int amount) {
        // WRITE YOUR BRILLIANT CODE HERE
        int[] changes = new int[amount+1];
        Arrays.fill(changes, 0x3f3f3f3f);
        changes[0] = 0;

        for (int i=1; i<=amount; i++) {
            for (Integer coin : coins) {
                if (i - coin < 0) continue;
                int change = i - coin;
                changes[i] = Math.min(changes[change] + 1, changes[i]);
            }
        }

        return changes[amount] > amount ? -1 : changes[amount];
    }

    public static void main(String[] argv) {
        System.out.println(coinChange(List.of(1,2,5), 11));
    }
}
