package com.rainz;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIIDatastructuredesign {
    public static void test(String args[]) {
        TwoSumIIIDatastructuredesign sum = new TwoSumIIIDatastructuredesign();
        sum.add(1); sum.add(3); sum.add(5);
        System.out.println(sum.find(4)); // -> true
        System.out.println(sum.find(7)); // -> false
        TwoSumIIIDatastructuredesign sum2 = new TwoSumIIIDatastructuredesign();
        sum2.add(3); sum2.add(1); sum2.add(2);
        System.out.println(sum2.find(3)); // -> true
        System.out.println(sum2.find(6)); // -> false
    }

    Map<Integer, Integer> numTable = new HashMap<>();
    public TwoSumIIIDatastructuredesign() {

    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        numTable.put(number, numTable.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> e: numTable.entrySet()) {
            int k = e.getKey();
            int target = value - k;
            if (target == k) {
                if (e.getValue() > 1)
                    return true;
            } else if (numTable.containsKey(target))
                return true;
        }
        return false;
    }
}
