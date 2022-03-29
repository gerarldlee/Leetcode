package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsetBacktrack(int[] array) {
//        Arrays.sort(array);
        List<List<Integer>> output = new ArrayList<>();
        backtrack(array, output, new ArrayList<>(), 0);
        return output;
    }

    private void backtrack(int[] array, List<List<Integer>> output, List<Integer> temp, int start) {
        output.add(new ArrayList<>(temp));
        for (int i=start; i<array.length; i++) {
            temp.add(array[i]);
            backtrack(array, output, temp, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public List<List<Integer>> subsetNaive(int[] array) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<>());

        for (int i=0; i<array.length; i++) {
            List<List<Integer>> subset = new ArrayList<>();
            for (int j=0; j < output.size(); j++) {
                List<Integer> newSubset = new ArrayList<>(output.get(j));
                newSubset.add(array[i]);
                subset.add(newSubset);
            }

            for (int j=0; j<subset.size(); j++) {
                output.add(subset.get(j));
            }
        }
        return output;
    }

    public static void main(String[] a) {

        int[] array = new int[] {1,2,3};

        Subsets s = new Subsets();

        System.out.println(s.subsetNaive(array));
        System.out.println(s.subsetBacktrack(array));
    }
}
