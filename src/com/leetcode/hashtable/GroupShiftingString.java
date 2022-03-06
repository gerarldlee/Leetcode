package com.leetcode.hashtable;

import java.util.*;

public class GroupShiftingString {

    /*
    The idea is to form a key to a map, in order to group them.

    Consider acf and pru. Now notice the differnce between each characters.
    acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers ASCII
    difference to form key.

    Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, just make it
    positive(rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.
     */

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            String key = getKey(str);
            List<String> list = map.getOrDefault(key, new LinkedList<>());
            list.add(str);  // we add str, not the hash key that we created
            map.put(key, list);
        }
        return new LinkedList<>(map.values());
    }

    private String getKey(String str) {
        char[] chars = str.toCharArray();
        char offset = chars[0];
        StringBuilder key = new StringBuilder();
        for (int i=1; i < chars.length; i++) {
            int diff = chars[i] - offset;
            key.append(diff < 0 ? diff + 26 : diff);   // adding 26 letters, will shift it back to a positive cycle
            key.append(" ");    // space will separate those m - a = 13, and b - a = 1, d - a = 3 which is also 13
        }
        return key.toString();
    }
}
