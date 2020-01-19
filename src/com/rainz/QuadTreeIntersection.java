package com.rainz;

import com.rainz.Main.QuadTree.Node;

/*
 * A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.
 * We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.
 */

public class QuadTreeIntersection {
    public static void test(String args[]) {
        int[][] input1 = {
                {1,1},
                {0,0},
        };
        Node q1 = Node.buildTreeFromGrid(input1);
        //System.out.println(q1);
        int[][] input2 = {
                {1,1,0,0},
                {1,1,1,1},
                {1,1,0,0},
                {1,1,0,0},
        };
        Node q2 = Node.buildTreeFromGrid(input2);
        //System.out.println(q2);
        System.out.println(intersect(q1, q2));
    }

    public static Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf)
            return quadTree1.val ? new Node(true, true, null, null, null, null) : quadTree2;
        if (quadTree2.isLeaf)
            return quadTree2.val ? new Node(true, true, null, null, null, null) : quadTree1;
        Node tl = intersect(quadTree1.topLeft, quadTree2.topLeft);
        Node tr = intersect(quadTree1.topRight, quadTree2.topRight);
        Node bl = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        Node br = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf &&
                tl.val == tr.val && tr.val == bl.val && bl.val == br.val)
            return new Node(tl.val, true, null, null, null, null);
        return new Node(false, false, tl, tr, bl, br);
    }
}
