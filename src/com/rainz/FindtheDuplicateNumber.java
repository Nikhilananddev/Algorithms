package com.rainz;

/*
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindtheDuplicateNumber {
    public static void test(String args[]) {
        int[] input1 = {1,3,4,2,2};
        System.out.println(findDuplicate(input1));
        int[] input2 = {3,1,3,4,2};
        System.out.println(findDuplicate(input2));
    }

    /*
     * O(nlogn) solution: binary search within numbers [1, n].
     * For each mid, count numbers <= mid.
     * If count<=mid, target is in upper region; else in lower region.
     */
    public static int findDuplicate(int[] nums) {
        int lower = 1, upper = nums.length - 1;
        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            int lessCount = 0;
            int equalCount = 0;
            for (int n: nums) {
                if (n == mid) {
                    ++equalCount;
                    if (equalCount > 1)
                        return n;
                }
                if (n <= mid)
                    ++lessCount;
            }
            if (lessCount <= mid)
                lower = mid + 1;
            else
                upper = mid -1;
        }
        return -1;
    }

    /*
     * O(n) solution. View each num n as a node and nums[n] as pointer to next node.
     * This becomes a find cycle in linked list problem.
     * Since there is dup, there is a cycle where two numbers point at same location.
     * Also, since you start from 0, you won't get into self-looping problem.
     */
    public static int findDuplicateCycle(int[] nums) {
        int slow = 0, fast = 0, t = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }
        while (true) {
            slow = nums[slow];
            t = nums[t];
            if (slow == t)
                break;
        }
        return slow;
    }
}
