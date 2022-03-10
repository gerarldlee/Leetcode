package com.leetcode.recursion2;

public class BubbleSort {

    /*
    The idea is to compare each element with the next element. If the next element is less than that of current element,
    swap them. Do this repeatedly until we finish the nums.length - 1 on the first iteration cycle.
    We are sure that the last most element is the biggest, so for every iteration cycle, there will be a - 1 in the
    condition of the loop.

    Time: o(n-1) + o(n-2) ... o(n-(n-1)) = o(n(n+1)/2)
    Space: o(1) - we did not use any space
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int cycle = 1;
        for (int i=0; i < nums.length-cycle; i++) {
            if (nums[i] > nums[i+1]) {
                int tmp = nums[i+1];
                nums[i+1] = nums[i];
                nums[i] = tmp;
            }
            if (i == nums.length-cycle-1) {
                cycle++;
                i = -1;
            }
        }
//        return Arrays.stream(nums).boxed().collect(Collectors.toList());
        return nums;
    }
}
