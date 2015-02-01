package com.rainz;

/**
 * Created by Yu on 1/31/2015.
 */
public class SearchforaRange {
    public static void test(String args[]) {
        //int[] test = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 7};
        //int[] test = {1, 2, 2, 2, 4, 4, 4, 4, 4, 9, 9, 9, 10};
        int[] test = {1,2,3,3,3,3,4,5,9};
        int[] answer = searchRange(test, 3);
        System.out.println("" + answer[0] + ", " + answer[1]);
    }

    public static int[] searchRange(int[] A, int target) {
        int begin = 0, end = A.length - 1; // end is inclusive
        int[] answer = {-1, -1};
        // Handle special cases
        if (A.length == 0)
            return answer;
        if (A.length == 1) {
            if (A[0] == target) {
                answer[0] = answer[1] = 0;
            }
            return answer;
        }

        // Binary search for 'target'
        int found = -1;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (A[mid] == target) {
                found = mid;
                break;
            }
            if (begin == end - 1) {
                // Two elements left. In this case begin == mid, already tested
                if (A[end] == target) {
                    found = end;
                    break;
                }
                // Neither begin nor end matches target. Search failed.
                return answer;
            }
            if (A[mid] < target)
                begin = mid;
            else
                end = mid;
        }
        System.out.println("Found target at " + found);

        // Search left for first appearance between begin and mid
        int left = found;
        while (begin <= left) {
            if (begin == left) {
                answer[0] = begin;
                break;
            }
            if (begin == left - 1) {
                answer[0] = (A[begin] == target ? begin : left);
                break;
            }
            int mid = (begin + left) / 2;
            if (A[mid] == target)
                left = mid;
            else
                begin = mid;
        }
        System.out.println("Found left at " + answer[0]);

        int right = found;
        while (right <= end) {
            if (right == end) {
                answer[1] = end;
                break;
            }
            if (right == end - 1) {
                answer[1] = (A[end] == target ? end : right);
                break;
            }
            int mid = (right + end) / 2;
            if (A[mid] == target)
                right = mid;
            else
                end = mid;
        }
        System.out.println("Found right at " + answer[1]);

        return answer;
    }
}
