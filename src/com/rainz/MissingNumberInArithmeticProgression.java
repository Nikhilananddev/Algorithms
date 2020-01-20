package com.rainz;

/*
 * In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
 * Then, a value from arr was removed that was not the first or last value in the array.
 * Return the removed value.
 */
public class MissingNumberInArithmeticProgression {
    public static void test(String args[]) {
        int[] input1 = {5,7,11,13};
        System.out.println(missingNumber(input1));
        int[] input2 = {15,13,12};
        System.out.println(missingNumber(input2));
    }

    public static int missingNumber(int[] arr) {
        int delta = arr[1] - arr[0];
        // Find the # after removed one
        int lo = 2, hi = arr.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int expected = arr[0] + delta * (mid - 0);
            if (arr[mid] == expected)
                lo = mid + 1;
            else
                hi = mid;
        }
        return arr[lo] - delta;
    }
}
