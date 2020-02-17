package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 */
public class GroupShiftedStrings {
    public static void test(String args[]) {
        String[] input1 = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        Main.printList2D(groupStrings(input1));
    }

    public static List<List<String>> groupStrings(String[] strings) {
        Map<List<Integer>, List<String>> shiftMap = new HashMap<>();
        for (String s: strings) {
            List<Integer> diffs = new ArrayList<>();
            for (int i = 1; i < s.length(); ++i) {
                char c2 = s.charAt(i), c1 = s.charAt(i - 1);
                int diff = c2 >= c1 ? c2-c1 : c2+26-c1;
                diffs.add(diff);
            }
            shiftMap.putIfAbsent(diffs, new ArrayList<>());
            shiftMap.get(diffs).add(s);
        }
        List<List<String>> ans = new ArrayList<>();
        ans.addAll(shiftMap.values());
        return ans;
    }
}
