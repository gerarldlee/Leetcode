package com.algomonster.ads;

import java.util.HashMap;

public class Trie {
    // add any other values you want to maintain as a field here
    char value;
    HashMap<Character, Trie> children = new HashMap<>();

    public Trie(char value) {
        this.value = value;
    }

    void insert(String s, int idx) {
        // idx: index of the current character in s
        if (idx == s.length()) {
            return;
        }
        if (children.containsKey(s.charAt(idx))) {
            children.get(s.charAt(idx)).insert(s, idx + 1);
        } else {
            children.put(s.charAt(idx), new Trie(s.charAt(idx)));
            children.get(s.charAt(idx)).insert(s, idx + 1);
        }
    }
}
