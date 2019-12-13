package com.rainz;

public class MinimumSwapsToMakeStringsEqual {
    public static void test(String args[]) {
        System.out.println(minimumSwap("xx", "yy"));
        System.out.println(minimumSwap("xy", "yx"));
        System.out.println(minimumSwap("xx", "xy"));
        System.out.println(minimumSwap("xxyyxyxyxx", "xyyxyxxxyx"));
    }

    public static int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length())
            return -1;
        int xy = 0;
        int yx = 0;
        for (int i = 0; i < s1.length(); ++i) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == c2)
                continue;
            if (c1 == 'x' && c2 == 'y')
                ++xy;
            else
                ++yx;
        }
        // A pair of same pattern (x/y and x/y, or y/x and y/x) only needs 1 swap
        int result = xy/2 + yx/2;
        int xymod2 = xy % 2;
        int yxmod2 = yx % 2;
        if (xymod2 != yxmod2)
            return -1;
        // A pair of different patterns (x/y and y/x) needs 2 swaps
        result += 2 * xymod2;
        return result;
    }
}
