package com.rainz;

/*
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */

public class KthLargestElementinanArray {
    public static void test(String args[]) {
        int[] input1 = {3,2,1,5,6,4};
        System.out.println(findKthLargest(input1, 2));
        int[] input2 = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(input2, 4));
    }

    // Returns size of "greater" section, including pivot
    private static int partition(int[] nums, int start, int len)
    {
        if (len <= 0)
            return 0;
        int pivot = nums[start];
        int largeIdx = start; // point at the last large number idx
        for (int i = start + 1; i < start + len; ++i) {
            if (pivot <= nums[i]) {
                ++largeIdx;
                int tmp = nums[largeIdx];
                nums[largeIdx] = nums[i];
                nums[i] = tmp;
            }
        }
        // Make pivot the last one in "greater" section, since all others are >= pivot
        nums[start] = nums[largeIdx];
        nums[largeIdx] = pivot;
        return largeIdx - start + 1;
    }

    private static int findKth(int[] nums, int start, int len, int k)
    {
        int greaterCount = partition(nums, start, len);
        if (greaterCount == k)
            return nums[start + k - 1];

        if (greaterCount > k)
            return findKth(nums, start, greaterCount-1, k);
        else
            return findKth(nums, start+greaterCount, len-greaterCount, k-greaterCount);
    }

    public static int findKthLargest(int[] nums, int k) {
        return findKth(nums, 0, nums.length, k);
    }

}
