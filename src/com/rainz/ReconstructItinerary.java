package com.rainz;

import java.util.*;

/*
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 */
public class ReconstructItinerary {
    public static void test(String args[]) {
        String[][] input = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        List<List<String>> l1 = Main.buildList2D(input);
        Main.printList(findItinerary(l1));
        String[][] input2 = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> l2 = Main.buildList2D(input2);
        Main.printList(findItinerary(l2));
        String[][] input3 = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        List<List<String>> l3 = Main.buildList2D(input3);
        Main.printList(findItinerary(l3));
    }

    private static boolean dfs(String from, Map<String, List<String>> graph, int tktCount, List<String> ans) {
        ans.add(from);
        if (ans.size() == tktCount + 1)
            return true;
        List<String> neighbors = graph.get(from);
        if (neighbors != null && !neighbors.isEmpty()) {
            for (int i = 0; i < neighbors.size(); ++i) {
                String n = neighbors.get(i);
                if (n == null)
                    continue;
                neighbors.set(i, null); // disables this because tickets can't be used again
                if (dfs(n, graph, tktCount, ans))
                    return true;
                neighbors.set(i, n); // restores this ticket
            }
        }
        ans.remove(ans.size()-1);
        return false;
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (x, y) -> x.get(1).compareTo(y.get(1))); // guarantees lexical order in graph
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> t: tickets) {
            List<String> neighbors = graph.get(t.get(0));
            if (neighbors == null) {
                neighbors = new ArrayList<>();
                graph.put(t.get(0), neighbors);
            }
            neighbors.add(t.get(1));
        }
        List<String> ans = new ArrayList<>();
        dfs("JFK", graph, tickets.size(), ans);
        return ans;
    }

}
