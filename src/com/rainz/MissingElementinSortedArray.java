package com.rainz;

/*
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 */
public class MissingElementinSortedArray {
    public static void test(String args[]) {
        int[] input1 = {4,7,9,10};
        System.out.println(missingElement(input1, 1));
        int[] input2 = {4,7,9,10};
        System.out.println(missingElement(input2, 3));
        int[] input3 = {1,2,4};
        System.out.println(missingElement(input3, 3));
    }

    public static int missingElement(int[] nums, int k) {
        // Find the element before the kth missing
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] - nums[0] - mid >= k)
                hi = mid - 1;
            else {
                // If this is end of array,
                // or if next element has diff > k, we've found our element
                if (mid == nums.length - 1 || nums[mid+1] - nums[0] - (mid+1) >= k) {
                    lo = mid;
                    break;
                }
                lo = mid + 1;
            }
        }
        // kth missing is between nums[lo] and nums[lo+1]
        return nums[lo] + k - (nums[lo] - nums[0] - lo);
    }
}
