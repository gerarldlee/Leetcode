package com.leetcode.recursion2;

import java.util.Arrays;

public class HeapSort {

    /*
    uses a heap binary tree to sort things out.
    - the value of each node, is no greater than the value of its child nodes
    - is a complete binary tree - all levels are completely filled except the lowest ones, and is filled from left to right
     */
    public int[] sort(int[] nums) {

        // Heapify - creates a max-heap binary tree
        // - MAX priority is sifting down
        // - MIN priority, is sifting up
        // we only heapify non-leaf nodes i.e. leaf nodes are already in the bottom down
        for (int i=nums.length/2 -1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }

        // because its already a max-heap i.e. largest node at the root
        // we move / swap the largest nums[0] to the end of the array, and shorten the array by one
        for (int i=nums.length-1; i>=0; i--) {
            swap(nums, 0, i);
            siftDown(nums, i, 0);
        }

        return nums;
    }

    /*
    Sifting down - starts with a parent node, to move or swap the node value down to its children nodes by
                    successively exchanging the value with the smaller of its 2 children, until the value is
                    less than both its children, or reaches the leaf

    Sifting up - starts with the leaf nodes, to move / swap the node value up by successively exchanging
                    the value to the value of its parent, until its value is less than its parent or reaches the root
     */
    private void siftDown(int[] nums, int length, int parent) {
        /*
        Childs of parent:
        - left = 2 * parentIndex + 1 (for a 0th index) or = 2 * parentIndex (for a 1th index array)
        - right = left + 1
         */
        int leftChild = parent * 2 + 1;
        int rightChild = leftChild + 1;

        // check if left child is valid, and continue to sift down until it reach the leaf node
        // or when parent < the smallest child
        // BUT since we are doing a max-heap, we need a decreasing order, ie. largest child until parent >= largest
        while (leftChild > 0 && leftChild < length) {

            int largerChild = leftChild;
            if (rightChild < length && nums[rightChild] > nums[leftChild]) {
                largerChild = rightChild;
            }

            // if parent > the smaller child, then swap it, otherwise break
            if (nums[parent] >= nums[largerChild]) break;

            // swap the larger child to its parent so that it becomes the new parent
            swap(nums, largerChild, parent);
            parent = largerChild;
            leftChild = parent * 2 + 1;
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

        System.out.println(Arrays.toString(array) + " => " + Arrays.toString(q.sort(array)));
    }

}
