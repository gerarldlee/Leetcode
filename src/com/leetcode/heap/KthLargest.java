package com.leetcode.heap;

import java.util.PriorityQueue;

public class KthLargest {

    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((a, b) -> (a - b));		// min-heap

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);				// adds value in ascending order
            if (heap.size() > k)
                heap.poll();			// removes the mininum value / root node
        }

        // what remains to the top, is the kth largest
        return heap.poll();
    }
}
