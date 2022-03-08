package com.leetcode.facebook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    /*
The idea is to iterate over the prefixes of string and look for it from the dictionary. A boolean array
as a marker will be used as memo.

The way to iterate over the string is to use sliding windows approach. Maintaining 2 indexes, begin, end
to get the substring.
	- Increase the end index
	- Iterate, start from 0 index to the end index
	- Compare to dictionary
	- Use memo to identify the words from begin to end which of them are in the dictionary
	- For this, we need to declare memo[string.length + 1] as the starting point should always be true, and does not mean anything but just the starting index.
	- Finally, when the ending of the memo[string.length] is true, that means all of the word are contained in the dictionary.

	Time: O(s.length * 2) - O(N), space - O(N) for the memo
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }

    /*
    The idea is to use recursion to find every possible prefix of the string in the dictionary.
    If it is found, then look for the remaining portion of the string.

    But this will be o(2^n) in time complexity and so we will use a memo boolean as a marker that it has been found
     */
    public boolean wordBreakMemo(String s, List<String> dict) {
        return wordBreakMemo(s, new HashSet<>(dict), new Boolean[s.length()], 0);
    }
    private boolean wordBreakMemo(String s, Set<String> dict, Boolean[] memo, int start) {
        if (s.length() == start) {
            return true;
        }
        if (memo[start] != null) return memo[start];
        for (int end=start+1; end <= s.length(); end++) {
            if (dict.contains(s.substring(start, end)) && wordBreakMemo(s, dict, memo, end)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    public static void main(String[] argv) {
        WordBreak w = new WordBreak();
        System.out.println(w.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
        System.out.println(w.wordBreak("leetcode", List.of("leet", "code")));
        System.out.println(w.wordBreak("applepenapple", List.of("apple","pen")));
    }
}
