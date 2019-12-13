package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class CountNumberOfNiceSubarrays {
    public static void test(String args[]) {
        int[] input1 = {1,1,2,1,1};
        int[] input2 = {2,4,6};
        int[] input3 = {2,2,2,1,2,2,1,2,2,2};

        System.out.println(numberOfSubarrays(input1, 3));
        System.out.println(numberOfSubarrays(input2, 1));
        System.out.println(numberOfSubarrays(input3, 2));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        List<Integer> oddIndices = new ArrayList<>();
        oddIndices.add(-1);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 1)
                oddIndices.add(i);
        }
        oddIndices.add(nums.length);
        int result = 0;
        int oddEnd = k; // start with [1, k]
        while (oddEnd < oddIndices.size()-1) {
            int oddStart = oddEnd - k + 1;
            int before = oddIndices.get(oddStart) - oddIndices.get(oddStart-1);
            int after = oddIndices.get(oddEnd+1) - oddIndices.get(oddEnd);
            result += before*after;
            ++oddEnd;
        }
        return result;
    }
}
