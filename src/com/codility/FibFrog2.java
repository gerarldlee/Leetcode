package com.codility;

/**
 * Knapsack algorithm
 */
public class FibFrog2 {

    public int solution(int[] A) {
        int[] fibs = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025};


        int[] moves = new int[A.length + 1];
        for (int i = 0; i <= A.length; i++) {
            moves[i] = Integer.MAX_VALUE;
        }

        // mask the moves
        for (int i = 0; i < fibs.length; i++) {
            int fib = fibs[i] - 1;
            if (fib < A.length && A[fib] == 1) {
                moves[fib] = 1;
            }
            if (fib == A.length) {
                moves[A.length] = 1;
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                for (int j = 0; j < fibs.length; j++) {
                    int fib_move = i - fibs[j];
                    if (fib_move >= 0 && moves[fib_move] != Integer.MAX_VALUE
                            && moves[i] > moves[fib_move] + 1) {
                        moves[i] = moves[fib_move] + 1;
                    }
                }
            }
//            System.out.println(i + " => " + moves[i]);
        }

        for (int i = A.length; i <= A.length; i++) {

            for (int j = 0; j < fibs.length; j++) {
                int fib_move = i - fibs[j];
                if (fib_move >= 0 && moves[fib_move] != Integer.MAX_VALUE
                        && moves[i] > moves[fib_move] + 1) {
                    moves[i] = moves[fib_move] + 1;
                }
            }

//            System.out.println(i + " => " + moves[i]);
        }

        if (moves[A.length] == Integer.MAX_VALUE)
            return -1;

        return moves[A.length];
    }

    public static void main(String[] a) {
        FibFrog2 f = new FibFrog2();
        System.out.println(f.solution(new int[]{0, 0}));  // 1
        System.out.println(f.solution(new int[]{0, 0, 0, 0}));  // 1
        System.out.println(f.solution(new int[]{0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}));  // 3
        System.out.println(f.solution(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));  // 3
    }
}
