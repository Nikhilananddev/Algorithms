package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 */
public class RestoreIPAddresses {
    public static void test(String args[]) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
    private static void helper(String s, int startIdx, int[] ip, int ipIdx, List<String> result) {
        if (startIdx == s.length() && ipIdx == 4) {
            result.add(String.format("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]));
            return;
        }
        if (startIdx >= s.length() || ipIdx >= 4)
            return;
        int num = s.charAt(startIdx) - '0';
        if (num < 0 || num > 9)
            return;
        ip[ipIdx] = num;
        helper(s, startIdx + 1, ip, ipIdx + 1, result);
        if (num != 0 && startIdx+1 < s.length()) {
            num = num*10 + s.charAt(startIdx+1) - '0';
            ip[ipIdx] = num;
            helper(s, startIdx + 2, ip, ipIdx + 1, result);
            if (startIdx+2 < s.length()) {
                num = num*10 + s.charAt(startIdx+2) - '0';
                if (num < 256) {
                    ip[ipIdx] = num;
                    helper(s, startIdx + 3, ip, ipIdx + 1, result);
                }
            }
        }
    }
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int[] ip = new int[4];
        helper(s, 0, ip, 0, result);
        return result;
    }
}
