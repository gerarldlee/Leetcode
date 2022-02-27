package com.leetcode.facebook;

public class AddBinary {

    public String solution(String a, String b) {

        if (b.length() > a.length()) {
            return solution(b, a);
        }

        int n = Math.max(a.length(), b.length());

        StringBuilder s = new StringBuilder();
        int add = 0;
        int m = b.length();
        for (int i=0; i<n; i++) {
            char aa = a.charAt(n-i-1);
            char bb = '0';
            if (i < m) {
                bb = b.charAt(m-i-1);
            }

            if (aa == '1') add++;
            if (bb == '1') add++;

            if (add == 0) {
                s.append("0");
            }
            else if (add == 1) {
                s.append("1");
                add = 0;
            }
            else if (add == 2) {
                s.append("0");
                add--;
            }
            // when carry =1, a=1 b =1
            else {
                s.append("1");
                add = 1;
            }
        }
        if (add == 1) {
            s.append("1");
        }
        return s.reverse().toString();
    }

//    public int binaryToDecimal(String a) {
//        int n = a.length();
//        int result = 0;
//        for (int i=0; i < n; i++) {
//            char z = a.charAt(n - i - 1);
//            if (z == '1') {
//                result += Math.pow(2, i);
//            }
//        }
//        return result;
//    }

    public static void main(String[] args) {
	    AddBinary m = new AddBinary();
        System.out.println(m.solution("11", "1"));  // 100
        System.out.println(m.solution("1010", "1011")); // 10101
        String a = m.solution("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
                "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011");
        System.out.println(a); // "110111101100010011000101110110100000011101000101011001000011011000001100011110011010010011000000000"
    }
}
