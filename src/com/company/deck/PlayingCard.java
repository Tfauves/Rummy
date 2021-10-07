package com.company.deck;

public class PlayingCard extends Card {

    public PlayingCard(String suit, int rank) {
        super(suit, rank);

    }

    @Override
    public String display() {
        String outPut;
//        if (isFaceDown) {
//            return "<*>";
//        }
        switch (getRank()) {
            case 1 -> outPut = "ACE";
            case 11 -> outPut = "JACK";
            case 12 -> outPut = "QUEEN";
            case 13 -> outPut = "KING";
            default -> outPut = getRank() == 10 ? Integer.toString(getRank()) : " " + getRank();
        };
        return outPut + " " + getSuit();
    }
}
