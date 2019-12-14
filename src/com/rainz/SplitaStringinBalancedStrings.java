package com.rainz;

public class SplitaStringinBalancedStrings {
    public static void test(String args[]) {
        System.out.println(balancedStringSplit("RLRRLLRLRL"));
        System.out.println(balancedStringSplit("RLLLLRRRLR"));
        System.out.println(balancedStringSplit("LLLLRRRR"));
        System.out.println(balancedStringSplit("RLRRRLLRLL"));
    }

    public static int balancedStringSplit(String s) {
        int result = 0;
        int Ls = 0;
        int Rs = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == 'L')
                ++Ls;
            else
                ++Rs;
            if (Ls == Rs) {
                ++result;
                Ls = 0;
                Rs = 0;
            }
        }
        return result;
    }
}
