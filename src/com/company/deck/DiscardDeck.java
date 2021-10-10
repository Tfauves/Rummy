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

    public List getDiscardedCards() {
        return discardedCards;
    }
}
