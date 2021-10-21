package com.company.rummy;

import com.company.deck.Card;

public class Set {

    public Set() {

    }


//    private boolean layDownSet(Hand activeHand) {
//        String input = Console.getString("\nmeld set? y/n:", true);
//        switch (input) {
//            case "y" -> {
//                int meldSize = Console.getInt("select number of cards to meld (3 or 4)", 3, 4, "invalid input");
//                List<Card> tempList = new ArrayList<>();
//                int counter = 0;
//                while (counter < meldSize) {
//                    Console.showHandWithIndex(activeHand);
//                    int index = Console.getInt("\nenter card number",1, 11, "invalid");
//                    Card meldCard = activeHand.getCards().get(index - 1);
//                    tempList.add(meldCard);
//                    System.out.println(meldCard.display());
//                    activeHand.removeCard(index - 1);
//                    counter++;
//                }
//
//                for (int i = 0; i < tempList.size() -1; i++) {
//                    if (tempList.get(i).getRank() == tempList.get(i + 1).getRank()) {
//                        System.out.println("good set");
//                    }
//                    else {
//                        activeHand.addCard(tempList.get(i));
//                        System.out.println("set is not a match");
//                    }
//                }
//
//                System.out.println(tempList);
//            }
//            default -> {
//                return true;
//            }
//        }
//        return true;
//    }

    //    private void playRunCard(Hand activeHand) {
//        List<Card> tempList = new ArrayList<>();
//        int meldSize = Console.getInt("select number of cards to meld (3 or 4)", 3, 4, "invalid input");
//        int userInput = Console.getInt("\nenter card number", 1, 11, "invalid");
//        while (tempList.size() < meldSize) {
//            sortHand(activeHand);
//            int index = userInput - 1;
//            for (int i = index; i < activeHand.getCards().size() - 1; i++) {
//                Card meldCard = activeHand.getCards().get(i);
//                int nextRunCardRank = meldCard.getRank() + 1;
//                if (activeHand.getCards().get(i + 1).getRank() == nextRunCardRank) {
//                    tempList.add(meldCard);
//                    tempList.add(activeHand.getCards().get(i));
//                    activeHand.removeCard(index);
//                }
//                else {
//                    System.out.println("not a match");
//                    for (Card cards : tempList) {
//                        activeHand.addCard(cards);
//                    }
//                    tempList.clear();
//                    layDownSet(activeHand);
//                    break;
//                }
//
//            }
//
//        }
//            System.out.println("current runs played: " + tempList);
//
//    }





}
