package com.company.deck;

public class Card implements Deck{
    private final String SUIT;
    private final int RANK;

    public Card(String SUIT, int RANK) {
        this.SUIT = SUIT;
        this.RANK = RANK;
    }

    public int getRANK() {
        return RANK;
    }

    public String toString() {
        return RANK + " " + SUIT;
    }

    @Override
    public Card draw() {
        return null;
    }

    public void shuffle() {}
}
