package com.rainz;

import java.util.HashMap;
import java.util.Map;

/*
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter {
    public static void test(String args[]) {
        LoggerRateLimiter logger = new LoggerRateLimiter();

        System.out.println(logger.shouldPrintMessage(1, "foo")); // returns true;
        System.out.println(logger.shouldPrintMessage(2,"bar")); // returns true;
        System.out.println(logger.shouldPrintMessage(3,"foo")); // returns false;
        System.out.println(logger.shouldPrintMessage(8,"bar")); // returns false;
        System.out.println(logger.shouldPrintMessage(10,"foo")); // returns false;
        System.out.println(logger.shouldPrintMessage(11,"foo")); // returns true;
    }

    private Map<String, Integer> tsTable = new HashMap<>();
    public LoggerRateLimiter() {

    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int oldTs = tsTable.getOrDefault(message, -9999);
        boolean ans = timestamp - oldTs >= 10;
        if (ans)
            tsTable.put(message, timestamp);
        return ans;
    }
}
