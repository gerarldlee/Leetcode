package com.leetcode.binarysearch;

public class SearchRotatedArray {

    /*
    Intuition:

    We still do a binary search, but we need to add double checking:
    - if middle is equal, then return
    - if middle is bigger than the starting position; that means left is sorted.
        - so if target is between these 2, since left is already sorted, assign right end to be middle -1
        - else if target is > middle, or outside this boundary, left = mid + 1
    - If middle is less than the right end, that means it is sorted in the right.
        - so if target is between these 2, and the right already sorted, we assign left to be middle + 1
        - else if target is outside this boundary or less than middle, right end = mid - 1

    Time: o( log n) space, o(1)

     */
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > nums[left]) {
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] a) {
        SearchRotatedArray searchRotatedArray = new SearchRotatedArray();

        searchRotatedArray.search(new int[] {3,5,1}, 3);

    }
}
