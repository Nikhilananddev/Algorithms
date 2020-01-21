package com.rainz;

import java.util.*;

/*
 * Given an array of strings, group anagrams together.
 */

public class GroupAnagrams {
    public static void test(String args[]) {
        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Main.printList2D(groupAnagrams(input1));
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> anaTable = new HashMap<>();
        for (String s: strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);;
            String ana = new String(arr);
            List<String> group = anaTable.get(ana);
            if (group == null) {
                group = new ArrayList<>();
                anaTable.put(ana, group);
            }
            group.add(s);
        }
        ans.addAll(anaTable.values());
        return ans;
    }

    // Old
    public static List<String> anagrams(String[] strs) {
        HashMap<String, List<String>> anaTable = new HashMap<>();
        for (String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            List<String> strList = anaTable.get(sorted);
            if (strList == null) {
                strList = new ArrayList<>();
                anaTable.put(sorted, strList);
            }
            strList.add(s);
        }
        List<String> answer = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: anaTable.entrySet()) {
            List<String> strList = entry.getValue();
            if (strList.size() < 2)
                continue;
            answer.addAll(strList);
        }
        return answer;
    }
}
