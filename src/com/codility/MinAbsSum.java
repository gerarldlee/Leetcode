package com.codility;

import java.util.HashSet;
import java.util.Set;

public class MinAbsSum {

    public static void main(String[] ar) {
        MinAbsSum m = new MinAbsSum();
        System.out.println(m.solution(new int[]{1,5,2,-2}));    // 0
    }

    public int solution(int[] A) {

        int M = 0;
        int S = 0;
        for (int i=0; i<A.length; i++) {
            A[i] = Math.abs(A[i]);
            M = Math.max(M, A[i]);
            S += A[i];
        }

        int[] dp = new int[S+1];
        dp[0] = 1;

        for (int i=0; i<A.length; i++) {
            for (int j=S; j>-1; j--) {
                if (dp[j] == 1 && j + A[i] <= S) {
                    dp[j + A[i]] = 1;
                }
            }
        }

        int result = S;
        int S2 = S / 2;
        for (int i=0; i<S2 + 1; i++) {
            if (dp[i] == 1) {
                result = Math.min(result, S - 2 * i);
            }
        }

        Set<Integer> array = new HashSet<>();
        Integer[] a = array.toArray(new Integer[0]);

        return result;
    }
}
