package com.algomonster.tp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TrapRainWater {
    public static int trappingRainWater(List<Integer> elevations) {
        // WRITE YOUR BRILLIANT CODE HERE
        int left=0, right=elevations.size()-1;
        int result=0;
        int leftMax=0, rightMax=0;

        while (left<right) {
            int leftHeight = elevations.get(left);
            int rightHeight = elevations.get(right);
            if (leftHeight < rightHeight) {
                if (leftHeight >= leftMax) leftMax=leftHeight;
                else result += leftMax-leftHeight;
                left++;
            }
            else {
                if (rightHeight >= rightMax) rightMax=rightHeight;
                else result += rightMax-rightHeight;
                right--;
            }
        }

        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> elevations = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = trappingRainWater(elevations);
        System.out.println(res);
    }
}
