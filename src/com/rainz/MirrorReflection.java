package com.rainz;

public class MirrorReflection {
    public static void test(String args[]) {
        System.out.println(mirrorReflection(2, 1));
    }

    public static int mirrorReflection(int p, int q) {
        boolean toRight = true;
        boolean up = true;
        int[] ray = {0, q};

        while (ray[1] != p && (ray[1] != 0 || !toRight)) {
            toRight = !toRight;
            int dest = ray[0] + 2 * (ray[1] - ray[0]);
            int src = ray[1];
            if (up) {
                if (dest > p) {
                    up = !up;
                    dest = p - (dest - p);
                    src = p + (p - ray[1]);
                }
                ray[0] = src;
                ray[1] = dest;
            } else {
                if (dest < 0) {
                    up = !up;
                    dest = -dest;
                    src = -ray[1];
                }
                ray[0] = src;
                ray[1] = dest;
            }
        }
        if (ray[1] == 0)
            return 0;
        return toRight ? 1 : 2;
    }
}
