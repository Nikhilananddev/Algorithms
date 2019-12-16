package com.rainz;

import java.util.ArrayList;
import java.util.List;

public class MaximumLengthofaConcatenatedStringwithUniqueCharacters {
    public static void test(String args[]) {
        List<String> input1 = List.of("un","iq","ue");
        System.out.println(maxLength(input1));
        List<String> input2 = List.of("cha","r","act","ers");
        System.out.println(maxLength(input2));
        List<String> input3 = List.of("abcdefghijklmnopqrstuvwxyz");
        System.out.println(maxLength(input3));
        List<String> input4 = List.of("yy","bkhwmpbiisbldzknpm");
        System.out.println(maxLength(input4));
    }

    private static int helper(List<String> arr, int[] masks, int start, int len, int mask, int max) {
        if (start >= masks.length)
            return max;
        int startMask = masks[start];
        if ( startMask != -1 &&(startMask & mask) == 0 ) {
            // no duplicate letter found, use this word
            int newLen = len + arr.get(start).length();
            if (newLen > max)
                max = newLen;
            newLen = helper(arr, masks, start+1, newLen, (startMask | mask), max);
            if (newLen > max)
                max = newLen;
        }
        int newLen = helper(arr, masks, start+1, len, mask, max);
        if (newLen > max)
            max = newLen;
        return max;
    }

    public static int maxLength(List<String> arr) {
        int[] masks = new int[arr.size()];
        for (int i = 0; i < arr.size(); ++i) {
            String s = arr.get(i);
            int mask = 0;
            for (int sidx = 0; sidx < s.length(); ++sidx) {
                char c = s.charAt(sidx);
                int bit = 1 << (c - 'a');
                if ((mask & bit) != 0) {
                    // This word has dup, cannot use it
                    mask = -1;
                    break;
                }
                mask |= bit;
            }
            masks[i] = mask;
        }
        return helper(arr, masks, 0, 0, 0, 0);
    }

}
