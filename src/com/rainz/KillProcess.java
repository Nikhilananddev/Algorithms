package com.rainz;

import java.util.*;

/*
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 */
public class KillProcess {
    public static void test(String args[]) {
        int[] pid =  {1, 3, 10, 5};
        int[] ppid = {3, 0, 5, 3};
        System.out.println(killProcess(Main.buildList(pid), Main.buildList(ppid), 5));
    }

    private static int findRootPathCompression(int p, Map<Integer, Integer> pTable, int kill) {
        Stack<Integer> stk = new Stack<>();
        while (p != 0 && p != kill) {
            stk.push(p);
            p = pTable.get(p);
        }
        // Now p contains root value
        pTable.put(p, p);
        while (!stk.isEmpty()) {
            pTable.put(stk.pop(), p);
        }
        return p;
    }

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> pTable = new HashMap<>();
        for (int i = 0; i < pid.size(); ++i)
            pTable.put(pid.get(i), ppid.get(i));
        for (int p: pid) {
            int root = findRootPathCompression(p, pTable, kill);
            if (root == kill)
                ans.add(p);
        }
        return ans;
    }
}
