package com.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utavi {
    /*
     * A solution to the question "how to determine the odd ball out from a list of 9 balls having the same weights"
     *
     * The mathematical method works like this:
     * 1. Divide the 9 balls into 3 equal groups. Each group has 3 balls.
     * 2. Compare 2 groups of balls. If the weights are the same, that means the last group has the odd ball.
     * 		2.1 If the weights are different, then compare the last group from the first group
     * 		2.2 And compare the last group from the second
     * 3. From the odd group, do the same method, divide that group by 3, to compare each balls.
     * 4. Compare 2 balls at first. If the weight are the same, we got the odd ball out (the remaining ball)
     * 		4.1 If the weights are different, then we have to compare the last ball if its the same with the first
     * 		4.2 Or the second ball.
     *
     * The time complexity (or steps) it takes to determine the odd ball out is 4 steps at best (when we compare the first 2 groups the same, and the first 2 balls in the last group the same), and 8 steps at worst.  Remember that we dont know the position of the odd ball.
     *
     *
     * -------------------------------------------------------------------------
     * The binary search solution also works similar:
     * Given an array of 9 elements representing the weights of the balls: [1,1,1,1,1,1,1,2,1]
     * 1. Divide the 9 balls by 2 but record the first element, last element and middle element into a hashmap, including its weight.
     * 2. Compare the weights of these elements.
     * 		2.1 If theres one of these different, we know immediately whos the odd ball out, under the assumption that every single one of them are the same, except for 1, which we are trying to find.
     * 		2.2 If they are all the same, then we know that somewhere in the left of the middle element, or the right of the middle element contains the odd ball out.
     * 			2.2.1 However, because most of the weights are the same, we just move the left pointer to the middle pointer + 1, and compare once again to the right
     * 			2.2.2 If we found the odd ball out from the right, great, if not, then we have to search the left group until we found it.
     *
     * Time complexity: (Steps) O(log 9) - best case scenario, we find it immediately from the first 3 comparison. worse case, we do binary search to the right, and not found it, then we do binary search to the left until we find it. This also is about 6 steps. (Hey at least its better than the math one!)
     *
     *
     * -------------------------------------------------------------------------
     * However, I have suggested to you about another solution using array pointers and hashmap.
     * Given an array that represents weight of the iron balls: [1,1,2,1,1,1,1,1,1]
     * The logic is as follows:
     * 1. Create 2 pointers for the first 2 elements. Record them in the hashmap
     * 2. Compare the first 2 elements from these pointer if they are the same.
     * 		2.1 If they are the same, then move the 2 pointers to the next 2 elements, 4 and 5, or until we encounter the odd one out
     * 		2.2 If they are different, then we know one of these is the odd ball
     * 			2.2.1 We then compare them from our hashmap record count.
     *
     * Time complexity: (Steps) O(9 elements/ 2) - at best, the odd ball is in the first 4 elements, so we only compare 2 steps.
     * At worse, the odd ball is in the last element, so we compare steps 9 elements / 2.  This is 4 steps as explained below.
     *
     * It does not make sense to compare 3 elements in each step because it would increase our steps comparison. We want to minimise the number of steps we take.
     *
     *
     *
     */
    public int solution(int[] weights) {

        // pointers
        int one = 0;
        int two = 1;

        HashMap<Integer, Integer> weightCounts = new HashMap<>();

        while (one / 2 < weights.length / 2) {
            // record both their weights
            weightCounts.put(weights[one], weightCounts.getOrDefault(weights[one], 0) + 1);
            weightCounts.put(weights[two], weightCounts.getOrDefault(weights[two], 0) + 1);

            // compare their count. the first ball who has less count than others is the odd ball out.
            if (weightCounts.size() > 1) {
                Iterator<Map.Entry<Integer, Integer>> iter = weightCounts.entrySet().iterator();
                Map.Entry<Integer, Integer> first = iter.next();
                Map.Entry<Integer, Integer> second = iter.next();
                if (first.getValue() < second.getValue()) {
                    return first.getKey();
                }
                else if (first.getValue() > second.getValue()) {
                    return second.getKey();
                }
            }
            one = one + 2;
            two = two + 2;
        }
        // finally if they are all the same, we know immediately that the last is element in the array is the odd ball out.
        return weights[one];
    }

    public static void main(String[] argv) {
        Utavi t = new Utavi();

        int[] weights = new int[] {1,1,1,2,1,1,1,1,1};
        System.out.println(t.solution(weights));
    }
}
