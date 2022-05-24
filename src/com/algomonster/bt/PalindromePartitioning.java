package com.algomonster.bt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PalindromePartitioning {
    public static boolean isPalindrome(String str, int left, int right) {
        while(left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(List<List<String>> results, String str, int start, List<String> partitions) {
        if (start >= str.length()) {
            results.add(new ArrayList(partitions));
        }

        for (int end = start; end < str.length(); end++) {
            if (isPalindrome(str, start, end)) {
                String palindrome = str.substring(start, end+1);
                partitions.add(palindrome);

                dfs(results, str, end+1, partitions);

                partitions.remove(partitions.size()-1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        // WRITE YOUR BRILLIANT CODE HERE

        List<List<String>> results = new ArrayList<>();

        dfs(results, s, 0, new ArrayList<>());

        return results;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();
        List<List<String>> res = partition(s);
        for (List<String> row : res) {
            System.out.println(String.join(" ", row));
        }
    }
}
