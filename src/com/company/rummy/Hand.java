package com.company.rummy;

import com.company.deck.Card;
import com.company.util.Console;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Actor holder;

    public Hand(Actor player) {
        this.holder = player;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard() {

    }

    public void discard() {
        int faceValue = Console.getInt("to discard a card enter card rank 1-13", 1, 13, "invalid entry");
        int suit = Console.getInt("1. \u2664 | 2. \u2665 | 3. \u2666 | 4. \u2667", 1, 4, "Invalid entry");
        int cardIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(cards.get(i) + " " + cardIndex);
        }
    }

    public String displayHand() {
        StringBuilder outPut = new StringBuilder();
        for (Card card : cards) {
            outPut.append(card.display()).append(" ");
        }
        return outPut.toString().trim();
    }

    public String getName() {
        return holder.getName();
    }

    public int getAction() {
        return holder.getAction(this);
    }

    public int getValue() {
        int score = 0;
        for (Card card : cards) {
            int value = card.getRank();
            switch (value) {
                case 1 -> {
                    value = 1;
                    score += value;
                }
                case 11, 12, 13 -> score += 10;
                default -> score += value;
            }
        }
        return score;
    }


    public List<Card> getCards() {
        return cards;
    }
}
