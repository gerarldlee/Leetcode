package com.codility;

public class FibFrog {

    public int solution(int[] A) {
        // get the fibs
        int[] fibs = new int[] { 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025};

        if (A.length <= 2) return 1;        // any length that's less than or equal to 2, is jumpable with 1 step

        int previous_step_index = -1;
        int step_taken = 0;

        // determine if the A.length is a valid fibonacci, it can jump just 1,
        // otherwise determine the maximum fibonacci number that it can jump
        // and determine the very first valid fibonacci jump it can do
        int max_fib = fibs[fibs.length-1];
        for (int i=0; i<fibs.length; i++) {
            int fib = fibs[i]-1;
            if (fib == A.length) return 1;
            if (fib < A.length) {
                max_fib = i;
            }
        }

        int previous_step_taken = step_taken;
        while(true) {
            for (int i = max_fib; i >= 0; i--) {
                int next_position = fibs[i] + previous_step_index;

                if (next_position > A.length) continue;

                else if (next_position == A.length) return step_taken + 1;

                else if (next_position < A.length && A[next_position] == 1) {
                    step_taken++;
                    previous_step_index += fibs[i];
                    break;
                }
            }
            if (step_taken == previous_step_taken) {
                return -1;
            }
            else {
                previous_step_taken = step_taken;
            }
        }
    }

    public static void main(String[] a) {
        FibFrog f = new FibFrog();
        System.out.println(f.solution(new int[] {0,0}));  // 1
        System.out.println(f.solution(new int[] {0,0,0,0}));  // 1
        System.out.println(f.solution(new int[] {0,0,0,1,1,0,1,0,0,0,0}));  // 3
        System.out.println(f.solution(new int[] {1,1,1,1,1,1,1,1,1,1,1}));  // 3
    }
}
