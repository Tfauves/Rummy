package com.company.deck;

public class PlayingCard extends Card {

    public PlayingCard(String suit, int rank) {
        super(suit, rank);

    }

    public String toString() {
        return getRank() + " " + getSuit();
    }

    @Override
    public String display() {
        String outPut;
        switch (getRank()) {
            case 1 -> outPut = "AC";
            case 11 -> outPut = "JK";
            case 12 -> outPut = "QU";
            case 13 -> outPut = "KI";
            default -> outPut = getRank() == 10 ? Integer.toString(getRank()) : " " + getRank();
        };
        return outPut + " " + getSuit();
    }

    public int getRank() {
        return super.getRank();
    }
}
