package com.rainz;

/**
 * Created by Yu on 1/18/2015.
 */
public class ZigZagConversion {
    public static void test(String args[]) {
        System.out.println(convert("PAYPALISHIRING", 1));
        System.out.println(convert("PAYPALISHIRING", 3));
        System.out.println(convert("PAYPALISHIRING", 4));
    }

    public static String convert(String s, int nRows) {
        if (nRows == 1) {
            return s;
        }
        char[] result = new char[s.length()];
        int writeIdx = 0;
        int cycle = 2*nRows - 2;
        for (int row = 0; row < nRows; ++row) {
            for (int i = 0; i < s.length(); i += cycle) {
                int readIdx = i + row;
                if (readIdx < s.length())
                    result[writeIdx++] = s.charAt(readIdx);
                if (row == 0 || row == nRows - 1)
                    continue;
                readIdx = i + cycle - row;
                if (readIdx < s.length())
                    result[writeIdx++] = s.charAt(i + cycle - row);
            }
        }
        return new String(result);
    }
}
