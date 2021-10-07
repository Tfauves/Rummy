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

    public String display() {
        String outPut;
//        if (isFaceDown) {
//            return "<*>";
//        }
        switch (rank) {
            case 1 -> outPut = "ACE";
            case 11 -> outPut = "JACK";
            case 12 -> outPut = "QUEEN";
            case 13 -> outPut = "KING";
            default -> outPut = getRank() == 10 ? Integer.toString(getRank()) : " " + getRank();
        };
        return outPut + " " + suit;
    }

    public void flip() {
        isFaceDown = !isFaceDown;
    }

    public boolean getIsFaceDown() {return isFaceDown;}
}
