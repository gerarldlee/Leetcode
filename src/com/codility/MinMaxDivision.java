package com.codility;

public class MinMaxDivision {

    public static void main(String[] ar) {
        MinMaxDivision m = new MinMaxDivision();
        System.out.println(m.solution(3,5,new int[]{2,1,5,1,2,2,2}));
    }

    public int solution(int k, int m, int[] a) {
        // get the max and possible min
        int max = 0;
        int min = 0;

        for (int i=0; i<a.length; i++) {
            max += a[i];
            min = Math.max(min, a[i]);  // at any given point in time, a[i] can be the min
        }

        int possible_value = max;

        // search for the middle, if its divisible by k blocks, return it
        while (min <= max) {
            int mid = (max + min) / 2;

            // if blocks are too small from K, that means the mid is too high, lower it
            if (checkBlocks(mid, a) < k) {
                max = mid - 1;
                // since its divisible, tag the possible result as such
                possible_value = mid;
            }
            else {
                // search for higher mid by setting min to mid + 1
                min = mid + 1;
            }
        }
        return possible_value;
    }

    public int checkBlocks(int mid, int[] a) {
        int sum = 0;
        int blocks = 0;
        for (int i=0; i<a.length; i++) {
            sum += a[i];
            if (sum > mid) {
                blocks++;
                sum = a[i];
            }
        }
        return blocks;
    }
}
