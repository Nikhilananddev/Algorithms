package com.rainz;

public class FlipGameII {
    public static void test(String args[]) {
        System.out.println(canWin("++++"));
    }

    private static boolean helper(StringBuilder sb) {
        int i = 0;
        while (i < sb.length()-1) {
            if (sb.charAt(i) != '+')
                ++i;
            else if (sb.charAt(i + 1) != '+')
                i += 2;
            else {
                sb.setCharAt(i, '-');
                sb.setCharAt(i + 1, '-');
                boolean opponentWin = helper(sb);
                sb.setCharAt(i, '+');
                sb.setCharAt(i + 1, '+');
                if (!opponentWin)
                    return true;
                ++i;
            }
        }
        return false;
    }

    public static boolean canWin(String s) {
         StringBuilder sb = new StringBuilder(s);
         return helper(sb);
    }
}
