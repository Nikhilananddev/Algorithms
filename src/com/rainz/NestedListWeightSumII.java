package com.rainz;

import com.rainz.Main.NestedInteger;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {
    public static void test(String args[]) {
        List<Main.NestedInteger> input1 = new ArrayList<>();
        Main.NestedInteger n11 = new Main.NestedInteger();
        n11.add(new Main.NestedInteger(1));
        n11.add(new Main.NestedInteger(1));
        input1.add(n11);
        input1.add(new Main.NestedInteger(2));
        Main.NestedInteger n12 = new Main.NestedInteger();
        n12.add(new Main.NestedInteger(1));
        n12.add(new Main.NestedInteger(1));
        input1.add(n12);
        System.out.println(depthSumInverse(input1));

        List<Main.NestedInteger> input2 = new ArrayList<>();
        input2.add(new Main.NestedInteger(1));
        Main.NestedInteger n21 = new Main.NestedInteger();
        n21.add(new Main.NestedInteger(4));
        Main.NestedInteger n31 = new Main.NestedInteger();
        n31.add(new Main.NestedInteger(6));
        n21.add(n31);
        input2.add(n21);
        System.out.println(depthSumInverse(input2));
    }

    // Compute sum level by level
    public static int depthSumInverse(List<NestedInteger> nestedList) {
        List<Integer> sums = new ArrayList<>();
        List<NestedInteger> currLevel = nestedList;
        while (!currLevel.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            int sum = 0;
            for (NestedInteger ni: currLevel) {
                if (ni.isInteger())
                    sum += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            sums.add(sum);
            currLevel = nextLevel;
        }
        int ans = 0;
        for (int i = 0; i < sums.size(); ++i)
            ans += sums.get(i) * (sums.size() - i);
        return ans;
    }
}
