package com.leetcode.recursion2;

public class HeapSort2 {
    /*
    Same with the previous code, but

    Building a heap is nlogn. Each value is added as a leaf node, then it bubbles up the heap to its correct location.
    To turn a heap into a sorted array, remove the root (which is the current max value), replace it with a leaf node,
    then let the new root sink down to its correct location. This is also nlogn.

    Rather than iteratively adding nodes to a heap, we can merge smaller heaps until only one is left.
    This ends up being O(n) for building the heap.
     */

    // time: O(N) for building heap, O(N log N) for sorting array
    public int[] heapSortImproved(int[] nums) {
        //build the heap
        for(int i=nums.length-1;i>=0;i--)
            //merge the 2 sub-heaps below i, by bubbling nums[i] down to the correct location
            siftDown(nums,i,nums.length);
        //turn it into a sorted list
        for(int i=nums.length-1;i>0;i--){
            //remove the root, and put the last leaf at the root of the heap
            int tmp = nums[i];
            nums[i]=nums[0];
            nums[0] = tmp;
            //bubble the new root down to the correct location
            siftDown(nums,0,i);
        }
        return nums;
    }
    void siftDown(int[] nums, int start, int end){
        int val = nums[start];
        int i = start, next = 2*i+1;
        while(next<end){
            if(next+1<end && nums[next+1]>nums[next])
                next++;
            if(nums[next]<=val)
                break;
            nums[i]=nums[next];
            i=next;
            next = 2*i+1; //2*i+1, 2*i+2 are the children of i
        }
        nums[i]=val;
    }

/*
Building a heap is nlogn. Each value is added as a leaf node, then it bubbles up the heap to its correct location.
To turn a heap into a sorted array, remove the root (which is the current max value), replace it with a leaf node,
then let the new root sink down to its correct location. This is also nlogn.
 */
    // time: O(N log N) for building heap, O(N log N) for sorting array
    public int[] heapSort(int[] nums) {
        //build the heap
        for(int i=1;i<nums.length;i++){
            int val = nums[i];
            int j=i, next = (j-1)/2; //(j-i)/2 is the index of this location's parent
            while(j>0 && nums[next]<val){
                nums[j]=nums[next];
                j=next;
                next = (j-1)/2;
            }
            nums[j]=val;
        }

        //turn it into a sorted list
        for(int i=nums.length-1;i>0;i--){
            int val = nums[i];
            nums[i]=nums[0];

            int j = 0, next = 1;
            while(next<i){
                if(next+1<i && nums[next+1]>nums[next])
                    next++;
                if(nums[next]<=val)
                    break;
                nums[j]=nums[next];
                j=next;
                next = 2*j+1; //2*j+1, 2*j+2 are the children of j
            }
            nums[j]=val;
        }
        return nums;
    }
}
