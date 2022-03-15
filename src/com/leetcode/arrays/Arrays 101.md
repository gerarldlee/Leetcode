Arrays
====

An Array is a collection of items. The items could be integers, strings, DVDs, games, books—anything really. The items are stored in neighboring (contiguous) memory locations. Because they're stored together, checking through the entire collection of items is straightforward.



### Capacity vs Length

Capacity - how much the array could potentially hold

Length or size - how many values the array is currently holding

Rule of thumb - when the length or size reaches 70% of capacity, allocate and create a new array and copy the old array to the new array.



### Insert

At the end of the array

```java
if (size < capacity) {
	array[size] = value;
}
else {
    // we need to allocate new array
}
```



At the beginning of the array

```java
// move the values to its next index, provided that the size has not reached capacity
for (int i=array.length-1; i>0; i++) {
    array[i] = array[i-1];
}
// insert the value
array[0] = value;
```



At the middle of the array

```java
// move the values to its next index, provided that the size has not reached capacity
for (int i=array.length-1; i>mid; i++) {
    array[i] = array[i-1];
}
// insert the value
array[mid] = value;
```



### Delete

At the end of the array

```java
if (size > 0) {
    array[size] = 0;	// remove the value and decrement the size
    size--;
}
```



At the beginning of the array

```java
// move the values to its previous index
for (int i=array.length-2; i>=0; i++) {
    array[i] = array[i+1];
}
array[size] = 0;	// remove the last value since it has been copied already
```



At the middle of the array

```java
// move the values to its previous index
for (int i=array.length-2; i>=mid; i++) {
    array[i] = array[i+1];
}
array[size] = 0;
```



### Search

- linear search - iterate the values of the array and compare if found
- binary search - if the array is in sorted order
- two pointer search - just to cut the search time in half



### In-place operations

- means modifying the input array to save in space complexity



### Two Pointer technique

- same direction for both pointers
- opposite direction
- fixed width



### Circular arrays

- unbounded array that connects the end to its beginning
