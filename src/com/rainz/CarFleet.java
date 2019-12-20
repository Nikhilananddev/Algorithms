package com.rainz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 * The distance between these two cars is ignored - they are assumed to have the same position.
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 * How many car fleets will arrive at the destination?
 */
public class CarFleet {
    public static void test(String args[]) {
        int[] positions1 = {10,8,0,5,3};
        int[] speeds1 = {2,4,1,1,3};
        System.out.println(carFleet(12, positions1, speeds1));
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        List<int[]> cars = new ArrayList<>();
        for (int i = 0; i < position.length; ++i) {
            int[] car = {position[i], speed[i]};
            cars.add(car);
        }
        // Sort by position, furthest first
        Collections.sort(cars, (x, y) -> y[0] - x[0]);

        List<int[]> fleets = new ArrayList<>();
        for (int[] car: cars) {
            if (fleets.isEmpty()) {
                fleets.add(car);
                continue;
            }
            int[] prevFleet = fleets.get(fleets.size()-1);
            int speedDiff = car[1] - prevFleet[1];
            if (speedDiff > 0) {
                double distDiff = prevFleet[0] - car[0];
                double prev2Target = target - prevFleet[0];
                if (distDiff/speedDiff <= prev2Target/prevFleet[1]) {
                    // This car will catch up the previous fleet
                    continue;
                }
            }
            // Can't catch up. This will be a new fleet
            fleets.add(car);
        }
        return fleets.size();
    }

}
