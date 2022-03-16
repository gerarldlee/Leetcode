package com.codility;

public class CountDistinctSlices {

    public static void main(String[] ar) {
        CountDistinctSlices c = new CountDistinctSlices();
        System.out.println(c.solution(6, new int[]{3,4,5,5,2})); // 9
    }

    public int solution(int M, int[] A) {
        // write your code in Java SE 8

        boolean[] seen = new boolean[M+1];

        int tail = 0;
        int head = 0;
        int result = 0;

        while (head < A.length && tail < A.length) {

            if (!seen[A[tail]]) {

                result += (tail - head + 1);
                if (result >= 1000000000) return 1000000000;

                seen[A[tail]] = true;
                tail++;
            }
            else {
                seen[A[head]] = false;
                head++;
            }
        }

        return result;
    }
}
