package com.leetcode.facebook;

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class ReadNCharsGivenRead42 extends Reader4 {

    int read_count = 0;
    int write_count = 0;
    char[] buffer = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int count = 0;

        while (count < n) {

            // when theres nothing left to write from the buffer, then read something from File
            if (write_count == 0) {
                read_count = read4(buffer);
            }

            // when theres no more read, stop the process
            if (read_count == 0) break;

            // write whats from the buffer, up to n elements, but if n > read_count,
            // limit it up to what was read_count only
            while (count < n && write_count < read_count) {
                buf[count++] = buffer[write_count++];
            }

            // when the write has finished writing 4 characters read from the buffer, reset it to 0
            if (write_count >= read_count) write_count = 0;
        }

        return count;
    }
}
