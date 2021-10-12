package com.company.rummy;

import com.company.deck.Card;
import com.company.deck.PlayingCard;
import com.company.util.Console;

import java.util.ArrayList;
import java.util.Collections;
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

    public Card removeCard(int index) {
        return cards.remove(index);
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
