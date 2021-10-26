package com.company.actor;

import com.company.rummy.Actor;
import com.company.rummy.Hand;
import com.company.util.Console;


public class Player implements Actor {
    private final String name;
    private int points = 0;
    private int actionsCount;
    public boolean canGoGin = true;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAvailableActions(Hand hand) {
        actionsCount = 3;
        StringBuilder outPut = new StringBuilder();
        System.out.println("------------------------------------------------");
        outPut.append("1. Draw from deck\n2. Pick up discarded card\n3. Sort");
        int deadWood = hand.sumHand();
        if (deadWood <= 10) {
            outPut.append("\n4. Knock");
            actionsCount++;
        }
        return outPut.toString();
    }

    public String availableMeldAction(Hand hand) {
        StringBuilder output = new StringBuilder();
        output.append("\nMeld type:\n1. Set\n2. Run\n3. Play a Card");
        return output.toString();
    }

    public int getMeldAction(Hand hand) {

        return Console.getInt(availableMeldAction(hand),1, 3, "invalid");
    }


    @Override
    public int getAction(Hand hand) {
        System.out.println(hand.displayHand());
//        System.out.println(hand.getValue());
        return Console.getInt(getAvailableActions(hand), 1, actionsCount, "invalid action");
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getCanGoGin() {
        return canGoGin;
    }

    public void setCanGoGin(boolean canGoGin) {
        this.canGoGin = canGoGin;
    }
}
