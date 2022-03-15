Hashtable
====

A data structure which organizes data using hash functions in order to support quick insertion and search

2 kinds:
- hashset - one implementation of a Set data structure to store unique or distinct values
- hashmap - one implementation of a map data structure to store key-value pairs


#### Design

- to use a hash function to map keys to buckets
  - when inserting new key, the hash function will decide which bucket they key should be assigned and stored in the bucket
  - when searching for a key, the hash table will use the same hash function to find the corresponding bucket and search that specific bucket

#### Hash Function

- usually modulo and prime numbers are used in the hash function
- will depend on the range of key values, and the number of buckets available
- try to assign the key uniformly as you can. ideally, there should be one-to-one mapping between the key and the bucket
  - will produce o(1) insert and search time
- when the hash function is not perfect, it will result in a collision.  The goal is to minimize collisions.
- tradeoff between the amount of buckets and the capacity of each bucket.



#### Collisions

- collisions occur when the key produce the same key from the hash function.  therefore it will put it in the same bucket
  - the more buckets we have, and the better the hash function, - the less collision occurs
- to handle collisions, each bucket should be able to handle multiple values
  - arrays - if N is small
  - linkedlist
  - binary search tree
  - self-balancing binary search tree - if N is large



#### Hash Set

```java
class MyHashSet {
    private final int MAX_LEN = 100000; // the amount of buckets
    private List<Integer>[] set;      // hash set implemented by array
    
    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }
    
    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Integer> temp = set[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i) == key) {
                return i;
            }
        }
        return -1;
    }
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        set = (List<Integer>[])new ArrayList[MAX_LEN];
    }
    
    public void add(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new key if key does not exist.
            if (set[index] == null) {
                set[index] = new ArrayList<Integer>();
            }
            set[index].add(key);
        }
    }
    
    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            // Remove the key if key exists.
            set[index].remove(pos);
        }
    }
    
    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        return pos >= 0;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
```



#### Hash Map

```java
import javafx.util.Pair;

class MyHashMap {
    private final int MAX_LEN = 100000;             // the amount of buckets
    private List<Pair<Integer, Integer>>[] map;     // hash map implemented by array
    
    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }
    
    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Pair<Integer, Integer>> temp = map[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).getKey() == key) {
                return i;
            }
        }
        return -1;
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = (List<Pair<Integer, Integer>>[])new ArrayList[MAX_LEN];
    }
    
    /** value will always be positive. */
    public void put(int key, int value) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new (key, value) pair if key is not existed.
            if (map[index] == null) {
                map[index] = new ArrayList<Pair<Integer, Integer>>();
            }
            map[index].add(new Pair(key, value));
        } else {
            // Update the value if key is existed.
            map[index].set(pos, new Pair(key, value));
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            return -1;
        } else {
            return map[index].get(pos).getValue();
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            map[index].remove(pos);
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
```



#### Removing an Element / Key

- o(N) - because we need to traverse the bucket size if we used an array
- to improve the time complexity to o(1)
  - swap the element to the last element, and remove the last element
  - or use a linked list if we don't want to modify the order of elements



#### Using height balanced binary search tree

- if there are too many values in the same bucket
- will give o(1) average case, o(log N) worse case for insertion and search



### Hash set Template

- finds duplicates

  ```java
  /*
   * Template for using hash set to find duplicates.
   */
  boolean findDuplicates(List<Type> keys) {
      // Replace Type with actual type of your key
      Set<Type> hashset = new HashSet<>();
      for (Type key : keys) {
          if (hashset.contains(key)) {
              return true;
          }
          hashset.add(key);
      }
      return false;
  }
  
  ```



### Hash Map Template 1

- when we need more information rather than only the key, we create a mapping relation between key and value
- when we need more information to help us with our decisions
- example: Given an array of integers, return **indices** of the two numbers such that they add up to a specific target.
  - we are asked to return the indices of the two numbers

```java
/*
 * Template for using hash map to find duplicates.
 * Replace ReturnType with the actual type of your return value.
 */
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    // Replace Type and InfoType with actual type of your key and value
    Map<Type, InfoType> hashmap = new HashMap<>();
    for (Type key : keys) {
        if (hashmap.containsKey(key)) {
            if (hashmap.get(key) satisfies the requirement) {
                return needed_information;
            }
        }
        // Value can be any information you needed (e.g. index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
```





### Hash Map Template 2

- when we want to aggregate all the information by key
- the key to solving this problem is to decide your strategy when you encounter an existing key
- example: Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
  - count the occurences, 
  - sum all the values up
  - replace the original value with new one

```java
/*
 * Template for using hash map to find duplicates.
 * Replace ReturnType with the actual type of your return value.
 */
ReturnType aggregateByKey_hashmap(List<Type>& keys) {
    // Replace Type and InfoType with actual type of your key and value
    Map<Type, InfoType> hashmap = new HashMap<>();
    for (Type key : keys) {
        if (hashmap.containsKey(key)) {
            hashmap.put(key, updated_information);
        }
        // Value can be any information you needed (e.g. index)
        hashmap.put(key, value);    
    }
    return needed_information;
}
```



## Design a Key

- Build a mapping relationship. Rules:
  - All values that belong to the same group will be mapped in the same group
  - values which needed to be separated into different groups will not be mapped into the same group
- This is as important as designing a hash function and they are very much alike and goes hand in hand together
  - The difference with hash functions, is that hash functions satisfies the first rule, but might not satisfy the second rule.
- Example: Given an array of strings, group anagrams together.
  - we need to design a key to represent the anagram in the same group, and different anagram to different group
  - a good mapping strategy could be:
    - sort the string
    - use this sorted string as the key ie. ["eat", "ate", "act"] -> "aet" = ["eat", "ate"], "act" -> ["act"]



#### Notes

- when the order of each element in the string or array does not matter, you can use sorted string / array as the key

  ![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/28/screen-shot-2018-02-28-at-144518.png)

- when you only care about the offset of each value, usually the offset from the first value, you can use the offset as the key

  ![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/28/screen-shot-2018-02-28-at-144738.png)

- in a tree, you might want to directly use the TreeNode as key sometimes. But in most cases, the *serialization of the subtree* might be a better idea.

- ![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/28/screen-shot-2018-02-28-at-143858.png)

- in a matrix, you might want to use the row index or the column index as key

- in a sudoku, you can combine the row index and the column index to identify which `block` this element belongs to

  ![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/28/screen-shot-2018-02-28-at-145454.png)

- sometimes, in a matrix, you might want to aggregate the values in the same diagonal line.

  ![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/28/screen-shot-2018-02-28-at-140029.png)
