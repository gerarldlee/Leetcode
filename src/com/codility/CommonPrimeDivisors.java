package com.codility;

public class CommonPrimeDivisors {

    public int gcd(int A, int B) {
        if (A % B == 0) return B;
        else return gcd(B, A % B);
    }

    public int solution(int[] A, int[] B) {
        int count = 0;

        // we need to get the factors of each item of A, and B
        for (int i=0; i<A.length; i++) {
            int a = A[i];
            int b = B[i];
            int gcd = gcd(a,b);
            while (gcd(a, gcd) != 1) {
                a = a / gcd(a, gcd);
            }
            while (gcd(b, gcd) != 1) {
                b = b / gcd(b, gcd);
            }
            if (a == 1 && b == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] a) {

        CommonPrimeDivisors c = new CommonPrimeDivisors();
        System.out.println(c.solution(new int[]{15,10,9}, new int[]{75,30,5}));
    }
}
