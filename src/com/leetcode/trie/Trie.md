Trie
====

Prefix tree - special form of n-nary tree

- used to store strings
- each node represent a string or a prefix
- each node have several children nodes, while paths to different children nodes represent different characters
- the string the child node represent will be the origin string represented by the node itself plus the character on the path

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/07/screen-shot-2018-01-31-at-163403.png)



- The value of the node exactly formed by the letters in the path from the root to the node
- The root node is empty string
- all descendants of a node have a common prefix of the string associated with that node.
- example: strings represented by nodes in the subtree rooted at node "b" have a common prefix "b"



### Applications:

- it is widely used in autocomplete, spell checker, etc
- we can also use Trie to accelerate DFS for words. ie. word squares, word search 2
- We can use Trie to store strings in most cases but not always i.e. Maximum XOR of 2 numbers in an array
- IP routing - longest prefix matching



## Representing a Trie

1. Array

   1. to store children nodes, we use a 26 size array that contains a - z characters. For specific character, we can use `c - 'a'` as index to find the corresponding child node in the array

   2. this is fast to visit a child node since we just use the index

   3. wasted space

      ```java
      class TrieNode {
          // change this value to adapt to different cases
          public static final N = 26;
          public TrieNode[] children = new TrieNode[N];
          
          // you might need some extra values according to different cases
      };
      
      /** Usage:
       *  Initialization: TrieNode root = new TrieNode();
       *  Return a specific child node with char c: root.children[c - 'a']
       */
      ```

      

2. Map

   1. we can declare a hashmap in each node.  The key of the hashmap are characters and value is the corresponding child node.

   2. might be a little slower than array, but it saves more space since we only store the children nodes we need. 

   3. more flexible because we are not limited by a fixed length and fixed range.

      ```java
      class TrieNode {
          public Map<Character, TrieNode> children = new HashMap<>();
          
          // you might need some extra values according to different cases
      };
      
      /** Usage:
       *  Initialization: TrieNode root = new TrieNode();
       *  Return a specific child node with char c: root.children.get(c)
       */
      ```

   

3. Boolean

   1. if we only want to store words in a Trie, we can declare boolean in each node as a flag to indicate the string represented by this node is a word or not



## Basic Operations



Insert - it is almost the same with inserting in a BST.

Steps:

1. We start with root node, and we will choose a child or add a new child node depending on the first character of string S that we want to insert, S[0]
2. We go down to the second node and we will make a choice according to S[1]
3. We go down to the third node, etc
4. We traverse all characters in S and reach the end. The end node will be the node which represent the string S

```java
Trie curr = root;
for (int i=0; i<S.length(); i++) {
	char c = S[i];
    // if curr does not have a children thats the letter S[i], we create it
	if (curr.children[c] == null) {
		curr.children[c] = new Trie(c);
	}
    else {
        curr = curr.children[c];
    }
}
```



Searching a prefix

```java
Trie curr = root;
for (int i=0; i<S.length(); i++) {
	char c = S[i];
    // if curr does not have a children thats the letter S[i], we return false asap
	if (curr.children[c] == null) {
		return false;
	}
    else {
        curr = curr.children[c];
    }
}
```



Searching a word

- treat the word as a prefix and search the same way
- if search fails, which means that no words start with the target word, the target word is not in the Trie
- if search succeeds, we need to check if the target word is only a prefix of words or it is exactly a word.
  - use boolean flag in each node to mark words vs prefix



Example

- implement insert, search, and startsWith
- when we insert a word, we put a boolean flag at the end of the word node to true
- when we search a word, we return true if we successfully find the path and the flag of the end node is true i.e. a word
- when we use startsWith, we return true if we successfully find the path

```java
class Trie {
    class TrieNode {
        public boolean isWord; 
        public Map<Character, TrieNode> childrenMap = new HashMap<>();
    }
    
    private TrieNode root; 

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(cur.childrenMap.get(c) == null){
                // insert a new node if the path does not exist
                cur.childrenMap.put(c, new TrieNode());
            }
            cur = cur.childrenMap.get(c); 
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.childrenMap.get(c) == null) {
                return false;
            }
            cur = cur.childrenMap.get(c);
        }
        return cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(int i = 0;i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.childrenMap.get(c) == null) {
                return false;
            }
            cur = cur.childrenMap.get(c);
        }
        return true;
    }
}
```



Complexity:

Time: O(N) - we search and insert according to S.length()

Space: O(N * M * K) - if we have M words to insert, and the length of the words is at most N, there will be M * N nodes in the worse case when M words are unique and does not have common prefix. Each node will also maintain a map of K different characters i.e. up to 26

