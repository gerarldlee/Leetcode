package com.algomonster.tp;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class MiddleOfLinkedList {
    public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T val) {
            this(val, null);
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int middleOfLinkedList(Node<Integer> head) {
        // WRITE YOUR BRILLIANT CODE HERE
        // use the slow pointer to move next, and use the fast pointer move 2 steps

        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

        }

        return (int) slow.val;
    }

    public static <T> Node<T> buildList(Iterator<String> iter, Function<String, T> f) {
        if (!iter.hasNext()) return null;
        String val = iter.next();
        Node<T> next = buildList(iter, f);
        return new Node<T>(f.apply(val), next);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> head = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        int res = middleOfLinkedList(head);
        System.out.println(res);
    }
}
