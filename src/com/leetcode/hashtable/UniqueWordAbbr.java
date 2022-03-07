package com.leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class UniqueWordAbbr {

    private Map<String, String> dictionary = new HashMap<>();

    public UniqueWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            // when theres an edge case where theres the same abbr, but the same words, they will still return true.
            if (this.dictionary.containsKey(abbr) && !this.dictionary.get(abbr).equals(word)) {
                // an edge case where theres the same abbr, but have different words in the dictionary. they
                // automatically return false.
                this.dictionary.put(abbr, this.dictionary.get(abbr) + ", " + word);
            }
            else {
                this.dictionary.put(abbr, word);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        // its unique when the dictionary does not contain the abbr,
        // or when the dictionary's entry contains the same abbr, and the same word
        if (!dictionary.containsKey(abbr) || dictionary.get(abbr).equals(word)) {
            return true;
        }
        return false;
    }

    private String getAbbr(String word) {
        if (word == null || word.length() <= 2) return word;

        StringBuilder sb = new StringBuilder();
        return sb.append(word.charAt(0)).append(word.length()-2).append(word.charAt(word.length()-1)).toString();
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */