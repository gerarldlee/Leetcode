package com.leetcode.arrays;

public class MaximumScore {
    public int maxSum(int[] nums1, int[] nums2) {

        int MODULO_AMT = 1000000007;
        long runningSum1 = 0, runningSum2 = 0;
        int left=0, right=0;
        long result = 0;

        // compare both elements in the array, when they are equal, compare both runningSum whichever is higher, add them to result
        // when they are not equal, if arr1 element is less than arr2, add them to runningSum1, vice versa for arr2
        while (left < nums1.length && right < nums2.length) {
            int leftElement = nums1[left];
            int rightElement = nums2[right];
            if (leftElement == rightElement) {
                result += Math.max(runningSum1, runningSum2) + leftElement; // we need to add the current element
                result %= MODULO_AMT;       // we need to mod the amount since it might be large
                runningSum1 = runningSum2 = 0;
                left++;
                right++;
            }
            else if (leftElement < rightElement) {
                runningSum1 += leftElement;
                left++;
            }
            else {
                runningSum2 += rightElement;
                right++;
            }
        }

        // because we only compare up to the shorter element of both arrays, we need to compare up to the end of both arrays
        while (left < nums1.length) {
            runningSum1 += nums1[left++];
        }
        while (right < nums2.length) {
            runningSum2 += nums2[right++];
        }

        result += Math.max(runningSum1, runningSum2);
        result %= MODULO_AMT;

        return (int) result;
    }
}
