package com.rainz;

import com.rainz.Main.QuadTree.Node;

/*
 * We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.
 * Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.
 */
public class ConstructQuadTree {
    public static void test(String args[]) {
        int[][] input1 = {
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
        };
        Node q1 = construct(input1);
        System.out.println(q1);
    }

    private static Node helper(int[][] grid, int startR, int startC, int len) {
        if (len == 1)
            return new Node(grid[startR][startC] == 1, true, null, null, null, null);
        len /= 2;
        Node tl = helper(grid, startR, startC, len);
        Node tr = helper(grid, startR, startC+len, len);
        Node bl = helper(grid, startR+len, startC, len);
        Node br = helper(grid, startR+len, startC+len, len);
        if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf &&
            tl.val == tr.val && tr.val == bl.val && bl.val == br.val)
            return new Node(tl.val, true, null, null, null, null);
        return new Node(false, false, tl, tr, bl, br);
    }

    public static Node construct(int[][] grid) {
        return helper(grid, 0, 0, grid.length);
    }
}
