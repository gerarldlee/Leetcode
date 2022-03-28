package com.leetcode.facebook;

public class Reader4 {

    // abstract method
    int read4(char[] buff) {
        for (int i=0; i<4; i++) {
            buff[i] = (char) ('a' + i);
        }
        return 4;
    }

}
