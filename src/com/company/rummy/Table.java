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
//        deck = new StandardDeck();
        deck = new TestDeck();
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
        for (int count = 0; count < 4; count++) {
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
                sortHand(player);
                int index = Console.getInt("\nEnter number to discard", 1, 11, "invalid selection");
                discardPile.add(player.getCards().remove(index - 1));
                Console.getString("Enter to start next turn", false);
            }
    }

    private boolean turn(Hand activeHand) {
        System.out.println(discardPile.get(discardPile.size() - 1).display());
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
        System.out.println("You drew a " + newCard.display());
        activeHand.addCard(newCard);
        sortHand(activeHand);
        Console.showHandWithIndex(activeHand);
//        activeHand.detectRunCard();
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
        System.out.println(activeHand.sumHand());
        System.out.println(activeHand.getName() + "has knocked!");
        displayTable();
        return true;
    }

    private void playSetCard(Hand activeHand) {
        List<Card> tempList = new ArrayList<>();
        int meldSize = Console.getInt("Total meld size (3 or 4)", 3, 4, "invalid input");
        int userInput = Console.getInt("\nStarting set cards location?", 1, 11, "invalid");
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
                layDownSet(activeHand);
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                break;
            }

        }
        setPlayArea.addAll(tempList);
        if (setPlayArea.size() > 0) {
            System.out.println("current sets played: " + setPlayArea);
            String playMoreCards = Console.getString("Play more cards? y/n", false);
                if ("y".equals(playMoreCards)) {
                    sortHand(activeHand);
                    Console.showHandWithIndex(activeHand);
                    layDownSet(activeHand);
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
            int userInput = Console.getInt("\nEnter card to play", 1, 11, "invalid selection");
            int cardIndex = userInput - 1;
            runCard = activeHand.getCards().get(cardIndex);
            tempList.add(runCard);
            //could result in crash.
            activeHand.removeCard(cardIndex);
        }

        for (int i = 0; i < tempList.size() -1; i++) {
            int cardRank = tempList.get(i).getRank();
            String cardSuit = tempList.get(i).getSuit();
            int nextRunCardRank = cardRank + 1;
            if (tempList.get(i + 1).getRank() == nextRunCardRank && tempList.get(i + 1).getSuit().equals(cardSuit)) {
                System.out.println("good run");
            }
            else {
                System.out.println("not a match");
                for (Card cards : tempList) {
                    activeHand.addCard(cards);
                }
                tempList.clear();
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                layDownSet(activeHand);
                break;
            }
        }
        runPlayArea.addAll(tempList);
        if (runPlayArea.size() > 0) {
            System.out.println("current runs played: " + runPlayArea);
            String playMoreCards = Console.getString("Play more cards? y/n", false);
            if ("y".equals(playMoreCards)) {
                sortHand(activeHand);
                Console.showHandWithIndex(activeHand);
                layDownSet(activeHand);
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

    private void layDownSet(Hand activeHand) {
        sortHand(activeHand);
        String input = Console.getString("\nmeld set? or run? s/r:\nEnter to skip:", false);
        switch (input) {
            case "s" -> {
                playSetCard(activeHand);
            }
            case "r" -> {
                playRunCard(activeHand);
            }
        }
    }

    private void addCardToPlay(Hand activeHand) {
        String input = Console.getString("\nmeld a card: add to set or run? s/r:", false);
        switch (input) {
            case "s" -> {
                int userCardChoiceIndex = Console.getInt("select a card to play", 1, 11, "invalid");
                int meldCardIndex = userCardChoiceIndex - 1;
                Card meldCard = activeHand.getCards().get(meldCardIndex);
                activeHand.removeCard(meldCardIndex);
                for (int i = 0; i < setPlayArea.size(); i++) {
                    if (meldCard.getRank() == setPlayArea.get(i).getRank()) {
                        setPlayArea.add(meldCard);
                        activeHand.removeCard(meldCardIndex);
                        break;
                    }
                }
                //get sets
            }
            case "r" -> {
                //get runs
            }
        }

    }

    private void gamePoints() {
        for (Hand players : hands) {
            System.out.println(players.getHolder().getName() + "Score: " + players.getHolder().getPoints());
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
