import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Calculator {

    public static void main(String[] a) {
        
        String expr = "2+3*((3-2*5)+4)/2"; //2+-9/2 = 2-4 = -2

        System.out.println(calculateGroup(expr));
    }

    public static int calculateGroup(String expr) {
        if (expr == null || expr.length() == 0) return 0;

        List<String> stack = new ArrayList()<>();
        char[] exprs = expr.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<exprs.length; i++) {
            char c = exprs[i];
            if (c == '(') {
                stack.add(sb.toString());
                sb.setLength(0);
            }
            else if (c == ')') {
                int group = calculate(sb.toString());
                sb.setLength(0);
                sb.append(group);
            }
            else {
                sb.append(c);
            }
        }

        // create a string from the list
        String ex = stack.stream().collect(Collectors.joining());
        return calculate(ex);
    }

    private static int calculate(String expr) {

        if (expr == null || expr.length() == 0) return 0;

        int result = 0;
        int operand = 0, prev = 0;
        int operator = '+';
        char exprChars = expr.toCharArray();
        for (int i=0; i<exprChars.length; i++) {
            if (Character.isDigit(exprChars[i])) {
                operand = operand * 10 + (exprChars[i] - '0');
            }
            char op = exprChars[i];
            if (!Character.isDigit(op) || i == exprChars.length-1) {
                if (operator == '*') {
                    prev *= operand;
                }
                else if (operator == '/') {
                    prev /= operand;
                }
                if (operator == '+' || operator == '-') {
                    result += prev;
                    if (operator == '-') prev -= operand;
                    else prev += operand;
                }
                operand = 0;    // reset it
                operator = op;
            }
        }
        result += prev;
        return result;
    }

}