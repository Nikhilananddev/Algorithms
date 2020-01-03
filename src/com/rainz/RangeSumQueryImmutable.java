package com.rainz;

public class RangeSumQueryImmutable {
    public static void test(String args[]) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        RangeSumQueryImmutable s = new RangeSumQueryImmutable(nums);
        System.out.println(s.sumRange(0, 2));
        System.out.println(s.sumRange(2, 5));
        System.out.println(s.sumRange(0, 5));
    }

    private int[] nums;
    private int[] sums;

    public RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;
        sums = new int[nums.length+1];
        sums[0] = 0;
        for (int i = 1; i < sums.length; ++i) {
            sums[i] = sums[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
}
