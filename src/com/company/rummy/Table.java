package com.company.rummy;


import com.company.deck.Deck;
import com.company.deck.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Hand> hands = new ArrayList<>();
    private Deck deck = new StandardDeck();

    private void deal() {
        for (int count = 0; count < 10; count++) {
            for (Hand player : hands) {
                player.addCard(deck.draw());
            }
        }
    }
}
