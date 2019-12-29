package com.rainz;

import java.util.*;

public class DivideArrayinSetsofKConsecutiveNumbers {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,3,4,4,5,6};
        System.out.println(isPossibleDivide(input1, 4));
        int[] input2 = {3,2,1,2,3,4,3,4,5,9,10,11};
        System.out.println(isPossibleDivide(input2, 3));
        int[] input3 = {3,3,2,2,1,1};
        System.out.println(isPossibleDivide(input3, 3));
        int[] input4 = {1,2,3,4};
        System.out.println(isPossibleDivide(input4, 3));
        int[] input5 = {13,14,15,7,8,9,20,21,22,4,5,6};
        System.out.println(isPossibleDivide(input5, 3));
    }

    public static boolean isPossibleDivide(int[] nums, int k) {
        // Frequence table
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int i: nums)
            freqs.put(i, freqs.getOrDefault(i, 0)+1);
        // Set of sequence heads
        Queue<Integer> heads = new LinkedList<>();
        for (int i: freqs.keySet()) {
            if (freqs.get(i-1) == null)
                heads.add(i);
        }
        while (!heads.isEmpty()) {
            int h = heads.remove();
            if (freqs.get(h) == null)
                continue; // potential head was removed
            for (int i = 0; i < k; ++i) {
                int n = h + i;
                Integer freq = freqs.get(n);
                // Check if it's still there because it could have been removed by another se
                if (freq == null)
                    return false; // can't form sequence of length k
                freq -= 1;
                if (freq == 0) {
                    freqs.remove(n);
                    heads.add(n+1); // potential new head
                }
                else {
                    freqs.put(n, freq);
                }
            }
            // Check if any of these k elements becomes
            if (freqs.get(h) != null)
                heads.add(h); // there are still more h's
        }

        return freqs.isEmpty();
    }
}
