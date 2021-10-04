package com.company.deck;

import java.util.ArrayList;
import java.util.List;

public class StandardDeck {
    private final List<Card> cards = new ArrayList<>();
    public final String[] SUITS = {"♦", "♠", "♣", "♥"
    };
    public final int[] VALUE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
    };

    public StandardDeck() {
        for (String suit : SUITS) {
            for (int value : VALUE) {
                cards.add(new Card(suit, value));
            }

        }
    }


}
