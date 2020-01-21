package com.rainz;

/*
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 */
public class DivideChocolate {
    public static void test(String args[]) {
        int[] input1 = {1,2,3,4,5,6,7,8,9};
        System.out.println(maximizeSweetness(input1, 5));
        int[] input2 = {5,6,7,8,9,1,2,3,4};
        System.out.println(maximizeSweetness(input2, 8));
        int[] input3 = {1,2,2,1,2,2,1,2,2};
        System.out.println(maximizeSweetness(input3, 2));
    }
    /*
     * Binary search the max of min sum and try cutting with it.
     */

    private static boolean canCut(int[] nums, int k, long minLimit) {
        long sum = 0;
        int parts = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (sum + nums[i] >= minLimit) {
                ++parts;
                sum = 0;
            } else
                sum += nums[i];
        }
        if (sum >= minLimit)
            ++parts; // last part exceeds limit, so count it
        return parts - 1 >= k;
    }

    public static int maximizeSweetness(int[] sweetness, int K) {
        int minVal = 0;
        for (int n: sweetness) {
            if (n < minVal)
                minVal = n;
        }
        long lo = minVal, hi = Integer.MAX_VALUE;
        int ans = 0;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (canCut(sweetness, K, mid)) {
                ans = (int)mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

}
