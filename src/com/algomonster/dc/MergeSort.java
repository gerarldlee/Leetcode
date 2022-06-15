package com.algomonster.dc;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public List<Integer> mergeSort(List<Integer> nums) {
        if (nums.size() <= 1) return nums;

        int size = nums.size();
        int mid = size / 2;

        List<Integer> leftCopy = new ArrayList<>(nums.subList(0, mid));
        List<Integer> rightCopy = new ArrayList<>(nums.subList(mid, size));

        List<Integer> leftSort = mergeSort(leftCopy);
        List<Integer> rightSort = mergeSort(rightCopy);

        return merge(leftSort, rightSort);
    }

    public List<Integer> merge(List<Integer> left, List<Integer> right) {
        int l=0, r=0;
        List<Integer> sorted = new ArrayList<>();
        while (l < left.size() || r < right.size()) {
            if (l >= left.size() || (r < right.size() && left.get(l) > right.get(r))) {
                sorted.add(right.get(r));
                r++;
            } else {
                sorted.add(left.get(l));
                l++;
            }
        }
        return sorted;
    }

    public static void main(String[] argv) {
        MergeSort sort = new MergeSort();
        System.out.println(sort.mergeSort(List.of(5,4,3,2,1)));
    }
}
