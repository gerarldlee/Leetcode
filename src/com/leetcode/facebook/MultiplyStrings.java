package com.leetcode.facebook;

public class MultiplyStrings {

    /*
    need to use the chinese multiplication

     */
    public String multiplyLarge(String num1, String num2) {
        int n = num1.length() + num2.length();
        int[] result = new int[n];

        int r = 0, m = 0, z = 0;
        for (int i=num1.length()-1; i>=0; i--) {
            for (int j=num2.length()-1; j>=0; j--) {
                int i_digit = num1.charAt(i) - '0';
                int j_digit = num2.charAt(j) - '0';

                m = j_digit * i_digit + result[i+j+1];
                z = m % 10;
                r = m / 10;

                result[i+j+1] = z;
                result[i+j] = result[i+j] + r;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            if (i != 0 || sb.length() != 0)
                sb.append(i);
        }
        return sb.toString().length() == 0 ? "0" : sb.toString();
    }


    /*
    Intuition:
    iterate each digit in num1, and multiply it by its base power. add all of them together.

    do the same for num2

    multiply them

    ** does not work with large numbers

    Time: O(N)
    Space: O(1)
     */
    public String multiply(String num1, String num2) {
        long num_1 = convert(num1);
        long num_2 = convert(num2);
        long result = num_1 * num_2;
        return String.valueOf(result);
    }

    private long convert(String num) {
        long result = 0;
        for (int i=0; i<num.length(); i++) {
            result = result * 10 + (num.charAt(i) - '0');
        }
        return result;
    }

    public static void main(String[] a) {
        MultiplyStrings m = new MultiplyStrings();
        System.out.println(m.multiply("123456789", "987654321"));

        System.out.println(m.multiplyLarge("123", "456"));

        System.out.println(m.multiplyLarge("498828660196", "840477629533"));

    }
}
