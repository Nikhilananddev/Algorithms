package com.rainz;

import java.util.List;

/*
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
 * We store logs in timestamp order that describe when a function is entered or exited.
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
 * A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 * Return the exclusive time of each function, sorted by their function id.
 */
public class ExclusiveTimeofFunctions {
    public static void test(String args[]) {
        List<String> input1 = List.of("0:start:0","1:start:2","1:end:5","0:end:6");
        Main.printArray(exclusiveTime(2, input1));
        List<String> input2 = List.of("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7");
        Main.printArray(exclusiveTime(1, input2));
    }
    public static int helper(List<String> logs, int id, int startTs, int startIdx, int[] endIdx, int[] ans) {
        int subTime = 0;
        int idx = startIdx;
        while (idx < logs.size()) {
            String log = logs.get(idx);
            String[] parts = log.split(":");
            int subId = Integer.parseInt(parts[0]);
            boolean bStart = parts[1].compareTo("start") == 0;
            int ts = Integer.parseInt(parts[2]);
            if (bStart) {
                int[] subEnd = {0};
                subTime += helper(logs, subId, ts, idx+1, subEnd, ans);
                idx = subEnd[0];
            } else {
                int res = ts - startTs + 1;
                endIdx[0] = idx;
                ans[id] += res - subTime;
                return res;
            }
            ++idx;
        }
        endIdx[0] = --idx;
        return 0;
    }

    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        int[] endIdx = new int[1];
        helper(logs, -1, 0, 0, endIdx, ans);
        return ans;
    }

}
