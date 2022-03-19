Palindromic Sequences
====

## Longest Palindromic Subsequences

example: string: `"agbdba"`  lps = `"abdba"`

String matrix:

|      | a    | g    | b    | d    | b    | a    |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- |
|      | 0    | 1    | 2    | 3    | 4    | 5    |
| 0    | 1    | 1    | 1    | 1    | 3    | 5    |
| 1    |      | 1    | 1    | 1    | 3    | 3    |
| 2    |      |      | 1    | 1    | 3    | 3    |
| 3    |      |      |      | 1    | 1    | 1    |
| 4    |      |      |      |      | 1    | 1    |
| 5    |      |      |      |      |      | 1    |


[Longest Palindromic Subsequence - YouTube](https://www.youtube.com/watch?v=_nCsPn7_OgI)

```java
/**
 *
 * Given a string find longest palindromic subsequence in this string.
 *
 * Time complexity - O(n2)
 * Space complexity - O(n2
 *
 * Youtube link - https://youtu.be/_nCsPn7_OgI
 *
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    public int calculate1(char []str){
        int T[][] = new int[str.length][str.length];
        for(int i=0; i < str.length; i++){
            T[i][i] = 1;
        }
        for(int l = 2; l <= str.length; l++){
            for(int i = 0; i < str.length-l + 1; i++){
                int j = i + l - 1;
                if(l == 2 && str[i] == str[j]){
                    T[i][j] = 2;
                }else if(str[i] == str[j]){
                    T[i][j] = T[i + 1][j-1] + 2;
                }else{
                    T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
                }
            }
        }
        return T[0][str.length-1];
    }


    public int calculateRecursive(char str[],int start,int len){
        if(len == 1){
            return 1;
        }
        if(len ==0){
            return 0;
        }
        if(str[start] == str[start+len-1]){
            return 2 + calculateRecursive(str,start+1,len-2);
        }else{
            return Math.max(calculateRecursive(str, start+1, len-1), calculateRecursive(str, start, len-1));
        }
    }
    
    public static void main(String args[]){
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String str = "agbdba";
        int r1 = lps.calculateRecursive(str.toCharArray(), 0, str.length());
        int r2 = lps.calculate1(str.toCharArray());
        System.out.print(r1 + " " + r2);
    }
    
}
```



## Longest Palindromic Substring - Manacher's Algorithm

Example:

- String: `abaxabaxabb`
- the palindromes:
  - `aba`, `abaxaba`, `baxabaxab` and this is the longest
- [Longest Palindromic Substring Manacher's Algorithm - YouTube](https://www.youtube.com/watch?v=V-sEwsca1ak)

```java
/**
 *
 * Given a string find longest palindromic substring in this string.
 *
 * References
 * http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * http://www.geeksforgeeks.org/longest-palindromic-substring-set-2/
 * http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 * http://www.akalin.cx/longest-palindrome-linear-time
 * http://tarokuriyama.com/projects/palindrome2.php
 */
public class LongestPalindromeSubstring {

    public int longestPalindromeSubstringEasy(char arr[]) {

        int longest_substring = 1;
        for (int i = 0; i < arr.length; i++) {

            int x, y;
            int palindrome;
            x = i;
            y = i + 1;
            palindrome = 0;
            while (x >= 0 && y < arr.length && arr[x] == arr[y]) {
                x--;
                y++;
                palindrome += 2;
            }
            longest_substring = Math.max(longest_substring, palindrome);
            
            x = i - 1;
            y = i + 1;
            palindrome = 1;
            while (x >= 0 && y < arr.length && arr[x] == arr[y]) {
                x--;
                y++;
                palindrome += 2;
            }
            longest_substring = Math.max(longest_substring, palindrome);
        }
        return longest_substring;
    }

    /**
     * Linear time Manacher's algorithm to find longest palindromic substring.
     * There are 4 cases to handle
     * Case 1 : Right side palindrome is totally contained under current palindrome. In this case do not consider this as center.
     * Case 2 : Current palindrome is proper suffix of input. Terminate the loop in this case. No better palindrom will be found on right.
     * Case 3 : Right side palindrome is proper suffix and its corresponding left side palindrome is proper prefix of current palindrome. Make largest such point as
     * next center.
     * Case 4 : Right side palindrome is proper suffix but its left corresponding palindrome is be beyond current palindrome. Do not consider this
     * as center because it will not extend at all.
     *
     * To handle even size palindromes replace input string with one containing $ between every input character and in start and end.
     */
    public int longestPalindromicSubstringLinear(char input[]) {
        int index = 0;
        //preprocess the input to convert it into type abc -> $a$b$c$ to handle even length case.
        //Total size will be 2*n + 1 of this new array.
        char newInput[] = new char[2*input.length + 1];
        for(int i=0; i < newInput.length; i++) {
            if(i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        //create temporary array for holding largest palindrome at every point. There are 2*n + 1 such points.
        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;
        //here i is the center.
        for(int i=0; i < newInput.length; ) {
            //expand around i. See how far we can go.
            while(start >0 && end < newInput.length-1 && newInput[start-1] == newInput[end+1]) {
                start--;
                end++;
            }
            //set the longest value of palindrome around center i at T[i]
            T[i] = end - start + 1;

            //this is case 2. Current palindrome is proper suffix of input. No need to proceed. Just break out of loop.
            if(end == T.length -1) {
                break;
            }
            //Mark newCenter to be either end or end + 1 depending on if we dealing with even or old number input.
            int newCenter = end + (i%2 ==0 ? 1 : 0);

            for(int j = i + 1; j <= end; j++) {

                //i - (j - i) is left mirror. Its possible left mirror might go beyond current center palindrome. So take minimum
                //of either left side palindrome or distance of j to end.
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                //Only proceed if we get case 3. This check is to make sure we do not pick j as new center for case 1 or case 4
                //As soon as we find a center lets break out of this inner while loop.
                if(j + T[i - (j - i)]/2 == end) {
                    newCenter = j;
                    break;
                }
            }
            //make i as newCenter. Set right and left to atleast the value we already know should be matching based of left side palindrome.
            i = newCenter;
            end = i + T[i]/2;
            start = i - T[i]/2;
        }

        //find the max palindrome in T and return it.
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < T.length; i++) {
            int val;
      /*      if(i%2 == 0) {
                val = (T[i] -1)/2;
            } else {
                val = T[i]/2;
            }*/
            val = T[i]/2;
            if(max < val) {
                max = val;
            }
        }
        return max;
    }

    public int longestPalindromeDynamic(char []str){
        boolean T[][] = new boolean[str.length][str.length];
        
        for(int i=0; i < T.length; i++){
            T[i][i] = true;
        }
        
        int max = 1;
        for(int l = 2; l <= str.length; l++){
            int len = 0;
            for(int i=0; i < str.length-l+1; i++){
                int j = i + l-1;
                len = 0;
                if(l == 2){
                    if(str[i] == str[j]){
                        T[i][j] = true;
                        len = 2;
                    }
                }else{
                    if(str[i] == str[j] && T[i+1][j-1]){
                        T[i][j] = true;
                        len = j -i + 1;
                    }
                }
                if(len > max){
                    max = len;
                }
            }
        }
        return max;
    }

    public static void main(String args[]) {
        LongestPalindromeSubstring lps = new LongestPalindromeSubstring();
        System.out.println(lps.longestPalindromicSubstringLinear("abba".toCharArray()));
        System.out.println(lps.longestPalindromicSubstringLinear("abbababba".toCharArray()));
        System.out.println(lps.longestPalindromicSubstringLinear("babcbaabcbaccba".toCharArray()));
        System.out.println(lps.longestPalindromicSubstringLinear("cdbabcbabdab".toCharArray()));
    }

}
```





## Palindromic Partitioning



```java
class Solution {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfs(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfs(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfs(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}
```

