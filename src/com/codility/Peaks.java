package com.codility;

import java.util.ArrayList;
import java.util.List;

public class Peaks {

	public int solution(int[] A) {
		
		if (A.length < 3) return 0;
		
		// determine peaks
		int[] peaksarr = new int[A.length];
		int peak_size = 0;
		List<Integer> peaks = new ArrayList<Integer>();
		
		for (int i=1; i<A.length-1; i++) {
			if (A[i-1] < A[i] && A[i] > A[i+1]) {
				peaksarr[i] = 1;
				peak_size++;
				peaks.add(i);
			}
		}
		
		if (peak_size == 0) return 0;
		
		// get the maximum dividing factor of A.length
		// consider it as the starting point of peaks
		// and then reverse-iterate from there.
		// once all groups have at least 1 peak, then no further need of continuation
//		int max_factor = (int) Math.min(peak_size, Math.ceil(Math.sqrt(A.length)));
		
		int N = A.length;
		
		for (int size = 1; size <= N; size++) {
			
			if (N % size != 0) continue;
			
			int groups = N / size;
			int find = 0;
			boolean success = true;
			
			for (int peak_index : peaks) {
				int peak_position = peak_index / size;
				if (peak_position > find) {
					success = false;
					break;
				}
				else if (peak_position == find) {
					find++;
				}
			}
			if (find != groups) {
				success = false;
			}
			if (success)
				return groups;
		}
		
		return 0;
		
//		for (int factor = max_factor; factor >= 2; factor--) {
//			
//			// if its not divisible, then move to the next
//			if (A.length % factor != 0) continue;
//			
//			// divide the array A into i groups
//			int group_size = A.length / factor;
//			
//			int num_peak_in_group = 0;
//			int i = 0;
//			for (i = 0; i < peaksarr.length; i++) {
//				if (peaksarr[i] == 1) {
//					num_peak_in_group++;
//				}
//				// when we reach end of group, num peak > 0, no point further
//				if ((i+1) % group_size == 0 && num_peak_in_group < 1) break;
//				
//				// reset the peak in group and move to the next group
//				else if ((i+1) % group_size == 0) {
//					num_peak_in_group = 0;
//				}
//			}
//			
//			if (i >= factor) return factor;
//		}
		
//		return 1;
	}
	
	public static void main(String[] argv) {
		Peaks p = new Peaks();
		System.out.println(p.solution(new int[] {1,2,3,4,3,4,1,2,3,4,6,2}));	// 3
		System.out.println(p.solution(new int[] {1,2,1,4,3,4,1,2,3,4,6}));		// 1
		System.out.println(p.solution(new int[] {1,2,1,4,3,4,1,2,3,4}));		// 2
		System.out.println(p.solution(new int[] {1,1,1,1,1,1,1,1,1,1,1}));		// 0
		System.out.println(p.solution(new int[] {1,2,3,4,3,4,1,2,3,4,6,2,5}));	// 1
		System.out.println(p.solution(new int[] {1,2,3,5,7}));	// 0
	}
}
