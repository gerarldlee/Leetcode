package com.leetcode.hashtable;

import java.util.*;

public class GroupAnagrams {

    /**
     * The idea is given an array of strings, we have to sort each string in the array.
     * Once we have sorted that out, we put it into a map that contains unique sorted strings. This represents the group
     * If the map does not contain this key, then we insert this key, with the following value:
     * - We create another list of string and put this into as the unique value of that group.
     * - We add this to the group 2D array.
     * If the map already contains this key, then we just get that array list from the unique array list and update them
     * Since we have already inserted it into the group, the group will be auto-updated.
     *
     * Time: o((k log(k)) * n) where k = # of characters for each string and n = # of strings in the array
     * Space: O(2 * N K) or O(NK)
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> group = new ArrayList<>();
        Map<String, List<String>> uniques = new HashMap<>();

        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String cStr = new String(c);

            if (!uniques.containsKey(cStr)) {
                List<String> tempGroup = new ArrayList<>();
                tempGroup.add(s);
                uniques.put(cStr, tempGroup);
                group.add(tempGroup);
            }
            else {
                List<String> tmpList = uniques.get(cStr);
                tmpList.add(s);
            }
        }

        return group;
    }

}
