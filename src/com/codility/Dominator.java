package com.codility;

import java.util.HashMap;
import java.util.Map;

public class Dominator {
    public int solution(int[] A) {
        if (A.length < 1) return -1;
        if (A.length < 2) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> dominators = new HashMap<>();

        int median = (A.length / 2) + 1;
        int highest_occurance = 0;
        int dominator_index = -1;

        for (int i=0; i<A.length; i++) {
            int value = A[i];

            int occurance = map.getOrDefault(value, 1);
            map.put(value, ++occurance);

            if (occurance > median && occurance > highest_occurance) {
                highest_occurance = occurance;
                dominator_index = i;
            }
        }

        return dominator_index;
    }
    
    public static void main(String[] argv) {
    	Dominator d = new Dominator();
    	System.out.println(d.solution(new int[]{5,5,5,6,6,6}));
    }
}
