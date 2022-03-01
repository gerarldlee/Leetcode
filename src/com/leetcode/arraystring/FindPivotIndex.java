package com.leetcode.arraystring;

public class FindPivotIndex {

    /*
    Intuition:
    The left sums of the index has to be equal to the right sums of the index, so we get all the right sums first
    Then as we move / increase the left sum, we decrease the right sum
    The value of the index is excluded in the right sum, so it would be rightsum = total sum - num[i]
    And the value of the leftsum starts from 0, increments by num[i]

    Time: o(n) space: o(1)
     */
    public int pivotIndex (int[] nums) {

        int leftsum = 0;
        int rightsum = 0;
        for (int n : nums) rightsum += n;

        for (int i=0; i< nums.length; i++) {
            rightsum -= nums[i];
            if (leftsum == rightsum) return i;
            leftsum += nums[i];
        }
        return -1;
    }
}
