package com.company.rummy;

import com.company.deck.PlayingCard;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> cards = new ArrayList<>();
    private Actor player;

    public Hand(Actor player) {
        this.player = player;
    }

    public void addCard(PlayingCard card) {
        cards.add(card);
    }



}
