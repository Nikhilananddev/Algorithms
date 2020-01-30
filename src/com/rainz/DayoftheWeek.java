package com.rainz;

public class DayoftheWeek {
    public static void test(String args[]) {
        System.out.println(dayOfTheWeek(31,8,2019));
        System.out.println(dayOfTheWeek(18,7,1999));
        System.out.println(dayOfTheWeek(15,8,1993));
    }

    private static boolean isLeapYear (int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static String dayOfTheWeek(int day, int month, int year) {
        int days = 0;
        // year
        days += 365 * (year - 1970);
        // account for leap years
        for (int leapyear = 1972; leapyear < year; leapyear += 4)
            if (isLeapYear (leapyear))
                days++;
        // month
        final int monthdays [] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        for (int monthiter = 1; monthiter < month; monthiter++)
            days += monthdays [monthiter];
        // day
        days += day;
        // if current year is a leap year and day is after feb 29th, add one more day.
        if (isLeapYear (year) && month > 2)
            days++;
        // translate.
        final String[] dayNames = {"Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday"};
        return dayNames [days % 7];
    }

}
