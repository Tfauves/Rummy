package com.company.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscardDeck implements Deck {
    private List<Card> discardedCards = new ArrayList<>();

    @Override
    public Card draw() {
        return discardedCards.remove(0);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(discardedCards);

    }

    @Override
    public void replenishDeck(List<Card> discardCards) {

    }

    public List getDiscardedCards() {
        return discardedCards;
    }
}
// TODO: 10/9/2021 make discard deck like the dealer in black jack??? treat it like another hand?????