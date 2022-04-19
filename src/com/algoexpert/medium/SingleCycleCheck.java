package com.algoexpert.medium;

public class SingleCycleCheck {
    public static boolean hasSingleCycle(int[] array) {
        // Write your code here.
        int nextIndex = 0;
        int visited = 0;
        while (visited < array.length) {
            if (visited > 0 && nextIndex == 0) return false;
            int jumpIndex = array[nextIndex];
            nextIndex = (nextIndex + jumpIndex) % array.length;
            if (nextIndex < 0) {
                nextIndex += array.length;
            }
            visited++;
        }
        return visited == array.length && nextIndex == 0;
    }
}
