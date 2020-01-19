package com.rainz;

/*
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {
    public static void test(String args[]) {
        int[] input1 = {3,2,3};
        System.out.println(majorityElement(input1));
        int[] input2 = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(input2));
    }

    /*
     * O(n) time and constant space (aka no hash table).
     * If each of the majority numbers cancels with another number, there will be at least 1 majority number left.
     * So, if curr number equals prev number, increase count; else, cancel them out (aka decrease count).
     * If count reaches 0, wait for the next 2 numbers and repeat.
     */
    public static int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 0;
        for (int num: nums) {
            if (count == 0 || num == majority) {
                majority = num;
                ++count;
            } else
                --count;
        }
        return majority;
    }
}
