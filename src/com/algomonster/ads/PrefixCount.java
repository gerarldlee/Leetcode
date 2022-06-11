package com.algomonster.ads;

import java.util.*;
import java.util.stream.Collectors;

public class PrefixCount {
    static class Trie {
        char val;
        int words;    // a word dictionary counter
        HashMap<Character, Trie> children = new HashMap<>();
        public Trie(char c) {this.val = c;}
        public void insert(String word, int index) {
            words++;
            if (index >= word.length()) return;
            char c = word.charAt(index);
            if (!children.containsKey(c)) {
                children.put(c, new Trie(c));
            }
            children.get(c).insert(word, index+1);
        }
        public int queryWord(String prefix, int index) {
            if (index >= prefix.length()) return this.words;

            char c = prefix.charAt(index);
            if (children.containsKey(c)) {
                return children.get(c).queryWord(prefix, index+1);
            }
            return 0;
        }
    }

    public static List<Integer> prefixCount(List<String> words, List<String> prefixes) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (words == null || words.isEmpty() || prefixes == null || prefixes.isEmpty()) return new ArrayList();

        // we need to create a Trie and enter all words in the dictionary
        Trie dictionary = new Trie('$');
        for (String word : words) {
            dictionary.insert(word, 0);
        }

        // we then search and query each prefix and count the result that we got fromt he dictionary
        List<Integer> result = new ArrayList<>();
        for (String prefix : prefixes) {
            result.add(dictionary.queryWord(prefix, 0));
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = splitWords(scanner.nextLine());
        List<String> prefixes = splitWords(scanner.nextLine());
        scanner.close();
        List<Integer> res = prefixCount(words, prefixes);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
