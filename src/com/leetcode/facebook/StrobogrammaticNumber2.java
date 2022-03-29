package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.List;

public class StrobogrammaticNumber2 {

    public List<String> findStrobogrammatic(int n) {
        List<String> cur, ans;
        // if its odd, return the single digit core-strobo, else if its 0, or even, return nothing, or continue
        ans = (n % 2 == 0 ? List.of("") : List.of("0", "1", "8"));

        if (n < 2) return ans;

        while (n > 1) {
            cur = ans;
            ans = new ArrayList<>();

            // for every item in the ans, pad it with strobo
            for (String i : cur) {
                if (n > 3) ans.add('0' + i + '0');  // 0 will only be effective if n>3, otherwise theres no 00
                ans.add('1' + i + '1');
                ans.add('8' + i + '8');
                ans.add('6' + i + '9');
                ans.add('9' + i + '6');
            }
            n -= 2;
        }

        return ans;
    }

    public static void main(String[] a) {
        StrobogrammaticNumber2 s = new StrobogrammaticNumber2();
        s.findStrobogrammatic(4);
    }
}
