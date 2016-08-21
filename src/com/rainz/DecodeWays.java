package com.rainz;

/**
 * Created by Yu on 8/21/2016.
 */
public class DecodeWays {
    public static void test(String args[]) {
        //String s = "123214";
        //String s = "10";
        String s = "27";
        System.out.println(numDecodings(s));
    }

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int idx = s.length() - 1;
        int prev2 = 0, prev1 = 1;
        int curr = s.charAt(idx--) == '0' ? 0 : 1;
        while (idx >= 0) {
            prev2 = prev1;
            prev1 = curr;
            char digit = s.charAt(idx);
            if (digit == '0') {
                curr = 0;
            } else if (digit == '1' || digit == '2' && s.charAt(idx+1) <= '6') {
                curr = prev2 + prev1;
            } else {
                curr = prev1;
            }
            --idx;
        }
        return curr;
    }
}
