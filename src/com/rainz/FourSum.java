package com.rainz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yu on 1/24/2015.
 */
public class FourSum {
    public static void test(String args[]) {
        int[] test = {1, 1, 0, 0, -1, -1, 0, -2, -2, 2, 2};
        List<List<Integer>> answer = fourSum(test, 0);
        for (List<Integer> sol: answer) {
            for (Integer i: sol) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        for (int i = 0; i < num.length - 3; ++i) {
            int curr1 = num[i];
            if (i > 0 && curr1 == num[i-1]) {
                continue;
            }
            for (int j = i + 1; j < num.length - 2; ++j) {
                int curr2 = num[j];
                if (j > i + 1 && curr2 == num[j - 1]) {
                    continue;
                }
                int leftIdx = j + 1;
                int rightIdx = num.length - 1;
                int sum2Target = target - curr1 - curr2;
                while (leftIdx < rightIdx) {
                    int left = num[leftIdx];
                    int right = num[rightIdx];
                    int sum2 = left + right;
                    if (sum2 == sum2Target) {
                        List<Integer> sol = new ArrayList<Integer>();
                        sol.add(num[i]);
                        sol.add(num[j]);
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
                    } else if (sum2 < sum2Target) {
                        ++leftIdx;
                    } else {
                        --rightIdx;
                    }
                }
            }
        }
        return answer;
    }
}
