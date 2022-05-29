package com.grind75;

public class DuplicateNumber {

    // the idea is to have some sort of marking
    // since an element is also 1-n, we can use the index as a mark, and make it negative
    public int findDuplicate(int[] nums) {

        for (int i=0; i<nums.length; i++) {
            int v = Math.abs(nums[i]);

            if (nums[v-1] < 0) return Math.abs(nums[i]);
            else {
                nums[v-1] = -nums[v-1];
            }
        }
        return 0;
    }

    // another answer is to use cycle detection, where
    // slow pointer moves 1 iteration, the next iteration index being the value of the current element
    // fast pointer moves 2 iteration, the next iteration index being the value of the current element, and the next
    //      iteration being the value of the next iteration index element
    // once fast pointer catches up the slow pointer, it has a cycle, and print that out
    // but when slow pointer reaches the Nth array size iteration, it does not have a cycle
}
