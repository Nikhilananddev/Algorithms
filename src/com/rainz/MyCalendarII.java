package com.rainz;

import java.util.Map;
import java.util.TreeMap;

/*
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 */

public class MyCalendarII {
    public static void test(String args[]) {
        MyCalendarII cal = new MyCalendarII();
        System.out.println(cal.book(10, 20)); // returns true
        System.out.println(cal.book(50, 60)); // returns true
        System.out.println(cal.book(10, 40)); // returns true
        System.out.println(cal.book(5, 15)); // returns false
        System.out.println(cal.book(5, 10)); // returns true
        System.out.println(cal.book(25, 55)); // returns true
    }

    private Map<Integer, Integer> bookings = new TreeMap<>();

    public MyCalendarII() {

    }

    public boolean book(int start, int end) {
        bookings.put(start, bookings.getOrDefault(start, 0) + 1);
        bookings.put(end, bookings.getOrDefault(end, 0) - 1);
        int count = 0;
        for (Map.Entry<Integer, Integer> entry: bookings.entrySet()) {
            count += entry.getValue();
            if (count >= 3) {
                bookings.put(start, bookings.get(start) - 1);
                bookings.put(end, bookings.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
