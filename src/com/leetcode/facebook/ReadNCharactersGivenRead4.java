package com.leetcode.facebook;

public class ReadNCharactersGivenRead4 extends Reader4 {

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {

        int write = 0;

        while (true) {
            char[] c_buff = new char[4];
            int i = read4(c_buff);

            if (i == 0) break;

            for (int j=0; j < i && write < n; j++) {
                buf[write++] = c_buff[j];
            }

            if (write == n) break;
        }

        return write;
    }
}
