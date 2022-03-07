package com.leetcode.facebook;

import java.util.Stack;

public class MinAddParenthesisValid {

    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) return 0;

        Stack<Character> parenthesis = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && !parenthesis.isEmpty() && parenthesis.peek() == '(')
                parenthesis.pop();
            else
                parenthesis.push(c);
        }
        return parenthesis.size();
    }
}
