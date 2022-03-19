Longest Common Sequences
====

## Longest Common Subsequence

- finding the longest subsequence common to all sequences in a set of sequences
- subsequences are not contiguous
- example:
  - string1 = "ABCDGH", string2 = "AEDFHR".  The longest common subsequence is "ADH"
    - it is comon to the both of the strings, and has proper order or letters
  - string1 = "AGGTAB", string2 = "GXTXAB". The longest common subsequence is "GTAB"
    - it is common to the both of the strings, and has proper order of letters
    - notice how the first "A" in the string1 is not included even though string2 has "A" as well
- complexity:
  - time: O(MN), space: O(MN)


```java
public class LongestCommonSubsequence {
   int subseq(char[] a, char[] b, int a_len, int b_len){
      int my_arr[][] = new int[a_len + 1][b_len + 1];
      for (int i = 0; i <= a_len; i++){
         for (int j = 0; j <= b_len; j++){
            if (i == 0 || j == 0)
	            my_arr[i][j] = 0;
            else if (a[i - 1] == b[j - 1])
    	        my_arr[i][j] = my_arr[i - 1][j - 1] + 1;
            else
        	    my_arr[i][j] = max_val(my_arr[i - 1][j], my_arr[i][j - 1]);
         }
      }
      return my_arr[a_len][b_len];
   }
   int max_val(int val_1, int val_2){
      return (val_1 > val_2) ? val_1 : val_2;
   }
   public static void main(String[] args){
      Demo my_inst = new Demo();
      String my_str_1 = "MNSQR";
      String my_str_2 = "PSQR";
      char[] a = my_str_1.toCharArray();
      char[] b = my_str_2.toCharArray();
      int a_len = a.length;
      int b_len = b.length;
      System.out.println("The length of the longest common subsequence is"+ " " + my_inst.subseq(a, b, a_len,       b_len));
   }
}
```



## Longest Common Substring



```java
public int  lgstComnSubstrDP(String str1, String str2){

     int m = str1.length();
    int n = str2.length();
    int maxLen = 0;
    int[][] dp = new int[m+1][n+1];

    for(int i = 0; i<=m; i++){
        for(int j = 0; j<=n; j++){
            if(i == 0 || j == 0)
                dp[i][j] = 0;
            else if(str1.charAt(i-1) == str2.charAt(j-1))
                dp[i][j] = 1+dp[i-1][j-1];
            else
                dp[i][j] = 0;
            maxLen= Math.max(dp[i][j], maxLen);
        }
    }

    return maxLen;
}
```

