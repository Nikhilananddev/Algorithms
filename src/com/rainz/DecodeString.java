package com.rainz;

public class DecodeString {
    public static void test(String args[]) {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }

    // Returns processed index+1
    private static int helper(String s, int start, int repeat, StringBuilder parentSb) {
        StringBuilder sb = new StringBuilder();
        if (repeat == 0)
            repeat = 1;
        int i = start;
        int count = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else if (c == '[') {
                i = helper(s, i, count, sb);
                count = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        // We either see a ']' or reached the end
        for (int r = 0; r < repeat; ++r)
            parentSb.append(sb.toString());
        return i;
    }

    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        helper(s, 0, 1, sb);
        return sb.toString();
    }
}
