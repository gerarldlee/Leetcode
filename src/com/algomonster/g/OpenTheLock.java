package com.algomonster.g;

import java.util.*;

public class OpenTheLock {
    // neighbor is 1 move
    public static List<String> getNeighbors(String lock) {
        List<String> results = new ArrayList();

        for (int i=0; i<4; i++) {
            char[] lockChars = lock.toCharArray();
            char c = lockChars[i];

            // increment c by 1, decrement by 1
            char c1 = c, c0 = c;
            c1 += 1;
            if (c1 > '9') c1 = '0';
            c0 -= 1;
            if (c0 < '0') c0 = '9';

            // append them to the lock string
            lockChars[i] = c1;
            results.add(new String(lockChars));

            lockChars[i] = c0;
            results.add(new String(lockChars));
        }
        return results;
    }

    public static int numSteps(String combo, List<String> trappedCombos) {
        // WRITE YOUR BRILLIANT CODE HERE
        // base case
        if (combo == null || combo.length() == 0) return -1;

        // when combo is in trappedCombos
        if (trappedCombos.contains(combo)) return -1;

        ArrayDeque<String> queue = new ArrayDeque();
        queue.offer("0000");

        // do not visit trappedCombos
        HashSet<String> visited = new HashSet(trappedCombos);

        // the move
        int level = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            while (size > 0) {
                String lock = queue.pop();
                if (lock.equals(combo)) {
                    return level;
                }
                for (String child : getNeighbors(lock)) {
                    if (!visited.contains(child)) {
                        queue.offer(child);
                        visited.add(child);
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String combo = scanner.nextLine();
        List<String> trappedCombos = splitWords(scanner.nextLine());
        scanner.close();
        int res = numSteps(combo, trappedCombos);
        System.out.println(res);
    }
}
