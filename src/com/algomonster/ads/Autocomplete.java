package com.algomonster.ads;

import java.util.HashMap;

public class Autocomplete {

    class Trie {
        char val;
        int occur;
        HashMap<Character, Trie> children;
        public Trie(char c) {
            this.val = c;
            this.children = new HashMap<>();
        }
        // insert word in this trie
        // a recursive function for inserting each character of word in this trie
        public void insert(String word, int index) {
            occur++;
            if (word == null) return;
            if (index >= word.length()) return;

            char current = word.charAt(index);

            // check if the current char is already a children in this Trie
            Trie trie;
            if (children.containsKey(current)) {
                trie = children.get(current);  // get the children Trie, and then insert this word
            }
            else {
                trie = new Trie(current);
                children.put(current, trie);
            }
            trie.insert(word, index+1);
        }

        // computes for the occurances recursively.
        // once there is no occurance, or occur=1, we know that we
        // can form a concrete autocomplete
        public int occur(String word, int index) {
            if (index >= word.length() || occur == 1) {
                return 0;
            }

            char c = word.charAt(index);
            Trie trie = this.children.get(c);
            int charOccur = trie.occur(word, index+1);

            return charOccur + 1;
        }

        // function to check frequency we get a prefix
        int query(String s, int idx) {
            // we have reached end of prefix, terminate by returning the value
            if (s.length() == idx || occur == 1) {
                return 0;
            }
            return children.get(s.charAt(idx)).query(s, idx + 1) + 1;
        }
    }

    public int solution(String[] words) {

        // sentinel node, so that we can track all beginning chars
        Trie dictionary = new Trie('$');
        int totalCount = 0;
        for (String word : words) {
            dictionary.insert(word, 0);
            totalCount += dictionary.occur(word, 0);
        }
        return totalCount + 1;
    }

    public static void main(String[] a) {
        Autocomplete t = new Autocomplete();

        int val = t.solution(new String[] {"hi", "hello", "bojack", "hills", "hill"});
        System.out.println(val);
    }
}
