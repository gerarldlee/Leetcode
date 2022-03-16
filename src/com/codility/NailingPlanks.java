package com.codility;

import java.util.*;

public class NailingPlanks {
    public static void main(String[] ar) {
        NailingPlanks m = new NailingPlanks();
        System.out.println(m.solution(
                new int[] {4,1,5,8},
                new int[] {5,4,9,10},
                new int[] {4,6,7,10,2,2}));
    }

    class Nail implements Comparable<Nail> {
        int index;
        int value;
        public Nail(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Nail{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Nail) {
                Nail o = (Nail) obj;
                return this.value == o.value;
            }
            return false;
        }

        @Override
        public int compareTo(Nail o) {
            return value - o.value;
        }
    }

    class Plank implements Comparable<Plank> {
        int left;
        int right;
        public Plank(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Plank o) {
            return left - o.left;
        }

        @Override
        public String toString() {
            return "Plank{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public int solution(int[] A, int[] B, int[] C) {

        List<Plank> planks = new ArrayList<>();
        for (int i=0; i<A.length; i++) {
            planks.add(new Plank(A[i], B[i]));
        }
        Collections.sort(planks);

        // remove the planks that completely encompass the next plank
        for (int i=0; i< planks.size()-1; i++) {
            if (i > 0 && planks.get(i).right > planks.get(i+1).right) {
                planks.remove(i--);
            }
        }

        List<Nail> nails = new ArrayList<>();
        for (int i=0; i<C.length; i++) {
            Nail n = new Nail(i, C[i]);
            if (!nails.contains(n)) // remove duplicate nail that contains the same value.
                nails.add(new Nail(i+1, C[i]));
        }
        Collections.sort(nails);

        // get the minimum index that can nail each plank
        int result = 0;
        for (int i=0; i<planks.size(); i++) {
            int minIndex = getMinIndex(planks.get(i), nails);
            if (minIndex == -1) { // if theres no nail index that can nail a plank, means -1
                return -1;
            }
            result = Math.max(result, minIndex);    // why max min?
        }

        return result;
    }

    public int getMinIndex(Plank plank, List<Nail> nails) {

        int max = nails.size() - 1;
        int min = 0;
        int result = -1;
        while (min <= max) {
            int mid = (max + min) / 2;
            if (nails.get(mid).value < plank.left) {
                // increase the mid
                min = mid + 1;
            }
            else if (nails.get(mid).value > plank.right) {
                max = mid - 1;
            }
            else {
                result = mid;
                max = mid -1;
            }
        }
        // if theres no nail found, then exit
        if (result == -1) return -1;

        // find the minimum index, why?
        int min_index = nails.get(result).index;
        for (int i=result+1; i<nails.size(); i++) {
            if (nails.get(i).value <= plank.right) {
                min_index = Math.min(min_index, nails.get(i).index);
            }
            else {
                return min_index;
            }
        }
        return min_index;
    }
}
