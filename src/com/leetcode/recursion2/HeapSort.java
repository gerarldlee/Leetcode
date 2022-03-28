package com.leetcode.recursion2;

import java.util.Arrays;

public class HeapSort {

    /*
    uses a heap binary tree to sort things out.
    - the value of each node, is no greater than the value of its child nodes
    - is a complete binary tree - all levels are completely filled except the lowest ones, and is filled from left to right
     */
    public int[] sort(int[] nums) {

        heapify(nums);
        int size = nums.length;
        while (size > 0) {
            int largestValue = removeMaxValue(nums, size);
            size--;
            nums[size] = largestValue;
        }
        return nums;
    }

    // creates a heap binary tree with max as priority
    // MAX priority is just sifting down
    // if we do MIN priority, then we need to sift up
    private void heapify(int[] nums) {
        // we only heapify non-leaf nodes i.e. leaf nodes are already in the bottom down
        for (int i=nums.length/2 -1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }
    }

    // removes the largest value
    private int removeMaxValue(int[] nums, int size) {
        int largestValue = nums[0];
        // move the last item in the heap to the root partition
        nums[0] = nums[nums.length-1];
        siftDown(nums, size, 1);
        return largestValue;
    }

    // sifts down the starting index value down to its children nodes
    private void siftDown(int[] nums, int size, int node) {
        int l_child = node * 2;		// left child of k = k * 2, right = left + 1

        // check if left child is valid
        // (doesn't make sense for the right child since we are achieving complete binary tree)
        while (l_child > 0 && l_child < size) {
            int smaller_child = l_child;
            int r_child = node * 2 + 1;
            // assign the smaller child if right or left
            if (r_child > 0 && nums[r_child] < nums[l_child]) {
                smaller_child = r_child;
            }
            // if k > smaller child, swap it
            if (nums[smaller_child] < nums[node]) {
                swap(nums, smaller_child, node);
                node = smaller_child;
                l_child = node * 2;
            }
            else {
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] ar) {
        HeapSort q = new HeapSort();
        int[] array = (new int[] {56,3,7,9,3,4});

        System.out.println(Arrays.toString(q.sort(array)));
    }
}
