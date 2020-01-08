package com.rainz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindCommonCharacters {
    public static void test(String args[]) {
        String[] input1 = {"bella","label","roller"};
        Main.printList(commonChars(input1));
        String[] input2 = {"cool","lock","cook"};
        Main.printList(commonChars(input2));
    }

    public static List<String> commonChars(String[] A) {
        Map<Character, Integer> allFreqs = null;
        for (String s: A) {
            Map<Character, Integer> freqs = new HashMap<>();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                freqs.put(c, freqs.getOrDefault(c, 0)+1);
            }
            if (allFreqs == null)
                allFreqs = freqs;
            else {
                for (Map.Entry<Character, Integer> entry: allFreqs.entrySet()) {
                    int val = freqs.getOrDefault(entry.getKey(), 0);
                    entry.setValue(Math.min(entry.getValue(), val));
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry: allFreqs.entrySet()) {
            for (int i = 0; i < entry.getValue(); ++i)
                ans.add(""+entry.getKey());
        }
        return ans;
    }
}
