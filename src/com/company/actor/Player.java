package com.company.actor;

import com.company.rummy.Actor;
import com.company.rummy.Hand;
import com.company.util.Console;


public class Player implements Actor {
    private final String name;
    private int points = 0;
    private int actionsCount;

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

    public int getPoints() {
        return points;
    }

    public String getAvailableActions(Hand hand) {
        actionsCount = 5;
        StringBuilder outPut = new StringBuilder();
        outPut.append("1. Draw from deck\n2. Pick up discarded card\n3. Sort\n4. Knock\n5. Play card");
//        int deadWood = hand.sumHand();
//        if (deadWood <= 10) {
//            outPut.append("\n4. Knock");
//            actionsCount++;
//        }
        return outPut.toString();
    }

    @Override
    public int getAction(Hand hand) {
        System.out.println(hand.displayHand());
//        System.out.println(hand.getValue());
        return Console.getInt(getAvailableActions(hand), 1, actionsCount, "invalid action");
    }


    public void setPoints(int points) {
        this.points = points;
    }
}
