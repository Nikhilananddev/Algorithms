package com.rainz;

/**
 * Created by Yu on 2/1/2015.
 */
public class CountandSay {
    public static void test(String args[]) {
        for (int i = 1; i <= 6; ++i)
            System.out.println(countAndSay(i));
    }

    public static String countAndSay(int n) {
        String curr = "1";
        int idx = 1;

        while (idx < n) {
            StringBuilder blder = new StringBuilder();
            char prevChar = ' ';
            int count = 0;
            for (int i = 0; i < curr.length(); ++i) {
                char c = curr.charAt(i);
                if (i > 0 && c != prevChar) {
                    blder.append(count);
                    blder.append(prevChar);
                    count = 1;
                } else
                    ++count;
                prevChar = c;
            }
            blder.append(count);
            blder.append(prevChar);
            curr = blder.toString();
            ++idx;
        }
        return curr;
    }
}
