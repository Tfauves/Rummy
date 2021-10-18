package com.company.rummy;

import com.company.deck.Card;
import com.company.deck.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Actor holder;
    private int handPoints;

    public Hand(Actor player) {
        this.holder = player;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card removeCard(int index) {
        return cards.remove(index);
    }

    public int sumHand() {
        handPoints = 0;
        for (Card card : cards) {
          int value = card.getRank();
          switch (value) {
            case 1 -> {
                value = 1;
                handPoints += value;
            }
            case 11, 12, 13 -> handPoints += 10;
            default -> handPoints += value;
          };
       }

       return handPoints;
    }

    public int playerPoints(Card cardPlayed) {
        handPoints = 0;
        int value = cardPlayed.getRank();
        switch (value) {
            case 1 -> {
                value = 1;
                handPoints += value;
            }
            case 11, 12, 13 -> handPoints += 10;
            default -> handPoints += value;
        }
        return handPoints;

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

    public Actor getHolder() {
        return holder;
    }

    public void discardHand() {
        cards.clear();
    }


}
