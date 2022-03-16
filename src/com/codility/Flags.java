package com.codility;

import java.util.ArrayList;
import java.util.List;

public class Flags {
    
    public int solution(int[] A) {

        // determine a peak
        List<Integer> peaks = new ArrayList<>();
        for (int i=1; i<A.length-1; i++) {
            if (A[i-1] < A[i] && A[i] > A[i+1]) {
                peaks.add(i);       // adds the index of the array that peaks
            }
        }
        if (peaks.isEmpty()) return 0;
        if (peaks.size() < 1) return peaks.size();

        int max_flags = (int) Math.min(Math.ceil(Math.sqrt(A.length)), peaks.size());
        int current_counter_flag = 1, max_flags_set = 0;
        for (int flags=max_flags; flags >= 1; flags--) {
            int last_flag_index = 0;
            current_counter_flag = 1;
            for (int i = 1; i < peaks.size(); i++) {
                if (peaks.get(i) - peaks.get(last_flag_index) >= flags && current_counter_flag < flags) {
                    current_counter_flag++;
                    last_flag_index = i;
                }
            }
            if (current_counter_flag < max_flags_set) {
                return max_flags_set;
            }
            else if (max_flags_set < current_counter_flag) {
                max_flags_set = current_counter_flag;
            }
        }

        return max_flags_set;
    }

    public static void main(String[] argv) {

        Flags f = new Flags();
        System.out.println(f.solution(new int[] {1,5,3,4,3,4,1,2,3,4,6,2}));
    }
}
