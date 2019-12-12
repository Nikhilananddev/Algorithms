package com.rainz;

public class FindTheSmallestDivisorGivenAThreshold {
    public static void test(String args[]) {
        int[] input = {1,2,5,9};
        System.out.println(findTheSmallestDivisorGivenAThreshold(input, 6));
    }

    public static int findTheSmallestDivisorGivenAThreshold(int[] nums, int threshold) {
        double sum = 0;
        for (int n: nums)
            sum += n;
        // lower points to a value which might not work
        int lower = (int)Math.ceil(sum/threshold);
        // upper points to a value which works (aka, sum_of_division <= thresh)
        int upper = (int)sum; // this guarantees sum_of_division is at least nums.length, which is min for threshold
        int result = upper;

        while (lower <= upper) {
            int guess = lower + (upper - lower) / 2; // compute avg while avoiding overflow
            int sum_of_division = 0;
            for (int n: nums)
                sum_of_division += Math.ceil((double)n/guess);
            if (sum_of_division <= threshold) {
                result = guess;
                upper = guess - 1;
            }
            else
                lower = guess + 1;
        }

        return result;

    }
}
