package com.codility;

public class MaxSliceSum {

	public int solution(int[] A) {
		
		int max = A[0];
		int maxSum = A[0];
		
		for (int i=0; i<A.length; i++) {
			
			max = Math.max(A[i], max + A[i]);
			maxSum = Math.max(maxSum, max);
		}
		return maxSum;
	}
}
