package com.rainz;

public class StringCompression {
    public static void test(String args[]) {
        char[] input1 = {'a','a','b','b','c','c','c'};
        System.out.println(compress(input1));
        Main.printArray(input1);
        char[] input2 = {'a'};
        System.out.println(compress(input2));
        Main.printArray(input2);
        char[] input3 = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(input3));
        Main.printArray(input3);
    }
    public static int compress(char[] chars) {
        int writeIdx = 0;
        int count = 0;
        char prev = '\0';
        // Note the "<=" below
        for (int readIdx = 0; readIdx <= chars.length; ++readIdx) {
            char curr = '\0';
            if (readIdx < chars.length) {
                curr = chars[readIdx];
                if (curr == prev) {
                    ++count;
                    continue;
                }
            }
            if (prev != '\0') {
                chars[writeIdx++] = prev;
                if (count > 1) { // don't forget to write count only for 2 or more
                    char[] countBuffer = new char[4];
                    char countIdx = 0;
                    while (count > 0) {
                        countBuffer[countIdx++] = (char) ('0' + count % 10);
                        count /= 10;
                    }
                    while (countIdx > 0) {
                        chars[writeIdx++] = countBuffer[--countIdx];
                    }
                }
            }
            count = 1;
            prev = curr;
        }
        return writeIdx;
    }
}
