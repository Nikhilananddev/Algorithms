package com.rainz;

import java.util.*;

/*
 * Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.
 * Grammar can best be understood through simple examples:
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the 3 rules for our grammar:
 * For every lowercase letter x, we have R(x) = {x}
 * For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 */
public class BraceExpansionII {
    public static void test(String args[]) {
        System.out.println(braceExpansionII("{a}f"));
        System.out.println(braceExpansionII("{a,b}"));
        System.out.println(braceExpansionII("{a,b{c,d}}"));
        System.out.println(braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println(braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        System.out.println(braceExpansionII("{a,b}c{d,e}f"));
    }

    private static Set<String> crossWord(Set<String> st, String w) {
        Set<String> res = new HashSet<>();
        if (w.length() == 0)
            res.addAll(st);
        else if (st.isEmpty())
            res.add(w);
        else {
            for (String s : st)
                res.add(s + w);
        }
        return res;
    }

    private static Set<String> crossList(Set<String> st1, Set<String> st2) {
        Set<String> res = new HashSet<>();
        if (st1.isEmpty())
            res.addAll(st2);
        else if (st2.isEmpty())
            res.addAll(st1);
        else {
            for (String s : st1) {
                for (String t : st2) {
                    res.add(s + t);
                }
            }
        }
        return res;
    }

    private static int helper(String expression, int start, Set<String> res) {
        StringBuilder sb = new StringBuilder();
        int i = start;
        boolean done = false;
        Set<String> curr = new HashSet<>();
        int L = expression.length();
        while (!done && i <= L) {
            char c = i < L ? expression.charAt(i) : ',';
            if (Character.isLowerCase(c)) {
                sb.append(c);
                ++i;
                continue;
            }
            String s = sb.toString();
            sb = new StringBuilder();
            curr = crossWord(curr, s);
            switch (c) {
                case '{': {
                    Set<String> subRes = new HashSet<>();
                    i = helper(expression, i + 1, subRes);
                    curr = crossList(curr, subRes);
                    break;
                }
                case '}':
                    res.addAll(curr);
                    curr.clear();
                    done = true;
                    break;
                case ',':
                    res.addAll(curr);
                    curr.clear();
                    break;
                default:
                    break;
            }
            ++i;
        }
        res.addAll(curr);
        if (--i == L)
            --i;
        return i;
    }

    public static List<String> braceExpansionII(String expression) {
        List<String> ans = new ArrayList<>();
        Set<String> res = new HashSet<>();
        helper(expression, 0, res);
        ans.addAll(res);
        Collections.sort(ans);
        return ans;
    }
}
