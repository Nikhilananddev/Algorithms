package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
 */

public class IntersectionofThreeSortedArrays {
    public static void test(String args[]) {
        int[] arr1 = {1,2,3,4,5}, arr2 = {1,2,5,7,9}, arr3 = {1,3,4,5,8};
        System.out.println(arraysIntersection(arr1, arr2, arr3));
    }

    // Return index of 1st element >= target
    private static int findAtLeast(int A[], int start, int target) {
        int lo = start, hi = A.length-1;
        int ret = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] >= target) {
                ret = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return ret;
    }

    public static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int i1 = 0, i2 = 0, i3 = 0;
        List<Integer> ans = new ArrayList<>();
        if (arr1.length == 0)
            return ans;
        int target = -1;
        while (i1 < arr1.length && i2 < arr2.length && i3 < arr3.length) {
            if (arr1[i1] == arr2[i2] && arr2[i2] == arr3[i3]) {
                ans.add(arr1[i1]);
                ++i1; ++i2; ++i3;
                target = -1;
                continue;
            }
            if (target >= 0) {
                // new target to search for
                i1 = findAtLeast(arr1, i1, target);
                if (i1 < 0)
                    break;  // all #s in this array are smaller, there won't be any more common #s.
            }
            target = arr1[i1];
            i2 = findAtLeast(arr2, i2, target);
            if (i2 < 0)
                break;
            target = arr2[i2];
            i3 = findAtLeast(arr3, i3, target);
            if (i3 < 0)
                break;
            target = arr3[i3];
        }
        return ans;
    }
}
