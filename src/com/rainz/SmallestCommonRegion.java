package com.rainz;

import java.util.*;

/*
 * You are given some lists of regions where the first region of each list includes all other regions in that list.
 * Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.
 * Given two regions region1, region2, find out the smallest region that contains both of them.
 * If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.
 * It's guaranteed the smallest region exists.
 */
public class SmallestCommonRegion {
    public static void test(String args[]) {
        String[][] input1 = {{"Earth", "North America", "South America"},
                {"North America", "United States", "Canada"},
                {"United States", "New York", "Boston"},
                {"Canada", "Ontario", "Quebec"},
                {"South America", "Brazil"}};
        List<List<String>> ll1 = Main.buildList2D(input1);
        System.out.println(findSmallestRegion(ll1, "Quebec", "New York"));
    }

    public static String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parents = new HashMap<>();
        for (List<String> l: regions) {
            String p = l.get(0);
            for (int i = 1; i < l.size(); ++i)
                parents.put(l.get(i), p);
        }
        Set<String> path1 = new HashSet<>();
        String curr = region1;
        while (curr != null) {
            path1.add(curr);
            curr = parents.get(curr);
        }
        curr = region2;
        while(curr != null) {
            if (path1.contains(curr))
                return curr;
            curr = parents.get(curr);
        }
        return null;
    }
}
