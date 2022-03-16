package com.codility;

import java.util.Arrays;

public class Ladder {

    public int[] solution(int[] A, int[] B) {

        int[] fib = new int[A.length+2];
        fib[0] = 1;
        fib[1] = 1;
        fib[2] = 2;
        for (int i=3; i <= A.length; i++) {
            fib[i] = (fib[i-1] + fib[i-2]) % (1 << 30);
        }

        int[] result = new int[A.length];
        for (int i=0; i< A.length; i++) {
//            result[i] = fib[A[i]] % (1 << B[i]);
            result[i] = fib[A[i]] % (int) (Math.pow(2, B[i]));
        }

        return result;
    }

    public static void main(String[] a) {
        Ladder l = new Ladder();
        System.out.println(Arrays.toString(l.solution(new int[] {4,4,5,5,1}, new int[] {3,2,4,3,1})));
    }
}
