package com.leetcode.binarysearch;

public class FirstBadVersion {

    int bad_version = 0;
    public FirstBadVersion(int bad_version) {
        this.bad_version = bad_version;
    }
    public boolean isBadVersion(int version) {
        return (version == bad_version);
    }

    // time: o(log n) space = o(1)
    public int firstBadVersion(int n) {

        if (n < 1) return n;

        int min = 1;
        int max = n;
        int potentially_bad = 1;
        while (min <= max) {
            int range = (max - min) / 2;
            int mid = range + min;

            if (isBadVersion(mid)) {
                max = mid - 1;
                potentially_bad = mid;
            }
            else {
                min = mid + 1;
            }
        }
        return potentially_bad;
    }

    public static void main(String[] a) {
        FirstBadVersion b = new FirstBadVersion(1702766719);

        long start = System.currentTimeMillis();
        System.out.println(b.firstBadVersion(2126753390));
        long end = System.currentTimeMillis();
        System.out.printf("%d ms has passed.\n", end - start);
    }
}
