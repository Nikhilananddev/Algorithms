package com.rainz;

import java.util.*;

public class LongestConsecutiveSequence {
    public static void test(String args[]) {
        int[] input = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(input));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        Map<Integer, Integer> allNums = new HashMap<>();
        for (int n: nums)
            allNums.put(n, null);
        for (int n: nums) {
            if (allNums.keySet().contains(n-1))
                allNums.put(n, n-1);
        }
        for (int n: allNums.keySet()) {
            List<Integer> updates = new ArrayList<>();
            Integer curr;
            Integer parent = n;
            do {
                curr = parent;
                parent = allNums.get(curr);
                if (parent != null)
                    updates.add(curr);
            } while (parent != null);
            // Now curr is head, update entire change
            for (Integer u: updates)
                allNums.put(u, curr);
        }
        Map<Integer, Integer> counts = new HashMap<>();
        int ans = 0;
        for (Integer h: allNums.values()) {
            if (h != null) {
                int count = counts.getOrDefault(h, 0) + 1;
                if (count > ans)
                    ans = count;
                counts.put(h, count);
            }
        }
        return ans+1; // add head to count
    }
}
