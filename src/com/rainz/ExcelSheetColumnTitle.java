package com.rainz;

public class ExcelSheetColumnTitle {
    public static void test(String args[]) {
        System.out.println(convertToTitle(1));
        System.out.println(convertToTitle(28));
        System.out.println(convertToTitle(701));
    }

    public static String convertToTitle(int n) {
        StringBuilder title = new StringBuilder();
        while (n > 0) {
            int d = n % 26;
            // Note the handling of d == 0 below
            if (d == 0) {
                d = 26;
                n -= 26;
            }
            title.append((char)('A' + d - 1));
            n /= 26;
        }
        title.reverse();
        return title.toString();
    }
}
