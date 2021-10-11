package com.company.rummy;


import com.company.actor.Player;
import com.company.deck.*;
import com.company.util.Console;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<Hand> hands = new ArrayList<>();
    private Deck deck;
    private List<Card> discardPile = new ArrayList<>();
    private int playerCount = 0;

    public Table() {
        playerCount = Console.getInt("number of players", 1, 6, "invalid input");
        for (int count = 0; count < playerCount; count++) {
            Player newPlayer = new Player("Player" + (count + 1) + ": ");
            hands.add(new Hand(newPlayer));

        }
    }

    public void playGame() {
        playARound();
    }

    public void playARound() {
        deck = new StandardDeck();
        deck.shuffle();
        deal();
//        displayTable();
        playerTurn();

        displayTable();
    }

    public void deal() {
        for (int count = 0; count < 10; count++) {
            for (Hand player : hands) {
                player.addCard(deck.draw());
            }
        }
        discardPile.add(deck.draw());
    }

    public void displayTable() {
        StringBuilder outPut = new StringBuilder();
        for (Hand player : hands) {
            outPut.append(player.getName()).append(" ").append( player.displayHand()).append(" | \n");
        }
        System.out.println(discardPile.get(0));
        System.out.println(outPut);
    }

    private void playerTurn() {
        for (int count = 0; count < hands.size(); count++) {
            Hand player = hands.get(count);
            player.displayHand();
            while (true) {
                if (!turn(player)) break;
            }
            System.out.println(player.displayHand());


            Console.getString("Enter to start next turn", false);
        }
    }

    private boolean turn(Hand activeHand) {
        System.out.println(discardPile.get(0));
        System.out.println(activeHand.getName());
        int action = activeHand.getAction();
        return switch (action) {
            case Actor.DRAW -> draw(activeHand);
            case Actor.DISCARD_DRAW -> drawDiscardedCard(activeHand);
            default -> false;
        };

    }

    private boolean draw(Hand activeHand) {
        activeHand.addCard(deck.draw());
        return false;
    }

    private boolean drawDiscardedCard(Hand activeHand) {
        activeHand.addCard(discardPile.get(0));
        return false;
    }

    // TODO: 10/10/2021 this method for card discard to end turn. 

    private boolean discard(Hand activeHand) {
        int faceValue = Console.getInt("to discard a card enter card rank 1-13", 1, 13, "invalid entry");
        int suit = Console.getInt("1. \u2664 | 2. \u2665 | 3. \u2666 | 4. \u2667", 1, 4, "Invalid entry");
        int cardIndex = 0;
        for (int i = 0; i < activeHand.getCards().size(); i++) {
            System.out.println(activeHand.getCards().get(i) + " " + cardIndex);
            activeHand.discard();

        }
        return false;
    }

    private void endTurn() {


    }


}
