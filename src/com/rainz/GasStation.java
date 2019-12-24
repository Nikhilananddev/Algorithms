package com.rainz;

/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 */

public class GasStation {
    public static void test(String args[]) {
        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas1, cost1));
        int[] gas2 = {2,3,4};
        int[] cost2 = {3,4,3};
        System.out.println(canCompleteCircuit(gas2, cost2));
        int[] gas3 = {3,1,1};
        int[] cost3 = {1,2,2};
        System.out.println(canCompleteCircuit(gas3, cost3));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] diff = new int[gas.length];
        int[] sumDiff = new int[gas.length];
        int sum = 0;
        int lowestIdx = -1;
        for (int i = 0; i < gas.length; ++i) {
            diff[i] = gas[i] - cost[i];
            sum += diff[i];
            sumDiff[i] = sum;
            if (lowestIdx < 0 || sum < sumDiff[lowestIdx])
                lowestIdx = i;
        }
        if (lowestIdx < 0 || sumDiff[gas.length-1] < 0)
            return -1;
        // Always start from the one after the lowest accumulative gas point
        int start = (lowestIdx + 1) % gas.length;
        int carGas = 0;
        for (int i = 0; i < diff.length; ++i) {
            int idx = (start + i) % diff.length;
            carGas += diff[idx];
            if (carGas < 0)
                return -1;
        }
        return start;
    }
}
