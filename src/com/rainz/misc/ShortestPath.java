package com.rainz.misc;
import java.util.*;

/*
 * (From Goldman Sachs)
 * Find shortest path between stations
 * Visual representation of the Train map used
   King's Cross St Pancras --- Angel ---- Old Street
   |                   \                            |
   |                    \                            |
   |                     \                            |
   Russell Square         Farringdon --- Barbican --- Moorgate
   |                                                  /
   |                                                 /
   |                                                /
   Holborn --- Chancery Lane --- St Paul's --- Bank
 */
public class ShortestPath {
    public static void test(String args[]) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
    /**
     * class Station
     * <p>
     * Respresents Station in the rail network. Each station is identified by
     * unique name. Station is connected with other stations - this information
     * is stored in the 'neighbours' field. Two station objects with the same name are
     * equal therefore they are considered to be same station.
     */
    private static class Station {
        private String name;
        private List<Station> neighbours;

        public Station(String name) {
            this.name = name;
            this.neighbours = new ArrayList<>(3);
        }

        String getName() {
            return name;
        }

        void addNeighbour(Station v) {
            this.neighbours.add(v);
        }

        List<Station> getNeighbours() {
            return this.neighbours;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Station && this.name.equals(((Station) obj).getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.name);
        }
    }

    /**
     * class TrainMap
     * <p>
     * Respresents whole rail network - consists of number of the Station objects.
     * Stations in the map are bi-directionally connected. Distance between any 2 stations
     * is of same constant distance unit. This implies that shortest distance between any
     * 2 stations depends only on number of stations in between
     */
    private static class TrainMap {

        private HashMap<String, Station> stations;

        public TrainMap() {
            this.stations = new HashMap<>();
        }

        public TrainMap addStation(String name) {
            Station s = new Station(name);
            this.stations.putIfAbsent(name, s);
            return this;
        }

        public Station getStation(String name) {
            return this.stations.get(name);
        }

        public TrainMap connectStations(Station fromStation, Station toStation) {
            if (fromStation == null) {
                throw new IllegalArgumentException("From station is null");
            }
            if (toStation == null) {
                throw new IllegalArgumentException("From station is null");
            }
            fromStation.addNeighbour(toStation);
            toStation.addNeighbour(fromStation);
            return this;
        }

        private void dfs(Station src, Station dst, List<Station> path, Set<Station> visited, List<Station> ans) {
            if (visited.contains(src))
                return;
            path.add(src);
            if (src == dst) {
//                for (Station s: path)
//                    System.out.print(s.getName() + " ");
//                System.out.println();
                if (ans.size() == 0 || path.size() < ans.size()) {
                    // Found shortest path so far
                    ans.clear();
                    ans.addAll(path);
                }
            } else if (ans.size() == 0 || path.size() < ans.size()) {
                // Continue searching only if path is shorter than shorest so far
                visited.add(src);
                List<Station> nbs = src.getNeighbours();
                for (Station nb : nbs) {
                    dfs(nb, dst, path, visited, ans);
                }
                visited.remove(src);
            }
            path.remove(path.size()-1);
        }

        class BFSNode {
            Station station;
            BFSNode parent;
            BFSNode (Station s, BFSNode p) { station = s; parent = p; }
        }
        public List<Station> shortestPath(String from, String to) {
            Station src = getStation(from), dst = getStation(to);
            List<Station> ans = new ArrayList<>();
            // DFS solution
//            List<Station> path = new ArrayList<>();
//            Set<Station> visited = new HashSet<>();
//            dfs(src, dst, path, visited, ans);

            // BFS solution
            Set<Station> visited = new HashSet<>();
            List<BFSNode> currLevel = new ArrayList<>();
            currLevel.add(new BFSNode(src, null));
            while (!currLevel.isEmpty()) {
                List<BFSNode> nextLevel = new ArrayList<>();
                for (BFSNode n: currLevel) {
                    if (n.station == dst) {
                        for (BFSNode node = n; node != null; node = node.parent)
                            ans.add(node.station);
                        Collections.reverse(ans);
                        return ans;
                    }
                    if (visited.contains(n.station))
                        continue;
                    visited.add(n.station);
                    for (Station nb: n.station.getNeighbours()) {
                        BFSNode child = new BFSNode(nb, n);
                        nextLevel.add(child);
                    }
                }
                currLevel = nextLevel;
            }
            return ans;
        }

        public static String convertPathToStringRepresentation(List<Station> path) {
            if (path.isEmpty()) {
                return "";
            }
            return path.stream().map(Station::getName).reduce((s1, s2) -> s1 + "->" + s2).get();
        }
    }


    public static boolean doTestsPass() {
        // todo: implement more tests, please
        // feel free to make testing more elegant
        TrainMap trainMap = new TrainMap();

        trainMap.addStation("King's Cross St Pancras").addStation("Angel").addStation("Old Street").addStation("Moorgate")
                .addStation("Farringdon").addStation("Barbican").addStation("Russel Square").addStation("Holborn")
                .addStation("Chancery Lane").addStation("St Paul's").addStation("Bank");

        trainMap.connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Angel"))
                .connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Farringdon"))
                .connectStations(trainMap.getStation("King's Cross St Pancras"), trainMap.getStation("Russel Square"))
                .connectStations(trainMap.getStation("Russel Square"), trainMap.getStation("Holborn"))
                .connectStations(trainMap.getStation("Holborn"), trainMap.getStation("Chancery Lane"))
                .connectStations(trainMap.getStation("Chancery Lane"), trainMap.getStation("St Paul's"))
                .connectStations(trainMap.getStation("St Paul's"), trainMap.getStation("Bank"))
                .connectStations(trainMap.getStation("Angel"), trainMap.getStation("Old Street"))
                .connectStations(trainMap.getStation("Old Street"), trainMap.getStation("Moorgate"))
                .connectStations(trainMap.getStation("Moorgate"), trainMap.getStation("Bank"))
                .connectStations(trainMap.getStation("Farringdon"), trainMap.getStation("Barbican"))
                .connectStations(trainMap.getStation("Barbican"), trainMap.getStation("Moorgate"));

        String solution = "King's Cross St Pancras->Russel Square->Holborn->Chancery Lane->St Paul's";

        String mySolution = TrainMap.convertPathToStringRepresentation(trainMap.shortestPath("King's Cross St Pancras", "St Paul's"));
        System.out.println(mySolution);
        return solution.equals(mySolution);
    }

}