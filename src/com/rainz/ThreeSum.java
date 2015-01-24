package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 1/24/2015.
 */
public class ThreeSum {
    public static void test(String args[]) {
        int[] test = {3, -2, 5, 1, -3, -2, 1, 3, 4, -4, 1, -1};
        List<List<Integer>> answer = threeSum(test);
        for (List<Integer> sol: answer) {
            for (Integer i: sol) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        for (int i = 0; i < num.length - 2; ++i) {
            int curr = num[i];
            if (i > 0 && curr == num[i-1]) {
                continue;
            }
            int leftIdx = i + 1;
            int rightIdx = num.length - 1;
            int target = - curr;
            while (leftIdx < rightIdx) {
                int left = num[leftIdx];
                int right = num[rightIdx];
                int sum2 = left + right;
                if (sum2 == target) {
                    List<Integer> sol = new ArrayList<Integer>();
                    sol.add(num[i]);
                    sol.add(left);
                    sol.add(right);
                    answer.add(sol);
                    // Skip all equal numbers
                    do {
                        ++leftIdx;
                    } while (leftIdx < num.length && num[leftIdx] == left);
                    do {
                        --rightIdx;
                    } while (rightIdx >= leftIdx && num[rightIdx] == right);
                } else if (sum2 < target) {
                    ++leftIdx;
                } else {
                    --rightIdx;
                }
            }
        }
        return answer;
    }
}
