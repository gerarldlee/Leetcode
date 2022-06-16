package com.algomonster.dp;

import java.util.Scanner;

public class NumberUniquePaths {

    // time: o(mn), space: o(mn)
    public static int uniquePaths(int m, int n) {
        // WRITE YOUR BRILLIANT CODE HERE
        int[][] dp = new int[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 || j==0) {
                    dp[i][j] = 1;
                    continue;
                }

                // recurrence relation is: the unique ways to go to point i,j is
                // the i-1,j + i,j-1
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = uniquePaths(m, n);
        System.out.println(res);
    }
}
