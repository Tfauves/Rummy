package com.company.rummy;

import com.company.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Actor player;

    public Hand(Actor player) {
        this.player = player;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String displayHand() {
        StringBuilder outPut = new StringBuilder();
        for (Card card : cards) {
            outPut.append(card.display()).append(" ");
        }
        return outPut.toString().trim();
    }

    public String getName() {
        return player.getName();
    }






}
