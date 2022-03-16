package com.codility;

public class MaxProfit {

	public int solution(int[] A) {
		
		int currentMin = A[0], currentMinIndex = 0;
		int currentMax = A[0], currentMaxIndex = 0;
		int maxProfit = 0;
		
		for (int i=1; i<A.length; i++) {
			
			// check if the value is the minimum
			if (currentMin > A[i] && i > currentMinIndex) {
				currentMin = A[i];
				currentMinIndex = i;
				// setting the value of minimum, will cause the max to reset. we want to ignore the loss
				currentMax = A[i];
				currentMaxIndex = i;
			}
			// else check if its the maximum
			else if (currentMax < A[i] && i > currentMaxIndex) {
				currentMax = A[i];
				currentMaxIndex = i;
				maxProfit = Math.max(maxProfit, currentMax - currentMin);
			}
	
		}
		
		return maxProfit;
	}
}
