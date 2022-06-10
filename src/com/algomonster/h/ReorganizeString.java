package com.algomonster.h;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {

    // the problem is that in a given string, the letters will have to be reorganized so that no 2 adjacent characters
    // are the same

    // the idea is to:
    // count all the characters and put them into a map
    // create a max-heap that takes in the characters count
    // then greedily remove the first 2 max heap, and put them into a string
    // at the same time, reduce the counts of that characters
    // now, when max-heap.size is just 1 character left
    // check if the count of that character from the map, is 1 or more
    // if more, then return empty as we cannot put adjacent
    // if 1, then put it into the string and return the string
    public String reorganizeString(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0)+1);
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> count.get(b)-count.get(a));
        queue.addAll(count.keySet());
        StringBuilder sb = new StringBuilder();
        while (queue.size() >= 2) {
            char a = queue.poll();
            char b = queue.poll();
            sb.append(a).append(b);
            count.put(a, count.get(a)-1);   // deduct 1 because we poll() one
            count.put(b, count.get(b)-1);
            if (count.get(a) > 0) queue.add(a); // put it back when count > 0
            if (count.get(b) > 0) queue.add(b);
        }
        if (queue.size() == 1) {
            char c = queue.poll();
            if (count.get(c) > 1) return "";
            else sb.append(c);
        }
        return sb.toString();
    }
}
