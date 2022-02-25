package com.leetcode.linkedlist;

public class DeepCopyListWithRandomPointer {


    /*
    Intuition:

    1. Copy Nodes one by one, through its 'next'. Create a head pointer for the new Node.
    2. But instead of pointing the new Node's next to its new Node, point the old Node's next to the newly
    created Node, and point the newly created Node's next to the old Node's next.
    3. Now, iterate from the old Node's head, looking for a non-null random pointer.
    4. Once a random pointer found, it is linked to an old Node. That means, the new Node next to it, should
    also be link to the pointed old Node's next to it.
    5. Point the adjacent new Node to the adjacent old Nodes next.

    Time: O(2N)
    Space: O(N)
     */
    public Node copyRandomList(Node head) {
        Node ret = null;
        Node p = head;

        // clone all nodes
        while (p != null) {
            Node clone = cloneNodeInSameList(p);
            p = clone.next;
        }
        ret = (head == null ? null : head.next);

        p = head;
        while (p != null) {
            p.next.random = (p.random == null ? null : p.random.next);
            p = p.next.next;
        }

        p = head;
        while (p != null) {
            Node nextHead = p.next.next;
            Node curr = p.next;

            // re-assign currs next to next heads next
            curr.next = (nextHead == null ? null : nextHead.next);

            // re-assign head's next pointer back
            p.next = nextHead;
            p = nextHead;
        }
        return ret;
    }

    private Node cloneNodeInSameList(Node head) {
        if (head == null) return null;
        Node node = new Node(head.val);
        node.next = head.next;
        head.next = node;
        return node;
    }
}
