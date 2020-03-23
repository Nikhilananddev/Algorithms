package com.rainz;

import java.util.Deque;
import java.util.LinkedList;

/*
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 */
public class DesignSnakeGame {
    public static void test(String args[]) {
        int[][] input1 = {{1,2},{0,1}};
        DesignSnakeGame game = new DesignSnakeGame(3, 2, input1);
        System.out.println(game.move("R"));
        System.out.println(game.move("D"));
        System.out.println(game.move("R"));
        System.out.println(game.move("U"));
        System.out.println(game.move("L"));
        System.out.println(game.move("U"));
    }

    private int W;
    private int H;
    private int[][] _food;
    private int _foodIdx = 0;
    private Deque<int[]> snake = new LinkedList<>();

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public DesignSnakeGame(int width, int height, int[][] food) {
        W = width;
        H = height;
        _food = food;
        int[] start = {0, 0};
        snake.offer(start);
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] next = {snake.peekLast()[0], snake.peekLast()[1]};
        switch (direction.charAt(0)) {
            case 'U':
                --next[0];
                if (next[0] < 0)
                    return -1;
                break;
            case 'D':
                ++next[0];
                if (next[0] >= H)
                    return -1;
                break;
            case 'L':
                --next[1];
                if (next[1] < 0)
                    return -1;
                break;
            case 'R':
                ++next[1];
                if (next[1] >= W)
                    return -1;
                break;
        }
        if (_foodIdx < _food.length && next[0] == _food[_foodIdx][0] && next[1] == _food[_foodIdx][1]) {
            ++_foodIdx;
        } else {
            snake.pollFirst();
            for (int[] p: snake) {
                if (next[0] == p[0] && next[1] == p[1])
                    return -1;
            }
        }
        snake.offerLast(next);
        return _foodIdx;
    }
}
