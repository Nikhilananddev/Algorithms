package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 */
public class IntegertoEnglishWords {
    public static void test(String args[]) {
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1000000));
    }
    private static List<String> n2w3Digit(int num) {
        final String[] strsX = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        final String[] strs1X = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        final String[] strsX0 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        List<String> res = new ArrayList<>();
        if (num >= 100) {
            res.add(strsX[num/100]);
            res.add("Hundred");
            num %= 100;
        }
        if (num < 20 && num >= 10)
            res.add(strs1X[num-10]);
        else {
            if (num >= 20) {
                res.add(strsX0[num/10]);
                num %= 10;
            }
            if (num > 0)
                res.add(strsX[num]);
        }
        return res;
    }
    public static String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        final String[] strsKs = {"", "Thousand", "Million", "Billion"};
        int idxK = 0;
        List<String> ans = null;
        while (num > 0) {
            int d3 = num % 1000;
            num /= 1000;
            if (d3 > 0) {
                List<String> curr = n2w3Digit(d3);
                if (idxK > 0)
                    curr.add(strsKs[idxK]);
                if (ans != null)
                    curr.addAll(ans);
                ans = curr;
            }
            ++idxK;
        }
        return String.join(" ", ans);
    }
}
