package com.company.deck;

public class Card implements Deck{
    private final String SUIT;
    private final int RANK;

    public Card(String SUIT, int RANK) {
        this.SUIT = SUIT;
        this.RANK = RANK;
    }



    public void shuffle() {}
}
