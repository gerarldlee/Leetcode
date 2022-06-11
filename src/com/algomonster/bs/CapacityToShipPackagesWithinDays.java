package com.algomonster.bs;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CapacityToShipPackagesWithinDays {
    public static int minMaxWeight(List<Integer> weights, int d) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (weights == null || weights.size() == 0) return 0;

        int left = 0;	// at the minimum,  truck capacity is >= max weight
        int right = 0;	// at the maximum, truck capacity is the sum(weight) where we delivery everything in 1 day
        int ans = -1;	// optimal truck capacity is somewhere in between, given d days
        for (int weight : weights) {
            right += weight;
            left = Math.max(weight, left);
        }

        while (left <= right) {
            int mid_truck_capacity = (left + right) / 2;
            if (isPossible(weights, mid_truck_capacity, d)) {
                ans = mid_truck_capacity;
                // try to squeeze it further by reducing the right
                right = mid_truck_capacity - 1;
            }
            else {
                left = mid_truck_capacity + 1;
            }
        }
        return ans;
    }

    /*
    determine if a given capacity is possible for all the items in weights array to be delivered in D days

    add the first i subset of weights, until it <= maxWeight, but not more
    when this is true, increment days
    */
    public static boolean isPossible(List<Integer> weights, int capacity, int days) {
        int tmp = capacity;
        int i = 0;
        while (i < weights.size()) {
            int w = weights.get(i);
            if (w <= tmp) {
                tmp -= w;
                i++;
            }
            else {
                // reset capacity
                tmp = capacity;
                days--;
            }
        }
        return days >= 1;    // 1 because at the minimum, we should be able to deliver everything in 1 day with max capacity
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> weights = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int d = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = minMaxWeight(weights, d);
        System.out.println(res);
    }
}
