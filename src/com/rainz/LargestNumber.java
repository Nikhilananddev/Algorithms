package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Given a list of non negative integers, arrange them such that they form the largest number.
 */

public class LargestNumber {
    public static void test(String args[]) {
        int[] input1 = {10,2};
        System.out.println(largestNumber(input1));
        int[] input2 = {3,30,34,5,9};
        System.out.println(largestNumber(input2));
        int[] input3 = {0,0};
        System.out.println(largestNumber(input3));
    }

    public static String largestNumber(int[] nums) {
        List<String> numStrs = new ArrayList<>();
        for (int n: nums)
            numStrs.add(""+n);
        Collections.sort(numStrs, (x, y) -> (y+x).compareTo(x+y));
        StringBuilder sb = new StringBuilder();
        for (String s: numStrs)
            sb.append(s);
        // Trim leading 0's. This is only needed if nums contains all 0's.
        int lead = 0;
        for (; lead < sb.length() - 1; ++lead) {
            if (sb.charAt(lead) != '0')
                break;
        }
        sb.delete(0, lead);
        return sb.toString();
    }

}
