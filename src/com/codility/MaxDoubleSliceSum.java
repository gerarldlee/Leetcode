package com.codility;

public class MaxDoubleSliceSum {

	public int solution(int[] A) {
		
		// kadane for both sides
		int[] k1 = new int[A.length];
		int[] k2 = new int[A.length];
		
		// starts with 1, and N-1 since it disregards 0 and 7
		for (int i=1; i<A.length-1; i++) {
			k1[i] = Math.max(0, k1[i-1] + A[i]);
		}
		for (int i=A.length-2; i>1; i--) {
			k2[i] = Math.max(0, A[i] + k2[i+1]);
		}
		
		int max = 0;
		for (int i=1; i<A.length-1; i++) {
			max = Math.max(max, k1[i-1] + k2[i+1]);
		}
		return max;
	}
	
	public static void main(String[] argv) {
		MaxDoubleSliceSum m = new MaxDoubleSliceSum();
		System.out.println(m.solution(new int[] {3,2,6,-1,4,5,-1,2}));
		System.out.println(m.solution(new int[] {0,10,-5,-2,0}));
	}
}
