package com.company.rummy;

import com.company.actor.Player;
import com.company.deck.*;
import com.company.util.Console;

import java.util.*;

public class Table {
    private List<Hand> hands = new ArrayList<>();
    private Deck deck;
    private List<Card> setPlayArea = new ArrayList<>();
    private List<Card> runPlayArea = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();
    private boolean activeRound = false;
    private final int HAND_CARD_AMT = 10;

    public Table() {
        int playerCount = Console.getInt("number of players", 1, 2, "invalid input");
        for (int count = 0; count < playerCount; count++) {
            Player newPlayer = new Player("Player" + (count + 1) + ": ");
            hands.add(new Hand(newPlayer));
        }
        Console.spaces();
    }

    public StringBuilder setPlayAreaDisplay() {
        StringBuilder sets = new StringBuilder();
        for (Card card : setPlayArea) {
            sets.append(card.display()).append(" ");
        }
        return sets;
    }

    public void playGame() {
      deck = new StandardDeck();
//      deck = new TestDeck();
        deck.shuffle();
        gamePoints();
        deal();
        playARound();
    }

    public void playARound() {
        activeRound = true;
//        deck.replenishDeck(discardPile);
       while (activeRound) {
            playerTurn();
            goneOut();
        }
       endRound();
    }

    public void deal() {
        for (int count = 0; count < HAND_CARD_AMT; count++) {
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

    // TODO: 10/24/2021 toString method for set and run play areas.
    private void displayPlayAreas() {
        if (setPlayArea.size() > 0) {
            System.out.println("\ncurrent sets: " + setPlayAreaDisplay());
        }
        if(runPlayArea.size() > 0) {
            System.out.println("\ncurrent runs:" + runPlayArea);
        }
    }

    private void playerTurn() {
        boolean getInput = true;
            for (int count = 0; count < hands.size(); count++) {
                Hand player = hands.get(count);
                player.displayHand();
                while (true) {
                    if (!turn(player)) break;
                    if (!activeRound) {
                       endRound();
                    }
                }
                sortHand(player);
                Console.showHandWithIndex(player);
                while (getInput) {
                    int index = Console.getInt("\nEnter number to discard", 1, 11, "invalid selection");
                    try {
                        discardPile.add(player.getCards().remove(index - 1));
                        getInput = false;

                    }catch (IndexOutOfBoundsException err) {
                        System.out.println("invalid selection");
                    }


                }
                Console.getString("\nEnter to start next turn", false);
                getInput = true;
                Console.spaces();
            }
    }

    private boolean turn(Hand activeHand) {
        System.out.println("Discard Pile | " + discardPile.get(discardPile.size() - 1).display());
        System.out.println();
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
        Console.spaces();
        Card newCard = deck.draw();
        System.out.println("You drew a " + newCard.display());
        System.out.println();
        activeHand.addCard(newCard);
        sortHand(activeHand);
        Console.showHandWithIndex(activeHand);
//        activeHand.detectRunCard();
        layDownMeld(activeHand);
        return false;
    }

    private boolean drawDiscardedCard(Hand activeHand) {
        activeHand.addCard(discardPile.get(discardPile.size() - 1));
        sortHand(activeHand);
        Console.showHandWithIndex(activeHand);
        layDownMeld(activeHand);
        return false;
    }

    private boolean sortHand(Hand activeHand) {
        activeHand.getCards().sort(Comparator.comparing(Card::getRank));
        return true;
    }

    private boolean knock(Hand activeHand) {
        activeRound = false;
        System.out.println(activeHand.sumHand());
        System.out.println(activeHand.getName() + "has knocked!");
        displayTable();
        return true;
    }

    private void playSetCard(Hand activeHand) {
        List<Card> tempList = new ArrayList<>();
        int meldSize = Console.getInt("Total meld size (3 or 4)", 3, 4, "invalid input");
        int userInput = Console.getInt("\nStarting set card location?", 1, 11, "invalid");
        while (tempList.size() < meldSize) {
            sortHand(activeHand);
            int index = userInput - 1;
            Card meldCard = activeHand.getCards().get(index);
            if (tempList.size() == 0) {
                tempList.add(meldCard);
                activeHand.removeCard(index);
            }
            else if (meldCard.getRank() == tempList.get(0).getRank()) {
            tempList.add(meldCard);
            activeHand.removeCard(index);
            }
            else {
                System.out.println("not a match");
                for (Card cards : tempList) {
                activeHand.addCard(cards);
                }
                tempList.clear();
                layDownMeld(activeHand);
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                break;
            }

        }
        setPlayArea.addAll(tempList);
        if (setPlayArea.size() > 0) {
            System.out.println("current sets played: " + setPlayAreaDisplay());
            String playMoreCards = Console.getString("Play more cards? y/n", false);
            String lowerPlatMoreCards = playMoreCards.toLowerCase();
                if ("y".equals(lowerPlatMoreCards)) {
                    sortHand(activeHand);
                    Console.showHandWithIndex(activeHand);
                    layDownMeld(activeHand);
                }

        }
    }

    private void playRunCard(Hand activeHand) {
        List<Card> tempList = new ArrayList<>();
        Card runCard;
        int meldSize = Console.getInt("select number of cards to meld (3 or 4)", 3, 4, "invalid input");
        while (tempList.size() < meldSize) {
            sortHand(activeHand);
            Console.showHandWithIndex(activeHand);
                int userInput = Console.getInt("\nEnter card to play", 1, activeHand.getCards().size(), "invalid selection");
                try {
                    int cardIndex = userInput - 1;
                    runCard = activeHand.getCards().get(cardIndex);
                    tempList.add(runCard);
                    activeHand.removeCard(cardIndex);
                } catch (IndexOutOfBoundsException err) {
                    System.out.println("invalid selection");
                }
        }

        for (int i = 0; i < tempList.size() -1; i++) {
            int cardRank = tempList.get(i).getRank();
            String cardSuit = tempList.get(i).getSuit();
            int nextRunCardRank = cardRank + 1;
            if (tempList.get(i + 1).getRank() != nextRunCardRank || !tempList.get(i + 1).getSuit().equals(cardSuit)) {
                System.out.println("not a match");
                for (Card cards : tempList) {
                    activeHand.addCard(cards);
                }
                tempList.clear();
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                layDownMeld(activeHand);
                break;
//
            }
//            else {
//                System.out.println("not a match");
//                for (Card cards : tempList) {
//                    activeHand.addCard(cards);
//                }
//                tempList.clear();
//                sortHand(activeHand);
//                Console.showHandWithIndex(activeHand);
//                layDownMeld(activeHand);
//                break;
//            }
        }
        runPlayArea.addAll(tempList);
        if (runPlayArea.size() > 0) {
            System.out.println("current runs played: " + runPlayArea);
            String playMoreCards = Console.getString("Play more cards? y/n", false);
            String lowerPlayMoreCards = playMoreCards.toLowerCase();
            if ("y".equals(lowerPlayMoreCards)) {
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                layDownMeld(activeHand);
            }
        }
//        System.out.println(tempList);
    }

    private void goneOut() {
        for (Hand players : hands) {
           if (players.getCards().size() == 0) {
               activeRound = false;
               endRound();
           }
        }
    }

    private void layDownMeld(Hand activeHand) {
        sortHand(activeHand);
        displayPlayAreas();
        String input = Console.getString("\nmeld set?, run? or card? s/r/c:\nEnter to skip:", false);
        String lowerInput = input.toLowerCase();
        switch (lowerInput) {
            case "s" -> {
                playSetCard(activeHand);
            }
            case "r" -> {
                playRunCard(activeHand);
            }
            case "c" -> {
                addCardToMeld(activeHand);
            }
        }
        Console.spaces();
    }

    private void addCardToMeld(Hand activeHand) {
                //get sets
        displayPlayAreas();
        String input = Console.getString("\nmeld a card: add to set or run? s/r:", false);
        String lowerInput = input.toLowerCase();
        switch (lowerInput) {
            case "s" -> {
                if (setPlayArea.size() == 0) {
                    System.out.println("not valid");
                    break;
                }
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                int userCardChoiceIndex = Console.getInt("\nselect a card to play", 1, 11, "invalid");
                int meldCardIndex = userCardChoiceIndex - 1;
                Card meldCard = activeHand.getCards().get(meldCardIndex);
                activeHand.removeCard(meldCardIndex);
                for (int i = 0; i < setPlayArea.size(); i++) {
                    if (meldCard.getRank() == setPlayArea.get(i).getRank()) {
                        setPlayArea.add(meldCard);
                        System.out.println(setPlayAreaDisplay());
                        break;
                    }
                }
            }
            case "r" -> {
                //get runs
                if (runPlayArea.size() == 0) {
                    System.out.println("not valid");
                    break;
                }
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                int userChoiceIndex = Console.getInt("\nselect a card to play", 1, 11, "invalid");
                int runCardIndex = userChoiceIndex - 1;
                Card runMeldCard = activeHand.getCards().get(runCardIndex);
                activeHand.removeCard(runCardIndex);
                int nextCardRank = runMeldCard.getRank() - 1;
                //If the rank of the players card is less than the rank of the card at index(i) insert card at index i-1.
                // If player card is greater than index(i) insert card at index(i+1)
                for (int i = 0; i < runPlayArea.size(); i++) {
                    if (runMeldCard.getSuit().equals(runPlayArea.get(i).getSuit()) ) {
                        System.out.println("suits match");
                        if (runMeldCard.getRank() < runPlayArea.get(0).getRank()) {
                            runPlayArea.add(runMeldCard);
                        }
                        if (nextCardRank == runPlayArea.get(runPlayArea.size() - 1).getRank()) {
                                runPlayArea.add(runMeldCard);
                        }
                        else {
                            System.out.println("not valid");
                            activeHand.addCard(runMeldCard);
                        }
                        runPlayArea.sort(Comparator.comparing(Card::getRank));
                        System.out.println(runPlayArea);
                        break;
                    }
                }

            }

        }

    }
// TODO: 10/22/2021 check for gin
    //need to count the number of cards that a player has melded during a turn if they manage to meld 10 cards and then discard, they go gin and earn 20 bonus points.

    private void gamePoints() {
        for (Hand players : hands) {
            System.out.println(players.getHolder().getName() + "Score: " + players.getHolder().getPoints());
            System.out.println();
            if (players.getHolder().getPoints() >= 100) {
                System.out.println(players.getHolder().getName() + "Wins the game");
                System.exit(200);
            }
        }
    }

    private void totalRdPoints() {
      for (int i = 0; i < hands.size() - 1; i++) {
          int player1HandPoints = hands.get(i).sumHand();
          int player2HandPoints = hands.get(i + 1).sumHand();
          int totalRdPoints;
          if (player1HandPoints < player2HandPoints) {
               totalRdPoints = player2HandPoints - player1HandPoints;
              hands.get(i).getHolder().setPoints(hands.get(i).getHolder().getPoints() + totalRdPoints);
          } else {
              totalRdPoints =  player1HandPoints - player2HandPoints ;
              hands.get(i + 1).getHolder().setPoints( hands.get(i + 1).getHolder().getPoints() + totalRdPoints);
          }
          System.out.println(hands.get(i).getHolder().getName() + hands.get(i).getHolder().getPoints());

      }

    }

    private void determineWinner() {
        for (Hand players : hands) {
          int playerPoints = players.sumHand();
//           players.getHolder().setPoints(playerPoints);
            System.out.println(players.getHolder().getName() + "total hand points: " + playerPoints);
        }

    }

    private void endRound() {
        determineWinner();
        totalRdPoints();
        String newGame = Console.getString("Would you like to start the next round? y/n",true);
        String lowerNewGameInput = newGame.toLowerCase();
        if (newGame.equals("y")) {
         resetGame();
        }else {
            gamePoints();
            System.out.println("thanks for playing");
            System.exit(0);
        }
    }

    private void resetGame() {
        for (Hand player : hands) {
            player.discardHand();
        }
        discardPile.clear();
        setPlayArea.clear();
        runPlayArea.clear();
        playGame();
    }


}
