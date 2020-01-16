package com.rainz;

import java.util.*;

/*
 * There are n people, each person has a unique id between 0 and n-1. Given the arrays watchedVideos and friends, where watchedVideos[i] and friends[i] contain the list of watched videos and the list of friends respectively for the person with id = i.
 * Level 1 of videos are all watched videos by your friends, level 2 of videos are all watched videos by the friends of your friends and so on. In general, the level k of videos are all watched videos by people with the shortest path equal to k with you. Given your id and the level of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest.
 */

public class GetWatchedVideosbyYourFriends {
    public static void test(String args[]) {
        String[][] videos1 = {{"A","B"},{"C"},{"B","C"},{"D"}};
        List<List<String>> v1 = Main.buildList2D(videos1);
        int[][] friends1 = {{1,2},{0,3},{0,3},{1,2}};
        System.out.println(watchedVideosByFriends(v1, friends1,  0, 1));

        String[][] videos2 = {{"A","B"},{"C"},{"B","C"},{"D"}};
        List<List<String>> v2 = Main.buildList2D(videos2);
        int[][] friends2 = {{1,2},{0,3},{0,3},{1,2}};
        System.out.println(watchedVideosByFriends(v2, friends2,  0, 2));
    }

    private static List<Integer> getFriends(int[][] friends, int id, int level) {
        List<Integer> currLevel = new ArrayList<>();
        int N = friends.length;
        int[] levels = new int[N];
        Arrays.fill(levels, Integer.MAX_VALUE);
        currLevel.add(id);
        levels[id] = 0;
        int lvl = 0;
        while (lvl < level) {
            ++lvl;
            List<Integer> nextLevel = new ArrayList<>();
            for (int person: currLevel)  {
                int[] friendList = friends[person];
                for (int f: friendList) {
                    if (lvl < levels[f]) {
                        levels[f] = lvl;
                        nextLevel.add(f);
                    }
                }
            }
            currLevel = nextLevel;
        }
        currLevel.clear();
        for (int i = 0; i < N; ++i)
            if (levels[i] == level)
                currLevel.add(i);
        return currLevel;
    }

    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<Integer> friendsN = getFriends(friends, id, level);
        Map<String, Integer> videoFreqs = new HashMap<>();
        for (int f: friendsN) {
            for (String v: watchedVideos.get(f)) {
                videoFreqs.put(v, videoFreqs.getOrDefault(v, 0)+1);
            }
        }
        List<String> ans = new ArrayList<>();
        ans.addAll(videoFreqs.keySet());
        Collections.sort(ans,
                (x, y) -> videoFreqs.get(x) != videoFreqs.get(y) ?
                        Integer.compare(videoFreqs.get(x), videoFreqs.get(y)) :
                        x.compareTo(y)
                );
        return ans;
    }
}
