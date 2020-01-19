package com.rainz;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 */
public class RelativeSortArray {
    public static void test(String args[]) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19}, arr2 = {2,1,4,3,9,6};
        Main.printArray(relativeSortArray(arr1, arr2));
    }
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> numTable = new HashMap<>();
        for (int i = 0; i < arr2.length; ++i)
            numTable.put(arr2[i], i);
        for (int n: arr1)
            numTable.put(n, numTable.getOrDefault(n, arr2.length+n));

        // Arrays.sort with custom comparator doesn't work with int!!
        Integer[] array1 = new Integer[arr1.length];
        for (int i = 0; i < array1.length; ++i)
            array1[i] = arr1[i];
        Arrays.sort(array1, Comparator.comparingInt(numTable::get));
        for (int i = 0; i < array1.length; ++i)
            arr1[i] = array1[i];
        return arr1;
    }
}
