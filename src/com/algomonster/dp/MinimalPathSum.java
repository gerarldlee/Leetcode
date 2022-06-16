package com.algomonster.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MinimalPathSum {
    public static int minPathSum(List<List<Integer>> grid) {
        // WRITE YOUR BRILLIANT CODE HERE

        int[][] dp = new int[grid.size()][grid.get(0).size()];

        for (int i=0; i<grid.size(); i++) {
            for (int j=0; j<grid.get(i).size(); j++) {
                if (i==0 && j==0) {
                    dp[i][j] = grid.get(i).get(j);
                }
                else if (i==0) {
                    dp[i][j] = grid.get(i).get(j) + dp[i][j-1];
                }
                else if (j==0) {
                    dp[i][j] = grid.get(i).get(j) + dp[i-1][j];
                }
                else {
                    // just like the unique path, the recurrence relation of this is
                    // Min of previous points, (i-1,j) and (i,j-1) + the current (i,j)
                    dp[i][j] = grid.get(i).get(j) + Math.min(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[grid.size()-1][grid.get(0).size()-1];
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gridLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < gridLength; i++) {
            grid.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = minPathSum(grid);
        System.out.println(res);
    }
}
