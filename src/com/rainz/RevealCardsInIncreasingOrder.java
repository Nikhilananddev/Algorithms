package com.rainz;

import java.util.*;

public class RevealCardsInIncreasingOrder {
    public static void test(String args[]) {
        int[] input = {17,13,11,2,3,5,7};
        System.out.println(Arrays.toString(revealCardsInIncreasingOrder(input)));
    }

    public static int[] revealCardsInIncreasingOrder(int[] deck) {
        Arrays.sort(deck);

        Queue<Integer> cards = new LinkedList<>();
        for (int i = 0; i < deck.length; ++i)
            cards.add(i);
        List<Integer> outCards = new ArrayList<>(deck.length);

        while (!cards.isEmpty()) {
            outCards.add(cards.remove());
            if (!cards.isEmpty())
                cards.add(cards.remove());
        }

        int[] result = new int[deck.length];
        for (int i = 0; i < deck.length; ++i) {
            result[outCards.get(i)] = deck[i];
        }

        return result;
    }
}
