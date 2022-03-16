package com.codility;

public class CountFactors {

	public int solution(int N) {
		
		if (N == 0) return 0;
		
		int factors = 0;
		
		double sqrtN = Math.sqrt(N);
		if (Math.pow(sqrtN, 2) == N) {
			factors++;
		}
		int sqrt = (int) Math.ceil(sqrtN);
		
		for (int i=1; i<sqrt; i++) {
			if (N % i == 0) {
				factors += 2;
			}
		}
		return factors;
	}
	
	public static void main(String[] argv) {
		CountFactors c = new CountFactors();
		System.out.println(c.solution(24));
		System.out.println(c.solution(2));
		System.out.println(c.solution(100));
	}
}
