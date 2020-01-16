package com.rainz;

/*
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 */

public class RangeSumQueryMutable {
    public static void test(String args[]) {
        int[] nums = {1, 3, 5};
        RangeSumQueryMutable r = new RangeSumQueryMutable(nums);
        System.out.println(r.sumRange(0, 2)); // -> 9
        r.update(1, 2);
        System.out.println(r.sumRange(0, 2)); // -> 8
    }

    // See https://www.cnblogs.com/grandyang/p/4985506.html
    private int[] tree; // segment tree
    private int N;

    public RangeSumQueryMutable(int[] nums) {
        N = nums.length;
        tree = new int[2* this.N]; // leave element 0 unused
        for (int i = N; i < N * 2; ++i) {
            tree[i] = nums[i - N];
        }
        for (int i = N - 1; i > 0; --i) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int i, int val) {
        i += N;
        tree[i] = val;
        while (i > 0) {
            tree[i / 2] = tree[i] + tree[i ^ 1]; // even and odd, i^1 flips even to odd and odd to even
            i /= 2;
        }
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        // Use +N to redirect to start of actual array (aka, leaves of tree)
        for (i += N, j += N; i <= j; i /= 2, j /= 2) {
            // If i is odd, it's the right child. Add it
            // If i is even, both left & right are included, so one of its parent will include it.
            if ((i & 1) == 1)
                sum += tree[i++];
            // If j is even, it's the left child. Add it.
            // If j is odd, both left & right are included, so one of its parent will include it.
            if ((j & 1) == 0)
                sum += tree[j--];
        }
        return sum;
    }
}

