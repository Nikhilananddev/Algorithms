package com.rainz;

public class GrumpyBookstoreOwner {
    public static void test(String args[]) {
        int[] customers1 = {1,0,1,2,1,1,7,5};
        int[] grumpy1 = {0,1,0,1,0,1,0,1};
        System.out.println(maxSatisfied(customers1, grumpy1, 3));
        int[] customers2 = {4,10,10};
        int[] grumpy2 = {1,1,0};
        System.out.println(maxSatisfied(customers2, grumpy2, 2));
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int minSatisfied = 0;
        for (int i = 0; i < customers.length; ++i)
            minSatisfied += customers[i] * (1 - grumpy[i]);
        int ans = minSatisfied;
        int start = 0;
        int end = 0; // exclusive
        int satisfied = minSatisfied;
        while (end < customers.length) {
            ++end;
            if (grumpy[end-1] == 1)
                satisfied += customers[end-1];
            if (end - start > X) {
                if (grumpy[start] == 1)
                    satisfied -= customers[start];
                ++start;
            }
            if (satisfied > ans)
                ans = satisfied;
        }
        return ans;
    }
}
