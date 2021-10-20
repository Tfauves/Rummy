package com.company.rummy;

import com.company.deck.Card;
import com.company.deck.PlayingCard;

import java.util.ArrayList;
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

//    public void detectRun() {
//        List<Card> tempList = new ArrayList<>();
//        int count = 0;
//        for (int i = 0; i < cards.size() -1; i ++) {
//            int meldRank = cards.get(i).getRank() + 1;
//            if (cards.get(i + 1).getRank() == meldRank) {
//                meldRank++;
//              tempList.add(cards.get(i));
//            }
//        }
//        System.out.println(tempList);
//    }

    public Card detectRunCard() {
        Card tempCard = null;
        for (int i = 0; i < cards.size() -1; i ++) {
            int tempRank = cards.get(i).getRank();
            String tempSuit = cards.get(i).getSuit();
            tempCard = new PlayingCard(tempSuit, tempRank);
            int nextCardRank = tempRank + 1;
            if (cards.get(i + 1).getRank() == nextCardRank) {
                nextCardRank++;
                return cards.get(i);
//              tempList.add(cards.get(i));
            }
        }
//        System.out.println(tempList);
        return tempCard;
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
