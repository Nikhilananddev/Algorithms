package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RabbitsinForest {
    public static void test(String args[]) {
        int[] input1 = {1, 1, 2};
        System.out.println(numRabbits(input1));
        int[] input2 = {10, 10, 10};
        System.out.println(numRabbits(input2));
        int[] input3 = {};
        System.out.println(numRabbits(input3));
    }

    public static int numRabbits(int[] answers) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < answers.length; ++i) {
            int ans = answers[i];
            Integer rabbits = table.get(ans);
            if (rabbits == null)
                table.put(ans, 1);
            else
                table.put(ans, rabbits+1);
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry: table.entrySet()) {
            int groupSize = entry.getKey() + 1; // add self to count
            int rabbits = entry.getValue();
            int groupCount = rabbits / groupSize;
            if (rabbits % groupSize != 0)
                ++groupCount;
            result += groupCount * groupSize;
        }
        return result;
    }

}
