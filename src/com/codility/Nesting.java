package com.codility;

import java.util.Stack;

public class Nesting {

	public int solution(String S) {
        if (S.isEmpty()) return 1;

        Stack<Character> parenthesis = new Stack<>();
        for (int i=0; i<S.length(); i++) {
            char p = S.charAt(i);

            if (p == ')') {
                if (parenthesis.isEmpty() || parenthesis.pop() != '(') {
                    return 0;
                }
                continue;
            }
            parenthesis.push(p);
        }

        return 1;
	}
	
	public static void main(String[] argv) {
		Nesting n = new Nesting();
		System.out.println(n.solution("()(()()(((()())(()()))"));
	}
}
