package com.rainz;

/*
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
 * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 */
public class LonelyPixelI {
    public static void test(String args[]) {
        char[][] input1 = {{'W', 'W', 'B'},
                           {'W', 'B', 'W'},
                           {'B', 'W', 'W'}};
        System.out.println(findLonelyPixel(input1));

    }

    public static int findLonelyPixel(char[][] picture) {
        int R = picture.length;
        if (R == 0)
            return 0;
        int C = picture[0].length;
        int[] rowB = new int[R];
        int[] colB = new int[C];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (picture[r][c] == 'B') {
                    ++rowB[r];
                    ++colB[c];
                }
            }
        }
        int ans = 0;
        for (int r = 0; r < R; ++r) {
            if (rowB[r] != 1)
                continue;
            for (int c = 0; c < C; ++c) {
                if (colB[c] == 1 && picture[r][c] == 'B')
                    ++ans;
            }
        }
        return ans;
    }

}
