package com.rainz;

import java.util.ArrayList;
import java.util.List;

/*
 * Return the result of evaluating a given boolean expression, represented as a string.
 * An expression can either be:
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 */
public class ParsingABooleanExpression {
    public static void test(String args[]) {
        System.out.println(parseBoolExpr("!(f)"));
        System.out.println(parseBoolExpr("|(f,t)"));
        System.out.println(parseBoolExpr("&(t,f)"));
        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    private static boolean eval(char op, List<Boolean> vals) {
        boolean result;
        switch (op) {
            case '!':
                result = !vals.get(0);
                break;
            case '&':
                result = true;
                for (boolean b : vals) {
                    if (!b) {
                        result = false;
                        break;
                    }
                }
                break;
            case '|':
                result = false;
                for (boolean b : vals) {
                    if (b) {
                        result = true;
                        break;
                    }
                }
                break;
            default:
                result = vals.get(0);
                break;
        }
        return result;
    }

    private static int helper(String expr, int start, List<Boolean> retVals) {
        int idx = start;
        boolean endOfExpr = false;
        char op = '\0';
        while (!endOfExpr && idx < expr.length()) {
            char c = expr.charAt(idx);
            switch (c) {
                case '!':
                case '&':
                case '|':
                    op = c;
                    break;
                case 't':
                    retVals.add(true);
                    break;
                case 'f':
                    retVals.add(false);
                    break;
                case '(':
                    List<Boolean> vals = new ArrayList<>();
                    idx = helper(expr, idx+1, vals);
                    retVals.add(eval(op, vals));
                    op = '\0';
                    break;
                case ')':
                    endOfExpr = true;
                    break;
                case ',':
                default:
                    break;
            }
            ++idx;
        }
        return idx-1;
    }

    public static boolean parseBoolExpr(String expression) {
        List<Boolean> vals = new ArrayList<>();
        helper(expression, 0, vals);
        return vals.get(0);
    }
}
