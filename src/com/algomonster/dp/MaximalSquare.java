package com.algomonster.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MaximalSquare {
    public static int maximalSquare(List<List<Integer>> matrix) {
        // WRITE YOUR BRILLIANT CODE HERE

        int m = matrix.size();
        int n = matrix.get(0).size();
        int[][] dp = new int[m][n];
        int area = 1;    // obviously at some point, 1 is a valid square

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (i==0 && j==0) dp[i][j] = matrix.get(i).get(j);
                else if (i==0) dp[0][j] = matrix.get(0).get(j);
                else if (j==0) dp[i][0] = matrix.get(i).get(0);
                else if (matrix.get(i).get(j) == 1) {
                    // the recurrence relation is that we need to get the
                    // min((i-1,j-1), (i-1,j), (i,j-1)) - the square have to match, otherwise, 0
                    // if they match, then 2. the next square will also be computed with 2, then 3
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    area = Math.max(area, dp[i][j]);
                }
            }
        }

        return area * area;    // since this is a square
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < matrixLength; i++) {
            matrix.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        int res = maximalSquare(matrix);
        System.out.println(res);
    }
}
