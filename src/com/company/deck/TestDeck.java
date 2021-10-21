package com.company.deck;


import com.company.util.Console;

public class TestDeck implements Deck {

    public final String[] SUITS = {"♦", "♠", "♣", "♥"
    };
    public final int [] FACE_VALUE = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
    };

    public void shuffle() {}

    @Override
    public Card draw() {
        int faceValue = Console.getInt("enter number 1-13", 1, 13, "invalid entry");
        int suit = Console.getInt("1. \u2664 | 2. \u2665 | 3. \u2666 | 4. \u2667", 1, 4, "Invalid entry");
        return new Card(SUITS[suit - 1], faceValue) {
            @Override
            public String display() {
                return null;
            }
        };
    }

    public Card flipDraw() {
        Card card = draw();
        card.flip();
        return card;
    }
}
