package com.leetcode.facebook;

import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumber {

    public boolean strobogrammatic(String num) {
        if (num == null || num.length() == 0) return false;

        Map<Character, Character> strobo = new HashMap<>();
        strobo.put('0', '0');
        strobo.put('1', '1');
        strobo.put('8', '8');

        if (num.length() == 1) {
            return strobo.containsKey(num.charAt(0));
        }
        else {
            strobo.put('6', '9');
            strobo.put('9', '6');
        }

        int l = 0, r = num.length()-1;
        while (l <= r) {
            char c = num.charAt(l), d = num.charAt(r);

            if (!strobo.containsKey(c)) return false;

            if (!strobo.get(c).equals(d)) return false;

            l++;
            r--;
        }
        return true;
    }
}
