package com.rainz;

public class MinimumCostForTickets {
    public static void test(String args[]) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println(minimumCostForTickets(days, costs));
        int[] days2 = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs2 = {2,7,15};
        System.out.println(minimumCostForTickets(days2, costs2));
        int[] days3 = {1,4,6,7,8,20};
        int[] costs3 = {7,2,15};
        System.out.println(minimumCostForTickets(days3, costs3));
    }

    private static int helper(int[] days, int[] costs, int startIdx, int endIdx /* inclusive */) {
        if (startIdx > endIdx)
            return 0;

        // Cost for buying ticket for day 1
        int idx1 = startIdx + 1; // inclusive
        int cost = costs[0] + helper(days, costs, idx1, endIdx);

        // Cost for buy ticket for day 1-7
        int startDay = days[startIdx];
        int idx7 = idx1;
        while (idx7 <= endIdx && days[idx7] - startDay + 1 <= 7)
            ++idx7;
        if (costs[1] < (days[idx7-1] - startDay + 1)*costs[0]) {
            int cost7 = costs[1] + helper(days, costs, idx7, endIdx);
            if (cost7 < cost)
                cost = cost7;
        }

        // Cost of buy ticket for day 1-30
        int idx30 = idx7;
        while (idx30 <= endIdx && days[idx30] - startDay + 1 <= 30)
            ++idx30;
        if (costs[2] < (days[idx30-1] - startDay + 1)*costs[0]) {
            int cost30 = costs[2] + helper(days, costs, idx30, endIdx);
            if (cost30 < cost)
                cost = cost30;
        }
        return cost;
    }

    public static int minimumCostForTicketsRecursive(int[] days, int[] costs) {
        return helper(days, costs, 0, days.length-1);
    }

    public static int minimumCostForTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        dp[0] = 0;
        int dayIdx = 0;
        int lastDay = days[days.length-1];
        for (int day = 1; day <= days[days.length-1]; ++day) {
            if (day < days[dayIdx]) {
                dp[day] = dp[day - 1];
                continue;
            }
            ++dayIdx;
            // Single-day ticket
            int cost = dp[day-1] + costs[0];
            // 7-day ticket
            int day7 = day - 7;
            if (day7 < 0)
                day7 = 0;
            int cost2 = dp[day7] + costs[1];
            if (cost2 < cost)
                cost = cost2;
            // 30-day ticket
            int day30 = day - 30;
            if (day30 < 0)
                day30 = 0;
            cost2 = dp[day30] + costs[2];
            if (cost2 < cost)
                cost = cost2;
            dp[day] = cost;
        }
        return dp[lastDay];
    }
}
