package com.rainz;

import java.util.HashSet;
import java.util.Set;

/*
 * Given two arrays, write a function to compute their intersection.
 */
public class IntersectionofTwoArrays {
    public static void test(String args[]) {
        int[] a1 = {1,2,2,1}, b1 = {2,2};
        int[] a2 = {4,9,5}, b2 = {9,4,9,8,4};
        Main.printArray(intersection(a1, b1));
        Main.printArray(intersection(a2, b2));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int n: nums1)
            s1.add(n);
        for (int n: nums2)
            s2.add(n);
        s1.retainAll(s2);
        int[] ans = new int[s1.size()];
        int idx = 0;
        for (int n: s1)
            ans[idx++] = n;
        return ans;
    }


}
