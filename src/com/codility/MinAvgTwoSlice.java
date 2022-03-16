package com.codility;

public class MinAvgTwoSlice {

	public int solution(int[] A) {
		
//		int[] prefixsum = new int[A.length+1];
//		
//		for (int i=0; i<A.length; ++i) {
//			prefixsum[i+1] = prefixsum[i] + A[i];
//		}
//		
//		// 0 <= P < Q < A.length
//		for (int i=0; i<A.length; ++i) {
//			int P = i;
//			int Q = i+1;
//		}
//		
		double min_avg = (A[0] + A[1]) / 2;
		int min_avg_index = 0;
		
		for (int i=2; i<A.length; ++i) {
			// two
			double two = (A[i] + A[i-1]) / 2;
			if (two < min_avg) {
				min_avg = two;
				min_avg_index = i-1;
			}
			
			// three
			double three = (A[i] + A[i-1] + A[i-2]) / 3;
			if (three < min_avg) {
				min_avg = three;
				min_avg_index = i-2;
			}
		}
		
		return min_avg_index;
	}
	
	public static void main(String[] argv) {
		
		MinAvgTwoSlice m = new MinAvgTwoSlice();
		int answer = m.solution(new int[] {4,2,2,5,1,5,8});
		
		System.out.println(answer);
	}
}
