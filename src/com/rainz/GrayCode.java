package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/2/2015.
 */
public class GrayCode {
    public static void test(String args[]) {
        System.out.println(grayCode(1));
        System.out.println(grayCode(2));
        System.out.println(grayCode(3));
        System.out.println(grayCode(4));
    }

    protected static void helper(int bit, int num, boolean reverse, List<Integer> answer) {
        int[] currBits = new int[2];
        if (reverse) {
            currBits[0] = bit;
            currBits[1] = 0;
        } else {
            currBits[0] = 0;
            currBits[1] = bit;
        }
        int gray1 = currBits[0] | num;
        int gray2 = currBits[1] | num;
        if (bit == 1) {
            answer.add(gray1);
            answer.add(gray2);
            return;
        }
        helper(bit >> 1, gray1, false, answer);
        helper(bit >> 1, gray2, true, answer);
    }

    public static List<Integer> grayCode(int n) {
        int bit = 1 << (n-1);
        List<Integer> answer = new ArrayList<Integer>();
        if (n > 0)
            helper(bit, 0, false, answer);
        else
            answer.add(0);
        return answer;
    }
}
