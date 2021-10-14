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
    private boolean activeRound = false;

    public Table() {
        playerCount = Console.getInt("number of players", 1, 3, "invalid input");
        for (int count = 0; count < playerCount; count++) {
            Player newPlayer = new Player("Player" + (count + 1) + ": ");
            hands.add(new Hand(newPlayer));

        }
    }

    public void playGame() {
        deck = new StandardDeck();
        deck.shuffle();
        deal();
        playARound();
    }

    public void playARound() {
        activeRound = true;
        //while(activeRound) {
            playerTurn();
//            displayTable();
        //}

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
        System.out.println(outPut);
    }

    private void playerTurn() {
        while(activeRound) {
            for (int count = 0; count < hands.size(); count++) {
                Hand player = hands.get(count);
                player.displayHand();
                while (true) {
                    if (!turn(player)) break;
                }
                System.out.println(player.displayHand());
                for (int i = 1; i < player.getCards().size() + 1; i++) {
                    System.out.print(i + "    ");
                }
                int index = Console.getInt("\nEnter number to discard", 0, 11, "invalid selection");
                discardPile.add(player.getCards().remove(index - 1));
                Console.getString("Enter to start next turn", false);
            }
        }
    }

    private boolean turn(Hand activeHand) {
        System.out.println(discardPile.get(discardPile.size() -1).display());
        System.out.println(activeHand.getName());
        int action = activeHand.getAction();
        return switch (action) {
            case Actor.DRAW -> draw(activeHand);
            case Actor.DISCARD_DRAW -> drawDiscardedCard(activeHand);
            case Actor.SORT -> sortHand(activeHand);
            case Actor.KNOCK -> knock(activeHand);
            default -> false;
        };
    }

    private boolean draw(Hand activeHand) {
        Card newCard = deck.draw();
        System.out.println("You drew a  " + newCard);
        activeHand.addCard(newCard);
        return false;
    }

    private boolean drawDiscardedCard(Hand activeHand) {
        activeHand.addCard(discardPile.get(discardPile.size() - 1));
        return false;
    }

    private boolean sortHand(Hand activeHand) {
        return true;
    }

    private boolean knock(Hand activeHand) {
        activeRound = false;
        System.out.println(activeHand.sumHand());
        System.out.println(activeHand.getName() + "has knocked!");
        return false;
    }

    private void endRound() {

    }

    private void determineWinner(Hand player) {


    }





}
