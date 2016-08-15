package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
	public static void test(String args[]) {
	    int[] test = {1, 2, 4, 8, 3, 6, 9, 18};
	    System.out.println(largestDivisibleSubset(test));
	}
	
	private static class Record {
		public int previous;
		public int count;
		public Record(int prev, int c) {
			previous = prev;
			count = c;
		}
	}

	public static List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> result = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
        	return result;
        }
        Arrays.sort(nums);
        
        Record[] records = new Record[n];
        records[n-1] = new Record(-1, 1);
        int overallMaxIdx = n - 1;
        for (int i = n - 2; i >= 0; --i) {
        	int maxIdx = -1;
        	for (int multipleIdx = i + 1; multipleIdx < n; ++multipleIdx) {
        		if (nums[multipleIdx] % nums[i] != 0) {
        			continue;
        		}
        		if (maxIdx == -1 || records[multipleIdx].count > records[maxIdx].count) {
        			maxIdx = multipleIdx;
        		}
        	}
        	int currCount = maxIdx == -1 ? 1 : (records[maxIdx].count + 1);
        	records[i] = new Record(maxIdx, currCount);
        	if (records[overallMaxIdx].count < currCount) {
        		overallMaxIdx = i;
        	}
        }
        int resultIdx = overallMaxIdx;
        do {
        	result.add(nums[resultIdx]);
        	resultIdx = records[resultIdx].previous;
        } while (resultIdx != -1);
        
        return result;
    }
}
