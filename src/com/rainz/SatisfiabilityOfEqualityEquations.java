package com.rainz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SatisfiabilityOfEqualityEquations {
    public static void test(String args[]) {
        String[] input = {"a==b","b!=a"};
        System.out.println(satisfiabilityOfEqualityEquations(input));
        String[] input2 = {"b==a","a==b"};
        System.out.println(satisfiabilityOfEqualityEquations(input2));
        String[] input3 = {"a==b","b==c","a==c"};
        System.out.println(satisfiabilityOfEqualityEquations(input3));
        String[] input4 = {"a==b","b!=c","c==a"};
        System.out.println(satisfiabilityOfEqualityEquations(input4));
        String[] input5 = {"c==c","b==d","x!=z"};
        System.out.println(satisfiabilityOfEqualityEquations(input5));
    }

    private static int getRootPathCompression(int[] rootTable, int c)
    {
        Stack<Integer> stk = new Stack<>();
        while (rootTable[c] >= 0) {
            stk.push(c);
            c = rootTable[c];
        }
        while (!stk.isEmpty()) {
            int x = stk.pop();
            rootTable[x] = c;
        }
        return c;
    }

    public static boolean satisfiabilityOfEqualityEquations(String[] equations) {
        List<String> notEqual = new ArrayList<>();
        int[] rootTable = new int[26];
        for (int i = 0; i < rootTable.length; ++i)
            rootTable[i] = -1;

        for (String eq: equations) {
            // Process "!=" later
            if (eq.charAt(1) == '!') {
                notEqual.add(eq);
                continue;
            }
            int r1 = getRootPathCompression(rootTable, eq.charAt(0) - 'a');
            int r2 = getRootPathCompression(rootTable, eq.charAt(3) - 'a');
            if (r1 != r2) {
                rootTable[r2] = r1;
            }
        }

        for (String eq: notEqual) {
            int r1 = getRootPathCompression(rootTable, eq.charAt(0) - 'a');
            int r2 = getRootPathCompression(rootTable, eq.charAt(3) - 'a');
            if (r1 == r2) {
                return false;
            }
        }
        return true;
    }
}
