package com.company.deck;

import java.util.List;

public interface Deck {
    Card draw();
    void shuffle();
    void replenishDeck(List<Card> discardCards);
}
