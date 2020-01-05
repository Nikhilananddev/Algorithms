package com.rainz;

import com.rainz.Main.Graph.Node;

import java.util.*;

/*
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 */

public class CloneGraph {
    public static void test(String args[]) {
        cloneGraph(null);
    }

    /* Clone each node and its neighbors, but leave neighbors' neighbors empty */
    public static Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Map<Node, Node> clones = new HashMap<>();
        Queue<Node> workQ = new LinkedList<>();
        workQ.add(node);
        Node ans = null;
        while (!workQ.isEmpty()) {
            Node n = workQ.remove();
            Node clone = clones.get(n);
            if (clone != null && !clone.neighbors.isEmpty())
                continue;
            // Cloning node
            if (clone == null) {
                clone = new Node(n.val, new ArrayList<>());
                clones.put(n, clone);
                if (ans == null)
                    ans = clone;
            }
            for (Node nb: n.neighbors) {
                Node nbClone = clones.get(nb);
                if (nbClone == null) {
                    nbClone = new Node(nb.val, new ArrayList<>());
                    clones.put(nb, nbClone);
                }
                clone.neighbors.add(nbClone);
            }
            workQ.addAll(n.neighbors);
        }
        return ans;
    }
}
