package com.algomonster.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HouseRobber {
    public static int rob(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE

        List<Integer> rob = new ArrayList<>();
        rob.add(0);
        rob.add(nums.get(0));

        for (int i=2; i<=nums.size(); i++) {
            int otherDay = rob.get(i-2);
            int yday = rob.get(i-1);
            int today = nums.get(i-1);

            int maxToday = Math.max(yday, today+otherDay);
            rob.add(maxToday);
        }

        return rob.get(rob.size()-1);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = rob(nums);
        System.out.println(res);
    }
}
