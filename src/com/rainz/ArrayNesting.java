package com.rainz;

public class ArrayNesting {
    public static void test(String args[]) {
        int[] A = {5,4,0,3,1,6,2};
        System.out.println(arrayNesting(A));
    }

    public static int arrayNesting(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == -1)
                continue;
            int len = 0;
            int curr = i;
            while (curr != -1) {
                ++len;
                int next = nums[curr];
                nums[curr] = -1;
                curr = next;
            }
            --len; // the initial number was counted again at the end
            if (len > ans)
                ans = len;
        }
        return ans;
    }
}
