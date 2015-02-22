package com.rainz;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Yu on 2/22/2015.
 */
public class SimplifyPath {
    public static void test(String args[]) {
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    public static String simplifyPath(String path) {
        Deque<String> dirStk = new LinkedList<String>();
        String[] dirs = path.split("/");
        for (String d: dirs) {
            if (d.equals("") || d.equals("."))
                continue;
            if (d.equals("..")) {
                if (!dirStk.isEmpty())
                    dirStk.removeLast();
            }
            else
                dirStk.addLast(d);
        }
        if (dirStk.isEmpty())
            return "/";
        StringBuilder bldr = new StringBuilder();
        for (String s: dirStk) {
            bldr.append("/");
            bldr.append(s);
        }
        return bldr.toString();
    }
}
