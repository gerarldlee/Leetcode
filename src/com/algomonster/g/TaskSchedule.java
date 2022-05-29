package com.algomonster.g;

import java.util.*;

public class TaskSchedule {

    private static Map<String, Integer> dependencyCount(List<List<String>> reqts, List<String> tasks) {
        Map<String, Integer> dependencyCount = new HashMap<>();
        tasks.forEach(entry -> dependencyCount.put(entry, 0));

        reqts.forEach(entry -> {
            String dependent = entry.get(1);
            dependencyCount.put(dependent, dependencyCount.get(dependent) + 1);
        });
        return dependencyCount;
    }

    private static List<String> getNeighbors(String task, List<List<String>> requirements) {
        List<String> neighbors = new ArrayList();

        requirements.forEach(entry -> {
            String dependent = entry.get(1);
            String t = entry.get(0);
            if (t.equals(task)) {
                neighbors.add(dependent);
            }
        });
        return neighbors;
    }

    public static List<String> taskScheduling(List<String> tasks, List<List<String>> requirements) {
        // WRITE YOUR BRILLIANT CODE HERE

        List<String> results = new ArrayList();

        // compute for dependency
        Map<String, Integer> count = dependencyCount(requirements, tasks);

        ArrayDeque<String> queue = new ArrayDeque();
        // add all the task that has no dependcy
        count.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) queue.add(entry.getKey());
        });

        while (queue.size() > 0) {
            String task = queue.poll();
            results.add(task);
            // get the neighbors of task, and for each neighbors, deduct 1 count
            // we dont need to link neighbors, as it will just have a negative count
            for (String child : getNeighbors(task, requirements)) {
                count.put(child, count.get(child) - 1);
                if (count.get(child) == 0) {
                    queue.offer(child);
                }
            }
        }

        // also check for cycle
        return tasks.size() == results.size() ? results : null;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = splitWords(scanner.nextLine());
        int requirementsLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> requirements = new ArrayList<>();
        for (int i = 0; i < requirementsLength; i++) {
            requirements.add(splitWords(scanner.nextLine()));
        }
        scanner.close();
        List<String> res = taskScheduling(tasks, requirements);
        if (res.size() != tasks.size()) {
            System.out.println("output size " + res.size() + " does not match input size " + tasks.size());
            return;
        }
        HashMap<String, Integer> indices = new HashMap<>();
        for (int i = 0; i < res.size(); i++) {
            indices.put(res.get(i), i);
        }
        for (List<String> req : requirements) {
            for (String task : req) {
                if (!indices.containsKey(task)) {
                    System.out.println("'" + task + "' is not in output");
                    return;
                }
            }
            String a = req.get(0);
            String b = req.get(1);
            if (indices.get(a) >= indices.get(b)) {
                System.out.println("'" + a + "' is not before '" + b + "'");
                return;
            }
        }
        System.out.println("ok");
    }
}
