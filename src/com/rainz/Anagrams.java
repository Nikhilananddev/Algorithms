package com.rainz;

import java.util.*;

/**
 * Created by Yu on 2/7/2015.
 */
public class Anagrams {
    public static void test(String args[]) {
        String[] test = {"abc", "dbac", "cba", "dcba", "bacd", "bbdca", "abcd"};
        System.out.println(anagrams(test));
    }

    public static List<String> anagrams(String[] strs) {
        HashMap<String, List<String>> anaTable = new HashMap<String, List<String>>();
        for (String s: strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            List<String> strList = anaTable.get(sorted);
            if (strList == null) {
                strList = new ArrayList<String>();
                anaTable.put(sorted, strList);
            }
            strList.add(s);
        }
        List<String> answer = new ArrayList<String>();
        for (Map.Entry<String, List<String>> entry: anaTable.entrySet()) {
            List<String> strList = entry.getValue();
            if (strList.size() < 2)
                continue;
            answer.addAll(strList);
        }
        return answer;
    }
}
