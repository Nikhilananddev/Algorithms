package com.rainz;

import java.util.*;

/*
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 */
public class EvaluateDivision {
    public static void test(String args[]) {
        String[][] equations = { {"a", "b"}, {"b", "c"} };
        List<List<String>> llEq = Main.buildList2D(equations);
        double[] values = {2.0, 3.0};
        String[][] queries = { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"} };
        List<List<String>> llQueries = Main.buildList2D(queries);
        Main.printArray(calcEquation(llEq, values, llQueries));

        String[][] equations2 = {{"a","e"},{"b","e"}};
        List<List<String>> llEq2 = Main.buildList2D(equations2);
        double[] values2 = {4.0,3.0};
        String[][] queries2 = {{"a","b"},{"e","e"},{"x","x"}};
        List<List<String>> llQueries2 = Main.buildList2D(queries2);
        Main.printArray(calcEquation(llEq2, values2, llQueries2));
    }

    private static class NeighborInfo {
        public String name;
        public double multiple;
        NeighborInfo(String n, double m) {
            name = n;
            multiple = m;
        }
    }

    private static double dfs(Map<String, List<NeighborInfo>> graph, Set<String> visited, String node, String dst, double multiple) {
        if (node.equals(dst))
            return multiple;
        List<NeighborInfo> nbs = graph.get(node);
        visited.add(node);
        for (NeighborInfo nb: nbs) {
            if (visited.contains(nb.name))
                continue;
            double result = dfs(graph, visited, nb.name, dst, multiple*nb.multiple);
            if (result >= 0)
                return result;
        }
        visited.remove(node);
        return -1;
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Build graph
        Map<String, List<NeighborInfo>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); ++i) {
            List<String> eq = equations.get(i);
            String aNode = eq.get(0);
            String bNode = eq.get(1);
            // Add a->b
            List<NeighborInfo> nbs = graph.get(aNode);
            if (nbs == null) {
                nbs = new ArrayList<>();
                graph.put(aNode, nbs);
            }
            NeighborInfo nb = new NeighborInfo(bNode, values[i]);
            nbs.add(nb);
            // Add b->a
            nbs = graph.get(bNode);
            if (nbs == null) {
                nbs = new ArrayList<>();
                graph.put(bNode, nbs);
            }
            nb = new NeighborInfo(aNode, 1/values[i]);
            nbs.add(nb);
        }

        double[] ans = new double[queries.size()];
        int idx = 0;
        for (List<String> q: queries) {
            String a = q.get(0), b = q.get(1);
            if (graph.get(a) == null || graph.get(b) == null)
                ans[idx++] = -1;
            else {
                Set<String> visited = new HashSet<>();
                ans[idx++] = dfs(graph, visited, a, b, 1);
            }
        }
        return ans;
    }


}
