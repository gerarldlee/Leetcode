LinkedList
====

- linear data structure with each of the elements are linked together by the reference to the next element

### Singly Linked List

- Linked List with reference to the next element
- Add / Insert
  - initialize the new node with the given value
  - link the next field of the new node to the previous next node
  - link the next field of the previous node to the new node
  - o(1)
- Add a node at the beginning
  - Create a sentinel node for ease of use
  - initialize new node with given value
  - link the next field of the new node to the sentinel's next
  - link the next field of the sentinel node to the new node
- Add a node at the end
  - Simply create a new node
  - point the next field of the last node to the new node
- Delete
  - Simply link the previous node's next field to the current node's next field
- Delete at the beginning
  - Simply link the sentinel node's next to the first node's next
- Delete at the end
  - Simple assign a null value to the previous node's next
- Search
  - iterate over the singly link list by its next field



#### Reverse a singly linked list

- iterate the nodes, and move each of the nodes to the head of the singly list one by one

- use as many pointers as you like

- you might need to track previous node, since you are not able to with singly linked list

- Time: o(N)

- Space: O(1)

  ```java
  public ListNode reverseList(ListNode head) {
      if (head == null) {
          return head;
      }
      ListNode prev = null;
      while (head != null) {
          ListNode nextNode = head.next;	// create temp node that points to current's next node
          head.next = prev;				// assign current next to previous
  		prev = head;					// assign previous to current
  		head = nextNode;				// assign current to the temp node to navigate further down
      }
      return prev;	// prev now becomes the head
  }
  ```

   

#### Two Pointers in Linked List

- How to determine if there is a cycle in the list

  - if there's no cycle, the fast pointer will stop at the end of the linked list
  - if there's a cycle, the fast pointer will eventually meet with the slow pointer

- Movement:

  - move the slow pointer one step at a time
  - move the fast pointer two steps at a time

- Tips

  - Always examine if node is null before calling the next field, especially with the fast pointer `fast = fat.next.next`
  - carefully define end conditions of the loop

- Complexity

  - Time: 
    - o(N/2) - no cycle, it will take N/2 steps for the fast pointer to arrive at the end of the list
    - o(M) - if there's a cycle, and M is the steps or length to catch up with the slower pointer, and is also the length of the cycle in the list.  M <= N
  - Space: o(1) - no space consumed apart from the pointers

  ```java
  // Initialize slow & fast pointers
  ListNode slow = head;
  ListNode fast = head;
  /**
   * Change this condition to fit specific problem.
   * Attention: remember to avoid null-pointer error
   **/
  while (slow != null && fast != null && fast.next != null) {
      slow = slow.next;           // move slow pointer one step each time
      fast = fast.next.next;      // move fast pointer two steps each time
      if (slow == fast) {         // change this condition to fit specific problem
          return true;
      }
  }
  return false;   // change return value to fit specific problem
  ```





## Doubly Linked List

- has `next` and `prev` field to reference the next and the previous node respectively



#### Operations

- we have to traverse from head or from end, depending on the `ith` index if its near the `size` of the list

- Add / Insert
  - create a new node with the given value
    - point the new node's `prev` field to the previous node
    - point the new node's `next` field to the next node
  - point the previous node's `next` to the new node
  - point the next node's `prev` field to the new node
- Delete
  - create a temporary nextNode <- node's `next` field
  - point the prev's node `next` to the nextNode
  - point the nextNode's `prev` to the prev node



## Notes



Similarities:

- both of them are not able to access data at a <u>random position</u> in constant time
- both of them can add a new node after given node, or at the beginning of the list in O(1) time
- both of them can delete the first node in O(1) time

Differences:

- In a singly linked list, it is not able to get the previous node of a given node, so we need to spend O(N) time to traverse and find the previous node before deleting the given node
- In double linked list, it will be much easier because we can get the previous node with the `prev` reference field. So we can delete a given node in O(1) time

Tips:

- if you need to add or delete a node frequently, a linked list is a good choice
- if you need to access an element by index, an array might be a better choice than a linked list
- A double linked list is faster when deleting a node than a singly linked list

![img](https://assets.leetcode.com/uploads/2020/10/02/comparison_of_time_complexity.png)
