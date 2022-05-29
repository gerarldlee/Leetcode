package com.algomonster.tp;

import java.util.*;
import java.util.stream.Collectors;

public class FindAllAnagramsInString {

    public static boolean isAnagram(Map<Character, Integer> checkset, String str) {
        // put all chars of str into a map, including how many times it occured
        Map<Character, Integer> strMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            strMap.put(c, strMap.getOrDefault(c, 0) + 1);
        }

        // check this map against the checkset, and deduct the counts
        for (Character c : strMap.keySet()) {
            if (!checkset.containsKey(c))
                return false;

            if (strMap.get(c) - checkset.get(c) > 0)
                return false;
        }

        return true;
    }

    public static List<Integer> findAllAnagrams(String original, String check) {
        // WRITE YOUR BRILLIANT CODE HERE
        List<Integer> result = new ArrayList<>();

        if (check.length() > original.length()) return result;

        // put all check letters into a map, with count
        Map<Character, Integer> checkset = new HashMap<>();
        for (char c : check.toCharArray()) {
            checkset.put(c, checkset.getOrDefault(c, 0) + 1);
        }

        for (int i=0; i<=original.length()-check.length(); i++) {
            if (isAnagram(checkset, original.substring(i, i+check.length()))) {
                result.add(i);
            }
        }


        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String original = scanner.nextLine();
        String check = scanner.nextLine();
        scanner.close();
        List<Integer> res = findAllAnagrams(original, check);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
