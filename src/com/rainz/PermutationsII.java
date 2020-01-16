package com.rainz;

import java.util.*;

/**
 * Created by Yu on 2/7/2015.
 */
public class PermutationsII {
    public static void test(String args[]) {
        int[] test = {1, 1, 2};
        List<List<Integer>> ans = permuteUnique(test);
        Main.printList2D(ans);
        System.out.println("" + ans.size() + " entries");
        int[] test2 = {-1,2,0,-1,1,0,1};
        ans = permuteUnique(test2);
        Main.printList2D(ans);
        System.out.println("" + ans.size() + " entries");
    }

    protected static void helperOld(int[] num, int start, List<Integer> perm, List<List<Integer>> answer) {
        if (start >= num.length) {
            List<Integer> sol = new ArrayList<Integer>();
            sol.addAll(perm);
            answer.add(sol);
            return;
        }
        Set<Integer> seen = new HashSet<Integer>();
        for (int i = start; i < num.length; ++i) {
            int numI = num[i];
            if (seen.contains(numI))
                continue;
            seen.add(numI);
            int numStart = num[start];
            num[start] = numI;
            num[i] = numStart;
            perm.add(numI);
            helperOld(num, start + 1, perm, answer);
            perm.remove(perm.size()-1);
            num[start] = numStart;
            num[i] = numI;
        }
    }
    public static List<List<Integer>> permuteUniqueOld(int[] num) {
        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        helperOld(num, 0, perm, answer);
        return answer;
    }


    protected static void helper(int[] nums, int start, List<List<Integer>> ans) {
        if (start >= nums.length) {
            List<Integer> sol = new ArrayList<>();
            for (int n: nums)
                sol.add(n);
            ans.add(sol);
            return;
        }
        int numStart = nums[start];
        Set<Integer> seen = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            int numI = nums[i];
            if (seen.contains(numI))
                continue;
            seen.add(numI);
            nums[start] = numI;
            nums[i] = numStart;
            helper(nums, start + 1, ans);
            nums[start] = numStart;
            nums[i] = numI;
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(nums, 0, ans);
        return ans;
    }
}
