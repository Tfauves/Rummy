package com.company.deck;

public abstract class Card {
    private final String suit;
    private final int rank;
    private boolean isFaceDown = true;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public String toString() {
        return rank + " " + suit;
    }

    public abstract String display();

    public void flip() {
        isFaceDown = !isFaceDown;
    }

    public boolean getIsFaceDown() {return isFaceDown;}

    public String getSuit() {
        return suit;
    }
}
