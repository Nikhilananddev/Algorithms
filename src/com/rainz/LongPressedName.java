package com.rainz;

/*
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 */
public class LongPressedName {
    public static void test(String args[]) {
        System.out.println(isLongPressedName("alex", "aaleex"));
        System.out.println(isLongPressedName("saeed", "ssaaedd"));
        System.out.println(isLongPressedName("leelee", "lleeelee"));
        System.out.println(isLongPressedName("laiden", "laiden"));
        System.out.println(isLongPressedName("vtkgn", "vttkgnn"));
    }
    public static boolean isLongPressedName(String name, String typed) {
        int nIdx = 0, tIdx = 0;
        int N = name.length(), T = typed.length();
        while (nIdx < N && tIdx < T) {
            char n = name.charAt(nIdx);
            char t = typed.charAt(tIdx);
            if (n == t) {
                ++nIdx; ++tIdx;
                continue;
            }
            if (tIdx < 1 || typed.charAt(tIdx-1) != t)
                return false;
            while (tIdx < T && typed.charAt(tIdx) == t)
                ++tIdx;
        }
        if (nIdx < N)
            return false;
        if (tIdx < T) {
            if (tIdx == 0)
                return false;
            // If there are more in typed, they should be repeats of last char
            char lastT = typed.charAt(tIdx-1);
            while (tIdx < T) {
                if (typed.charAt(tIdx) != lastT)
                    return false;
                ++tIdx;
            }
        }
        return true;
    }

}
