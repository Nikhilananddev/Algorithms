package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class NumberOfBurgersWithNoWasteOfIngredients {
    public static void test(String args[]) {
        System.out.println(numberOfBurgersWithNoWasteOfIngredients(16, 7));
    }

    public static List<Integer> numberOfBurgersWithNoWasteOfIngredients(int tomatoSlices, int cheeseSlices) {
        List<Integer> result = new ArrayList<>();
        if (tomatoSlices % 2 == 0 && tomatoSlices >= 0 && cheeseSlices >= 0) {
            int jumbo = tomatoSlices/2 - cheeseSlices;
            if (jumbo >= 0 && jumbo <= cheeseSlices) {
                result.add(jumbo);
                result.add(cheeseSlices-jumbo);
            }
        }
        return result;
    }
}
