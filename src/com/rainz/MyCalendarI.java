package com.rainz;

/*
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 */

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarI {
    public static void test(String args[]) {
        MyCalendarI cal = new MyCalendarI();
        System.out.println(cal.book(10, 20)); // returns true
        System.out.println(cal.book(15, 25)); // returns false
        System.out.println(cal.book(20, 30)); // returns true
    }

    private TreeMap<Integer, Integer> bookings = new TreeMap<>();
    public MyCalendarI() {

    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> prevEntry = bookings.floorEntry(start);
        if (prevEntry != null && prevEntry.getValue() > start)
            return false;
        Map.Entry<Integer, Integer> nextEntry = bookings.ceilingEntry(start);
        if (nextEntry != null && nextEntry.getKey() < end)
            return false;
        bookings.put(start, end);
        return true;
    }

}
