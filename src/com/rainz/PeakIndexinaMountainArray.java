package com.rainz;

public class PeakIndexinaMountainArray {
    public static void test(String args[]) {
        int[] input1 = {0,1,0};
        System.out.println(peakIndexInMountainArray(input1));
        int[] input2 = {0,2,1,0};
        System.out.println(peakIndexInMountainArray(input2));
    }

    public static int peakIndexInMountainArray(int[] A) {
        int lower = 1;
        int upper = A.length - 2;
        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            if (A[mid] > A[mid-1]) {
                if (A[mid] > A[mid+1])
                    return mid;
                lower = mid + 1;
            }
            else {
                upper = mid - 1;
            }
        }
        return -1;
    }
}
