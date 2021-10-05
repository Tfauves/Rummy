package com.company.deck;

public class Card implements Deck{
    private final String suit;
    private final int rank;
    private boolean isFaceDown = true;

    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRANK() {
        return rank;
    }

    public String toString() {
        return rank + " " + suit;
    }

    public String display() {
        String outPut = "";
        if (isFaceDown) {
            return "<*>";
        }
        switch (getRANK()) {
            case 1 -> outPut = "ACE";
            case 11 -> outPut = "JACK";
            case 12 -> outPut = "QUEEN";
            case 13 -> outPut = "KING";
            default -> outPut = getRANK() == 10 ? Integer.toString(getRANK()) : " " + getRANK();
        };
        return outPut + " " + suit;
    }

    @Override
    public Card draw() {
        return null;
    }

    public void shuffle() {}

    public void flip() {
        isFaceDown = !isFaceDown;
    }

    public boolean getIsFaceDown() {return isFaceDown;}
}
