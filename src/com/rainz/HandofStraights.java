package com.rainz;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

/*
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * Return true if and only if she can.
 */
public class HandofStraights {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,6,2,3,4,7,8};
        System.out.println(isNStraightHand(input1, 3));
        int[] input2 = {1,2,3,4,5};
        System.out.println(isNStraightHand(input2, 4));
    }

    public static boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0)
            return false;
        Map<Integer, Integer> handMap = new TreeMap<>();
        for (int h: hand)
            handMap.put(h, handMap.getOrDefault(h, 0)+1);
        List<Integer> nums = new ArrayList<>();
        nums.addAll(handMap.keySet());
        int idx = 0;
        while (idx < nums.size()) {
            int n = nums.get(idx);
            int count = handMap.getOrDefault(n, 0);
            if (count == 0) {
                ++idx;
                continue;
            }
            handMap.put(n, count - 1);
            // Find next W-1 numbers
            for (int w = 1; w < W; ++w) {
                int wCount = handMap.getOrDefault(n+w, 0);
                if (wCount == 0)
                    return false;
                handMap.put(n + w, wCount - 1);
            }
        }

        return true;
    }
}
