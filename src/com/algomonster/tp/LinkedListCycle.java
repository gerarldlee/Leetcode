package com.algomonster.tp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LinkedListCycle {
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

    public static boolean hasCycle(Node<Integer> nodes) {
        // WRITE YOUR BRILLIANT CODE HERE
        Node fast = nodes;
        Node slow = nodes;

        while (fast != null) {
            slow = slow.next;

            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }

            // when fast reaches slow, there is a cycle
            if (slow != null && fast != null && slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> rawInput = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        ArrayList<Node<Integer>> nodesList = new ArrayList<>();
        for (int i = 0; i < rawInput.size(); i++) {
            nodesList.add(new Node<Integer>(i));
        }
        for (int i = 0; i < rawInput.size(); i++) {
            if (rawInput.get(i) != -1) {
                nodesList.get(i).next = nodesList.get(rawInput.get(i));
            }
        }
        Node<Integer> nodes = nodesList.get(0);
        scanner.close();
        boolean res = hasCycle(nodes);
        System.out.println(res);
    }
}
