package com.codility;

import java.util.HashMap;
import java.util.Map;

public class EquiLeader {
    public int solution(int[] A) {

        Map<Integer, Integer> map = new HashMap<>();
        int leader_occurance = 1;
        int leader_value = A[0];

        // get the leader value
        for (int i=1; i<A.length; i++) {
            int value = A[i];

            int occurance = map.getOrDefault(value, 1);
            map.put(value, ++occurance);

            if (occurance > leader_occurance) {
                leader_occurance = occurance;
                leader_value = value;
            }
        }
        
        // when theres no leader
        if (leader_occurance <= A.length/2) return 0;
        
        int equi_leader = 0;
        int left_leader_count = 0;
        int right_leader_count = 0;
        
        // find the leader value across the arrays
        for (int i=0; i<A.length; i++) {
        	int left_partition = i+1;
        	int right_partition = A.length-i-1;
        	if (A[i] == leader_value) {
        		left_leader_count++;
        		right_leader_count = leader_occurance - left_leader_count;
        	}
        	
        	if ((left_leader_count > left_partition/2) && (right_leader_count > right_partition/2)) {
        		equi_leader++;
        	}
        }

        return equi_leader;
    }
    
    public static void main(String[] argv) {
    	EquiLeader d = new EquiLeader();
    	System.out.println(d.solution(new int[]{4,3,4,4,4,2}));
    }
}
