package com.rainz;

/*
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 */

import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame {
    public static void test(String args[]) {
        int[] input1 = {4, 1, 8, 7};
        System.out.println(judgePoint24(input1));
        int[] input2 = {1, 2, 1, 2};
        System.out.println(judgePoint24(input2));
        int[] input3 = {8, 1, 6, 6};
        System.out.println(judgePoint24(input3));
    }

    private static boolean helper(List<Double> cards) {
        final double EPS = 0.001;
        if (cards.size() == 1)
            return Math.abs(cards.get(0)-24) < EPS;
        for (int i = 0; i < cards.size(); ++i) {
            for (int j = i + 1; j < cards.size(); ++j) {
                double a = cards.get(i), b = cards.get(j);
                List<Double> newNums = new ArrayList<>();
                newNums.add(a+b);
                newNums.add(Math.abs(a-b));
                newNums.add(a*b);
                if (a > EPS)
                    newNums.add(b/a);
                if (b > EPS)
                    newNums.add(a/b);
                List<Double> next = new ArrayList<>();
                for (int idx = 0; idx < cards.size(); ++idx) {
                    if (idx != i && idx != j)
                        next.add(cards.get(idx));
                }
                for (double n: newNums) {
                    next.add(n);
                    if (helper(next))
                        return true;
                    next.remove(next.size()-1);
                }
            }
        }
        return false;
    }

    public static boolean judgePoint24(int[] nums) {
        List<Double> cards = new ArrayList<>();
        for (int n: nums)
            cards.add((double)n);
        return helper(cards);
    }
}
