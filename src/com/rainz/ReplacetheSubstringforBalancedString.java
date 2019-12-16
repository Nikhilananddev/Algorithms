package com.rainz;

public class ReplacetheSubstringforBalancedString {
    public static void test(String args[]) {
        System.out.println(balancedString(""));
        System.out.println(balancedString("QWER"));
        System.out.println(balancedString("QQWE"));
        System.out.println(balancedString("QQQW"));
        System.out.println(balancedString("QQQQ"));
        System.out.println(balancedString("WQWRQQQW"));
        System.out.println(balancedString("RQQERWEWWREQEQWR"));
    }

    public static int balancedString(String s) {
        final String letters = "QWER";
        int[] mapping = new int[26];
        for (int i = 0; i < letters.length(); ++i)
            mapping[letters.charAt(i)-'A'] = i;

        int[] allFreqs = new int[letters.length()];
        for (int i = 0; i < s.length(); ++i) {
            int code = mapping[s.charAt(i)-'A'];
            ++allFreqs[code];
        }
        int avg = s.length()/4;
        int positives = 0;
        for (int i = 0; i < allFreqs.length; ++i) {
            allFreqs[i] -= avg;
            if (allFreqs[i] > 0)
                positives += allFreqs[i];
        }
        if (positives == 0)
            return 0; // already balanced

        // Some letters are above avg, others are below. But total #s above and below should be equal.
        // We just need to replace the letters above avg with letters below avg to balance the string.
        // So, expand/shrink window to find smallest window containing positive allFreqs.
        int start = 0;
        int end = 0; // [start, end)
        int[] freqs = new int[letters.length()];
        int total = 0;
        int result = Integer.MAX_VALUE;
        while (end < s.length()) {
            if (total < positives) {
                // Expand
                ++end;
                int code = mapping[s.charAt(end-1)-'A'];
                ++freqs[code];
                if (freqs[code] <= allFreqs[code])
                    ++total;
            }
            while (total >= positives && start < end) {
                // Check if new window is smaller
                int len = end - start;
                if (len < result)
                    result = len;
                // Shrink as much as possible
                int code = mapping[s.charAt(start)-'A'];
                --freqs[code];
                if (freqs[code] < allFreqs[code])
                    --total;
                ++start;
            }
        }
        return result;
    }
}
