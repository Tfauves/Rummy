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

    public String display() {
        String outPut = "";
//        if (isFaceDown) {
//            return "<*>";
//        }
        switch (getRANK()) {
            case 1 -> outPut = "ACE";
            case 11 -> outPut = "JACK";
            case 12 -> outPut = "QUEEN";
            case 13 -> outPut = "KING";
            default -> outPut = getRANK() == 10 ? Integer.toString(getRANK()) : " " + getRANK();
        };
        return outPut;
    }

    @Override
    public Card draw() {
        return null;
    }

    public void shuffle() {}
}
