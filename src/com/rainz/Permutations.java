package com.rainz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2/7/2015.
 */
public class Permutations {
    public static void test(String args[]) {
        int[] test = {1, 2, 3};
        List<List<Integer>> answer = permute(test);
        System.out.println(answer);
    }

    protected static void helper(int[] num, int start, List<Integer> perm, List<List<Integer>> answer) {
        if (start >= num.length) {
            List<Integer> sol = new ArrayList<Integer>();
            sol.addAll(perm);
            answer.add(sol);
            return;
        }
        for (int i = start; i < num.length; ++i) {
            int numStart = num[start];
            int numI = num[i];
            num[start] = numI;
            num[i] = numStart;
            perm.add(numI);
            helper(num, start + 1, perm, answer);
            perm.remove(perm.size()-1);
            num[start] = numStart;
            num[i] = numI;
        }
    }

    public static List<List<Integer>> permute(int[] num) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        helper(num, 0, perm, answer);
        return answer;
    }
}
