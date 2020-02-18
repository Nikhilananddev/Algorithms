package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Note:
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */

public class EncodeandDecodeStrings {
    public static void test(String args[]) {
        List<String> input1 = List.of("", "abc", "defg", "", "", "hijklmn", "");
        System.out.println(decode(encode(input1)));
        List<String> input2 = List.of("|1234", "abc", "", "1", "");
        System.out.println(decode(encode(input2)));
        List<String> input3 = List.of("1234", "|1234", "abc", "defg", "", "", "hijklmn");
        System.out.println(decode(encode(input3)));
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s: strs) {
            sb.append(s.length());
            sb.append('|');
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        int idx = 0;
        List<String> ans = new ArrayList<>();
        while (idx < s.length()) {
            int dIdx = s.indexOf('|', idx);
            String lenStr = s.substring(idx, dIdx);
            int len = Integer.parseInt(lenStr);
            int nextIdx = dIdx+1+len;
            String str = s.substring(dIdx+1, nextIdx);
            ans.add(str);
            idx = nextIdx;
        }
        return ans;
    }
}
