package com.company.rummy;


import com.company.actor.Player;
import com.company.deck.*;
import com.company.util.Console;

import java.util.*;

public class Table {
    private List<Hand> hands = new ArrayList<>();
    private Deck deck;
//    private Map<Integer, String> sets = new HashMap<>();
    private List<Card> setPlayArea = new ArrayList<>();
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
       while (activeRound) {
            playerTurn();

        }
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
            for (int count = 0; count < hands.size(); count++) {
                Hand player = hands.get(count);
                player.displayHand();
                while (true) {
                    if (!turn(player)) break;
                    if (!activeRound) {
                       endRound();
                    }
                }
                Console.showHandWithIndex(player);
                int index = Console.getInt("\nEnter number to discard", 0, 11, "invalid selection");
                discardPile.add(player.getCards().remove(index - 1));
                Console.getString("Enter to start next turn", false);
            }
    }

    private boolean turn(Hand activeHand) {
       // while (activeRound) {
            System.out.println(discardPile.get(discardPile.size() - 1).display());
            System.out.println(activeHand.getName());
            int action = activeHand.getAction();
            return switch (action) {
                case Actor.DRAW -> draw(activeHand);
                case Actor.DISCARD_DRAW -> drawDiscardedCard(activeHand);
                // TODO: 10/15/2021 sort method needs to show after a draw as well. 
                case Actor.SORT -> sortHand(activeHand);
                // TODO: 10/15/2021 strike this off ??? knock logic in place, need to track deadwood score. 
                case Actor.KNOCK -> knock(activeHand);
//                case Actor.LAY_DOWN_SET -> draw(activeHand);
                default -> false;
            };

    }

    private boolean draw(Hand activeHand) {
        Card newCard = deck.draw();
        System.out.println("You drew a " + newCard.display());
        activeHand.addCard(newCard);
        sortHand(activeHand);
        Console.showHandWithIndex(activeHand);
        layDownSet(activeHand);
        return false;
    }

    private boolean drawDiscardedCard(Hand activeHand) {
        activeHand.addCard(discardPile.get(discardPile.size() - 1));
        sortHand(activeHand);
        Console.showHandWithIndex(activeHand);
        layDownSet(activeHand);
        return false;
    }

    private boolean sortHand(Hand activeHand) {
        activeHand.getCards().sort(Comparator.comparing(Card::getRank));
        return true;
    }

    private boolean knock(Hand activeHand) {
        activeRound = false;
//        System.out.println(activeHand.sumHand());
        System.out.println(activeHand.getName() + "has knocked!");
        displayTable();
        return true;
    }

    private void playCard(Hand activeHand) {
        List<Card> tempList = new ArrayList<>();
            while (tempList.size() < 3) {
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                int index = Console.getInt("\nEnter card to play", 1, 11, "invalid selection");
                Card playCard = activeHand.getCards().get(index - 1);
                activeHand.removeCard(index -1);
                tempList.add(playCard);
                setPlayArea.add(playCard);
            }
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getRank() == setPlayArea.get(i).getRank()) {
                    System.out.println(setPlayArea.get(i).display());
                } else {
                    System.out.println("not a matching set");
                    for (Card card : tempList) {
                    activeHand.addCard(card);
                    }
                    setPlayArea.clear();
                    tempList.clear();
                    return;
                }
            }
            System.out.println("Good set");
            tempList.clear();

    }

    private boolean layDownSet(Hand activeHand) {
        String input = Console.getString("\nPlay set? y/n:", true);
        switch (input) {
            case "y" -> {
//                sortHand(activeHand);
//                Console.showHandWithIndex(activeHand);
                playCard(activeHand);
//                int cardAt1 = Console.getInt("\nEnter card to play", 1, 11, "invalid selection");
//                Card card1 = activeHand.getCards().get(cardAt1 - 1);
//                setPlayArea.add(card1);

//                System.out.println(card1.display() + "hello");
//                int cardAt2 = Console.getInt("\nEnter card to play", 1, 11, "invalid selection");
//                int cardAt3 = Console.getInt("\nEnter card to play", 1, 11, "invalid selection");
//                // TODO: 10/16/2021 need error check to make sure the rank of chosen cards are the same(if card at index of card input then set to play area.)
//                setPlayArea.add(activeHand.getCards().remove(cardAt1 - 1));
//                setPlayArea.add(activeHand.getCards().remove(cardAt2 - 1));
//                setPlayArea.add(activeHand.getCards().remove(cardAt3 - 1));
                //something blows up when index 10 is selected?????
                // TODO: 10/15/2021 cards played for a set are placed onto set area on the table(hash map???)
            }
            default -> {
                return true;
            }

        }
        return true;
    }

    private void determineWinner() {
        for (Hand players : hands) {
           int playerPoints = players.sumHand();
           players.getHolder().setPoints(playerPoints);
            System.out.println(players.getHolder().getName() + " " + players.getHolder().getPoints());
        }

    }

    private void endRound() {
        determineWinner();
        String newGame = Console.getString("Would you like to play again? y/n",true);
        if (newGame.equals("y")) {
         resetGame();
        }else {
            System.exit(0);
        }
    }

    private void resetGame() {
        for (Hand player : hands) {
            player.discardHand();
        }
        discardPile.clear();
        playGame();
    }



}
