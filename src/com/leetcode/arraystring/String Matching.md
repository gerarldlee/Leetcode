String Matching
====

## Rabin-Karp Algorithm

- used for searching and matching patterns in the text using hash function
- it does not travel through every character in the initial phase, rather it filters the characters that do not match and then performs comparison

Algorithm: A sequence of characters is taken and checked for the possibility of the presence of the required string. If the possibility is found then, character matching is performed.

1. In the text "ABCCDDAEFG", find "CDD"
2. Lets suppose to assign weights as 1-26 for A-Z. This will be the input set.
3. calculate the **hash value** of "CDD" from the input set. 

```
"CDD" hash value:
h(p) = Σ(v * dm-1) mod 13 
= ((3 * 10^2) + (4 * 10^1) + (4 * 10^0)) mod 13 
= 344 mod 13
= 6
```

4. calculate the hash value of the first 3 letter window "ABC".  Hash = 6 too
5. compare the hash, if they match, then character matching is performed.
6. Since they do not match, go for the next window, "BCC"
7. we calculate the hash value of the next window by subtracting the first term and adding the next term
   1. in order to optimize this process, we make use of the previous hash value in the following way

```
t = ((1 * 10^2) + ((2 * 10^1) + (3 * 10^0)) * 10 + (3 * 100)) mod 13 
  = 233 mod 13  
  = 12
Where, t = the first window "ABC"
  
t = ((d * (t - v[character to be removed] * h) + v[character to be added] ) mod 13  
  = ((10 * (6 - 1 * 9) + 3 )mod 13  
  = 12
Where, h = dm-1 = 103-1 = 100.
```

8. for "BCC", t = 12 != 6. Therefore, go for the next window. After few searches, we will get the match for the window "CDD"

The algorithm is as follows, with time: O(M + N) best, and O(MN) worse.

```
function RabinKarp(string s[1..n], string pattern[1..m])
    hpattern := hash(pattern[1..m]);
    for i from 1 to n-m+1
        hs := hash(s[i..i+m-1])
        if hs = hpattern
            if s[i..i+m-1] = pattern[1..m]
                return i
    return not found
```

```java
public class RabinKarp {
  public final static int d = 10;

  static void search(String pattern, String txt, int q) {
    int m = pattern.length();
    int n = txt.length();
    int i, j;
    int p = 0;
    int t = 0;
    int h = 1;

    for (i = 0; i < m - 1; i++)
      h = (h * d) % q;

    // Calculate hash value for pattern and text
    for (i = 0; i < m; i++) {
      p = (d * p + pattern.charAt(i)) % q;
      t = (d * t + txt.charAt(i)) % q;
    }

    // Find the match
    for (i = 0; i <= n - m; i++) {
      if (p == t) {
        for (j = 0; j < m; j++) {
          if (txt.charAt(i + j) != pattern.charAt(j))
            break;
        }

        if (j == m)
          System.out.println("Pattern is found at position: " + (i + 1));
      }

      if (i < n - m) {
        t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
        if (t < 0)
          t = (t + q);
      }
    }
  }

  public static void main(String[] args) {
    String txt = "ABCCDDAEFG";
    String pattern = "CDD";
    int q = 13;
    search(pattern, txt, q);
  }
}
```



### How to generate rolling hash - RK

Consider:

- window size = length of pattern to be searched
- alphabet size - 26
- prime number - larger prime number means lower collisions. lets take 53 for now. 
  - choice of 53 - because A - Z = 26 + a - z = 26 = 52. Its almost close
  - valid choices: 13, 29, 53, 127, 257 for characters

```java
int alphabet_size = 26;
int window_size = pattern.length();
int prime = 29;
char[] pattern = "ABC".toCharArray();
char[] text = "ADFASDFASDFABC".toCharArray();

// calculate the initial hash value of the pattern and the first window of text
for (int i=0; i < window_size; i++) {
    int pattern_value = pattern[i] - 'A' + 1;
    int text_value = text[i] - 'A' + 1;
    
    pattern_hash = (pattern_value + alphabet_size * pattern_hash) % prime;
    running_window_hash = (text_value + alphabet_size * running_window_hash) % prime;
}

// calculate the running hash of the running window
int first_value = Math.pow(text[0] - 'A' + 1, window_size - 1);
int previous_value = alphabet_size * (running_window_hash - first_value);
int next_value = text[window_size + 1] - 'A' + 1;

running_window_hash = (previous_value + next_value) % prime;

// there might be a possibility that this is a nagative esp if prime is small.
if (running_window_hash < 0) {
    running_window_hash += prime;
}
```





## Knuth-Morris-Pratt string searching algorithm



1. generate a prefix table
   1. the purpose of prefix table is so that we know how many indexes to skip, when there is a mismatch
2. compare the text string, with the word that needs to be searched, one character at a time. 
   1. if they do not match lets say given string "ACAT ACG" and pattern "ACAC", they will not match at the second "C" vs "T".
      1. the index of "C" is 4 from the pattern and the prefix table.
      2. given the prefix table, the 4th index = 2. this means we need to move the whole pattern so that its 2 index aligns with the mismatched character from the given string
      3. if the prefix table index is 0, just move the whole pattern so that its 0th index aligns with the mismatched character from the given string

### How to generate the prefix table of the pattern?

lets assume pattern is "ACACAGT"

1. iterate each character in the pattern. the first is "A"
   1. prefix of "A" - none, suffix of "A" - none. is there common prefix and suffix? none - so 0
2. next window of characters is "AC" up to the 2nd iteration
   1. prefix - "A", suffix - "C". are they common? no, so 0
3. next window of characters is "ACA" up to the 3rd iteration
   1. prefix - `[A, AC]`, suffix: `[CA, A]` 
   2. whats the length of the longest common prefix and suffix ? "A"' length is 1. So, 1
4. etc
5. in the next window "ACACA"
   1. prefix: `[A, AC, ACA, ACAC]`
   2. suffix: `[CACA, ACA, CA, A]`
   3. length of longest common? "ACA", so 3
6. in the window "ACACAG"
   1. prefix: `[A, AC, ACA, ACAC, ACACA]`
   2. suffix: `[CACAG, ACAG, CAG, AG, G]`
   3. length of longest common? no common element, so 0
7. etc
8. prefix table: `[A=0, C=0, A=1, C=2, A=3, G=0, T=0]`

https://www.youtube.com/watch?v=JoF0Z7nVSrA

```java
class KMP {
    
    public int search(String haystack, String needle) {
        if (needle == "") return 0;

        int[] prefixes = new int[needle.length()];

        // generate prefixes
        int prevPrefixIndex = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(prevPrefixIndex)) {
                prefixes[i] = prevPrefixIndex + 1;
                prevPrefixIndex++;
                i++;
            }
            else if (prevPrefixIndex == 0) {
                prefixes[i] = 0;
                i++;
            }
            else {
                prevPrefixIndex = prefixes[prevPrefixIndex - 1];
            }
        }

        // search the haystack
        int haystack_idx = 0;
        int needle_idx = 0;
        while (haystack_idx < haystack.length()) {
            if (haystack.charAt(haystack_idx) == needle.charAt(needle_idx)) {
                haystack_idx++;
                needle_idx++;
            }
            else {
                if (needle_idx == 0) {
                    haystack_idx++;
                }
                else {
                    needle_idx = prefixes[needle_idx - 1];
                }
            }

            if (needle_idx == needle.length()) {
                return haystack_idx - needle.length();
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
	    KMP m = new KMP();


        String haystack = "ASDFASFASDFASDGERARDFGASDFASDFASDFADSFSADF";
        String needle = "GERARD";

        System.out.println(m.search(haystack, needle)); // 14
    }
}
```



## Comparisson

- space usage favors Rabin-Karp - it utilizes O(1)
  - great for large text
  - KMP use O(N)
- worse case single match efficiency favors KMP
  - the Rabin-Karp has a flaw:
    - the particular prime numbers use by RK are known to a malicious adversary - that adversary could potentially craft an input that causes the rolling hash to match the hash of a pattern string at each point in time causing the performance to degrete
- worse case multiple match efficiency favors KMP
  - RK is slow for multiple match, because there will be lots of copies that will appear, degrading performance
- generalizations to muiltiple strings favor KMP
  - suppose you have multiple text string to search
  - KMP generalizes nicely to the Aho-Corasick string matching which runs O(M + N + Z) time. Z is the matches found
