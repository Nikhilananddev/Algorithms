package com.rainz;

/*
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 */

import java.util.ArrayList;
import java.util.List;

public class FlipGame {
    public static void test(String args[]) {
        System.out.println(generatePossibleNextMoves("++++"));
    }

    public static List<String> generatePossibleNextMoves(String s) {
        List<String> ans = new ArrayList<>();
        int i = 0;
        while (i < s.length()-1) {
            if (s.charAt(i) != '+')
                ++i;
            else if (s.charAt(i+1) != '+')
                i += 2;
            else {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(i, '-');
                sb.setCharAt(i+1, '-');
                ++i;
                ans.add(sb.toString());
            }
        }
        return ans;
    }
}
