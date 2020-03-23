package com.rainz;

import com.rainz.Main.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 */
public class NestedListWeightSum {
    public static void test(String args[]) {
        List<NestedInteger> input1 = new ArrayList<>();
        NestedInteger n11 = new NestedInteger();
        n11.add(new NestedInteger(1));
        n11.add(new NestedInteger(1));
        input1.add(n11);
        input1.add(new NestedInteger(2));
        NestedInteger n12 = new NestedInteger();
        n12.add(new NestedInteger(1));
        n12.add(new NestedInteger(1));
        input1.add(n12);
        System.out.println(depthSum(input1));

        List<NestedInteger> input2 = new ArrayList<>();
        input2.add(new NestedInteger(1));
        NestedInteger n21 = new NestedInteger();
        n21.add(new NestedInteger(4));
        NestedInteger n31 = new NestedInteger();
        n31.add(new NestedInteger(6));
        n21.add(n31);
        input2.add(n21);
        System.out.println(depthSum(input2));
    }

    private static int compute(NestedInteger ni, int level) {
        if (ni.isInteger())
            return ni.getInteger()*level;
        int sum = 0;
        for (NestedInteger n: ni.getList()) {
            sum += compute(n, level+1);
        }
        return sum;
    }

    public static int depthSum(List<NestedInteger> nestedList) {
        int ans = 0;
        for (NestedInteger ni: nestedList)
            ans += compute(ni, 1);
        return ans;
    }
}
