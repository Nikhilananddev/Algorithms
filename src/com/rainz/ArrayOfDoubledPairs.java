package com.rainz;

import java.util.*;

public class ArrayOfDoubledPairs {
    public static void test(String args[]) {
        int[] input = {3,1,3,6};
        System.out.println(arrayOfDoubledPairs(input));
        int[] input2 = {2,1,2,6};
        System.out.println(arrayOfDoubledPairs(input2));
        int[] input3 = {4,-2,2,-4};
        System.out.println(arrayOfDoubledPairs(input3));
        int[] input4 = {1,2,4,16,8,4};
        System.out.println(arrayOfDoubledPairs(input4));
        int[] input5 = {1,2,1,-8,8,-4,4,-4,2,-2};
        System.out.println(arrayOfDoubledPairs(input5));
    }

    public static boolean arrayOfDoubledPairs(int[] A) {
        Map<Integer, Integer> histo = new HashMap<>();
        for (int x: A) {
            Integer freq = histo.get(x);
            if (freq == null)
                histo.put(x, 1);
            else
                histo.put(x, freq+1);
        }

        while (!histo.isEmpty()) {
            Map.Entry<Integer, Integer> entry = histo.entrySet().iterator().next();
            List<int[]> chain = new ArrayList<>();
            Integer num = entry.getKey();
            Integer freq = entry.getValue();
            // Move current number from histo to chain
            int[] kv = {num, freq};
            chain.add(kv);
            histo.remove(num);
            if (num == 0) {
                // Special case for 0: check if chain length is even
                if (freq % 2 != 0)
                    return false;
                continue;
            }

            // Extract smaller numbers in chain
            int curr = num;
            while (curr % 2 == 0) {
                curr /= 2;
                freq = histo.get(curr);
                if (freq == null)
                    break;
                int[] kv2 = {curr, freq};
                chain.add(kv2);
                histo.remove(curr);
            }
            Collections.reverse(chain);
            // Extract larger numbers in chain
            curr = num;
            while (curr <= Integer.MAX_VALUE/2 && curr >= Integer.MIN_VALUE/2) {
                curr *= 2;
                freq = histo.get(curr);
                if (freq == null)
                    break;
                int[] kv2 = {curr, freq};
                chain.add(kv2);
                histo.remove(curr);
            }
            // Make pairs in chain, starting from smallest element
            while (!chain.isEmpty()) {
                if (chain.size() == 1)
                    return false;
                int[] p1 = chain.get(0);
                int[] p2 = chain.get(1);
                if (p2[1] < p1[1])
                    return false; // not enough 2nd numbers to pair with 1st numbers
                chain.remove(0);
                p2[1] -= p1[1]; // match all 1st numbers with 2nd numbers
                if (p2[1] == 0)
                    chain.remove(0); // same # of 1st & 2nd numbers, all 2nd number matched
            }
        }

        return true;
    }
}
