package com.leetcode.binarysearchtree;

public class UniqueBinarySearchTrees {

    /*
    Intuition

    The idea is that unique BST is a catalan number. Therefore we apply the catalan formula

    C(0) = 1

    C(n+1) = ((2 (2 n + 1)) / (n + 2)) * C(n)

    Time: O(N)
    Space: O(1)
    */
    public int uniqueBst(int n) {
        long c = 1;
        for(int i=0; i < n; i++) {
//            c = c * ((2 * (2 * i + 1)) / (i + 2));
            c = c * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) c;
    }

    public static void main(String[] a) {
        UniqueBinarySearchTrees n = new UniqueBinarySearchTrees();
        System.out.println(n.uniqueBst(8));
        System.out.println(n.uniqueBstDp(8));
    }

    /*
    For array [1,2,3,4], each element, i, has i-1 combinations of element to its left, and n - i combinations of element
    to its right. We could construct this into DP.

    [1,2,3,4]
    [1] = [1] = 1
    [2] = [1,2] [2,1] = 2
    [3] = [1,2,3] [1,3,2] [2,1,3] [3,2,1] [3,1,2] = 5 = dp[2] + 1 + 2
    [4] = [1,2,3,4] [1,2,4,3] [1,3,2,4] [1,4,3,2] [1,4,2,3] = 5 = dp[3] +
          [2,1,3,4] [2,1,4,3] = 2 +
          [3,1,2,4] [3,2,1,4] = 2 +
          [4,3,2,1] [4,3,1,2] [4,2,1,3] [4,1,2,3] [4,1,3,2] = 5 = 14 in total

     */
    public int uniqueBstDp(int n) {
        if (n < 2) return n;

        int[] dp = new int[n+1];
        dp[0] = 1;  // just a base
        dp[1] = 1;  // only 1 combination from 1 element

        // start with dp[2] index up to n
        for (int i=2; i<=n; i++) {
            // left = 1 up to i-1
            // right = i+1 up to n
            // start from 1 up to i
            for (int j=1; j<=i; j++) {
                int left = j-1;
                int right = i-j;

                // dp[i] = sum of the result of the product of what's in the left and right of j … up to i
                dp[i] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }
}