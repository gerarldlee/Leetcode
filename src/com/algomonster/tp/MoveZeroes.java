package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MoveZeroes {
    public static void moveZeros(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE

        int fast = 0;
        int slow = 0;

        while (fast < nums.size()) {
            // swap them if fast pointer is non-0 and slow pointer is 0
            if (nums.get(slow) == 0 && nums.get(fast) != 0) {
                nums.set(slow, nums.get(fast));
                nums.set(fast, 0);
            }

            // when slow pointer is pointed to a 0 value, move the fast pointer to the first non-zero and swap
            if (nums.get(slow) == 0) {
                fast++;
            }
            // when slow pointer is pointed to a non-0 value, increment both slow and fast pointer by 1
            else {
                slow++;
                fast++;
            }
        }
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        moveZeros(nums);
        System.out.println(nums.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
