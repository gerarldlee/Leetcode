package com.leetcode.queuestack;

import java.util.ArrayDeque;
import java.util.Queue;

public class Calculator {

    public static void main(String[] a) {
        
        String expr = "2+3*((3-2*5)+4)/2"; //2+-9/2 = 2-4 = -2

//        System.out.println(calculate("3-2*5")); // -7
        System.out.println(calculate(expr));
    }
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Queue<Character> queue = new ArrayDeque<>();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            queue.add(c);
        }
        queue.add('+');
        return helper(queue);
    }

    public static int helper(Queue<Character> queue) {
        int result = 0, prev = 0;
        int operand = 0;
        char operator = '+';
        while (queue.size() > 0) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                operand = operand * 10 + (c - '0');
            }
            if (c == '(') {
                operand = helper(queue);
            }
            if (!Character.isDigit(c)) {
                switch (operator) {
                    case '+':
                    case '-':
                        result += prev;
                        prev = (operator == '-' ? -operand : operand);
                        break;
                    case '/':
                        prev /= operand;
                        break;
                    case '*':
                        prev *= operand;
                        break;
                }

                operand = 0;
                operator = c;
            }
            if (c == ')') break;
        }
        return result + prev;
    }

}