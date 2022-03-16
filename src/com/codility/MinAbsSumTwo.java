package com.codility;

import java.util.Arrays;

public class MinAbsSumTwo {
    public int solution(int[] A) {
        // write your code in Java SE 8

        Arrays.sort(A);

        int head = A.length-1;
        int tail = 0;

        int absSum = Math.abs(A[tail] + A[head]);
        while (tail <= head) {
            int sum = A[tail] + A[head];
            absSum = Math.min(absSum, Math.abs(sum));

            if (sum <= 0) {
                tail++;
            }
            else {
                head--;
            }
        }
        return absSum;
    }

    public static void main(String[] g) {
        MinAbsSumTwo m = new MinAbsSumTwo();
        System.out.println(m.solution(new int[]{1,4,-3}));
        System.out.println(m.solution(new int[]{-8,4,5,-10,3}));
    }
}
