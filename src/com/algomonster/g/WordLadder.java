package com.algomonster.g;

import java.util.*;

public class WordLadder {
    public static int editDistance(String word1, String word2) {
        // whos the longer string
        String longer = word1.length() >= word2.length() ? word1 : word2;
        String shorter = word1.length() < word2.length() ? word1 : word2;

        int i=0;
        int distance = 0;
        for (; i<shorter.length(); i++) {
            char l = longer.charAt(i);
            char s = shorter.charAt(i);

            if (l == s) continue;

            distance++;
        }

        if (i < longer.length()) {
            distance += (longer.length() - i);
        }

        return distance;
    }

    // gets a 1 edit distance of word from wordlist, without visiting the visited
    public static List<String> getNeighbors(String word, HashSet<String> visited, List<String> wordList) {
        List<String> results = new ArrayList();
        for (String w : wordList) {
            if (visited.contains(w)) continue;

            int change = editDistance(word, w);
            if (change == 1) {
                results.add(w);
            }
        }
        return results;
    }

    public static int wordLadder(String begin, String end, List<String> wordList) {
        // WRITE YOUR BRILLIANT CODE HERE

        ArrayDeque<String> queue = new ArrayDeque();
        queue.offer(begin);

        HashSet<String> visited = new HashSet();
        visited.add(begin);

        int steps = 0;
        while (queue.size() > 0) {
            int size=queue.size();
            while (size > 0) {
                String word = queue.pop();
                if (word.equals(end)) {
                    return steps;
                }
                for (String child : getNeighbors(word, visited, wordList)) {
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
                size--;
            }
            steps++;
        }
        return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String begin = scanner.nextLine();
        String end = scanner.nextLine();
        List<String> wordList = splitWords(scanner.nextLine());
        scanner.close();
        int res = wordLadder(begin, end, wordList);
        System.out.println(res);
    }
}
