package com.rainz;

public class MergeSortedArray {
	public static void test(String args[]) {
		
	}
	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int writePtr = m + n - 1;
        int ptr1 = m - 1;
        int ptr2 = n - 1;
        while (ptr1 >= 0 && ptr2 >= 0) {
            if (nums1[ptr1] > nums2[ptr2]) {
                nums1[writePtr--] = nums1[ptr1--];
            } else {
                nums1[writePtr--] = nums2[ptr2--];
            }
        }
        while (ptr2 >= 0) {
            nums1[writePtr--] = nums2[ptr2--];
        }
    }
}
