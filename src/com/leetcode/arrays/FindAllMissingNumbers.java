package com.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindAllMissingNumbers {

    // time: o(n), space: o(n), but too big in memory and time consumption on BIG array
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // create a list of 1 to n
        // remove each item in the list, once iterating over the same value in nums
        Set<Integer> r = IntStream.range(1, nums.length + 1).boxed().collect(Collectors.toSet());
        for (int n : nums) {
            r.remove(Integer.valueOf(n));
        }
        return new ArrayList<>(r);
    }

    // time: o(n), space: o(1)
    public List<Integer> findDisappearedNumbers1(int[] nums) {

        // we know that values in nums are in the range 1..n
        // we iterate 1..nums
        // we tag the num[i] as visited, via making it negative number
        for (int i=0; i< nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (nums[num-1] > 0) nums[num-1] *= -1;
        }

        List<Integer> result = new ArrayList<>();

        // we iterate over 1..n and check if its value is negative, if not, we write that to the list
        for (int i=0; i< nums.length; i++) {
            if (nums[i] > 0)
                result.add(i+1);
        }
        return result;
    }

    // time: o(n + m), space: o(1)
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        for (int i=0; i< nums.length; i++) {
            while (nums[i] != i+1 && nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i=0; i< nums.length; i++) {
            if (nums[i] != i+1)
                result.add(i+1);
        }
        return result;
    }

    private void swap(int[] nums, int idx, int idx2) {
        int z = nums[idx2];
        nums[idx2] = nums[idx];
        nums[idx] = z;
    }

    public static void main(String[] a) {
        FindAllMissingNumbers f = new FindAllMissingNumbers();
//        f.findDisappearedNumbers(new int[]{1,1});
//        f.findDisappearedNumbers(new int[]{4});
        f.findDisappearedNumbers2(new int[]{4,3,2,7,8,2,3,1});
    }
}
