package com.leetcode.linkedlist;

public class InsertIntoCyclicSortedList {

    /*
    Intuition:

    Given that the list is sorted and cyclic, we compare the value of insertVal and head.val.
    If the insertVal falls before head.val, we traverse backwards, else forwards.
    If the value of insertVal is between the head.val and the head.next.val, we insert it there.
    We get the current Node, we get the next Node. We create the Node with insertVal.
    Current node is pointed to insertVal's Node, and insertVals Node is pointed to the Current Node
    Next node is pointed to insertVals Node and insertVals Node next is pointed to the Next Node.

    If the head == null, we create a Node that points the next to itself and the prev to itself.

    We return the head.

    Time: O(N) to search and iterate
    Space: O(1)

     */
    public Node insert(Node head, int insertVal) {
        if (head == null) return insert(null, null, insertVal);

        Node curr = head;
        while (curr.next != head) {
            // when the insertVal is between curr and curr.next in the chain
            if (curr.val <= insertVal && insertVal <= curr.next.val) {
                insert(curr, curr.next, insertVal);
                return head;
            }
            // when the insertVal is after the max but before the min
            else if (curr.val > curr.next.val &&
                    (curr.val <= insertVal || insertVal <= curr.next.val)) {
                insert(curr, curr.next, insertVal);
                return head;
            }
            curr = curr.next;
        }
        // when the insertVal is more or less than the values,
        // but cannot find in 1 loop i.e. [3,3,3]
        insert(curr, curr.next, insertVal);
        return head;
    }

    // inserts the node in between curr and next
    // time: o(1), space: o(1)
    private Node insert(Node curr, Node next, int val) {
        Node n = new Node(val);
        if (curr == null) {
            n.next = n;
            return n;
        }
        curr.next = n;
        if (next == null) {
            n.next = curr;
            return n;
        }
        n.next = next;
        return n;
    }
}
