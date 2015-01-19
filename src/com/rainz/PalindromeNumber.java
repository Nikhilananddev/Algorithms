package com.rainz;

/**
 * Created by Yu on 1/18/2015.
 */
public class PalindromeNumber {
    public static void test(String args[]) {
        System.out.println(isPalindrome(0));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1210));
        System.out.println(isPalindrome(1231));
        System.out.println(isPalindrome(9999));
        System.out.println(isPalindrome(9009));
        System.out.println(isPalindrome(90109));
        System.out.println(isPalindrome(10001));
        System.out.println(isPalindrome(1001));
        System.out.println(isPalindrome(99999));
        System.out.println(isPalindrome(99989));
        System.out.println(isPalindrome(98999));
        System.out.println(isPalindrome(-2147447412));
        System.out.println(isPalindrome(Integer.MAX_VALUE));
        System.out.println(isPalindrome(Integer.MIN_VALUE));
        System.out.println(isPalindrome(1000021));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        long xx = (long) x;
        long power10 = 10;
        while (power10 <= xx)
            power10 *= 10;
        power10 /= 10;
        // Now power10 and xx have same # digits. Example: xx=456 ==> power10=100

        while (power10 >= 10) {
            long lastDigit = xx % 10;
            // remove last digit & first digit
            xx -= lastDigit + lastDigit * power10;
            if (xx < 0 || xx >= power10)
                return false;
            xx /= 10;
            power10 /= 100;
        }
        return true;
    }
}
