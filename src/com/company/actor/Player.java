package com.company.actor;

import com.company.rummy.Actor;
import com.company.rummy.Hand;
import com.company.util.Console;


public class Player implements Actor {
    private final String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int getAction(Hand hand) {
        System.out.println(hand.displayHand());
//        System.out.println(hand.getValue());
        return Console.getInt(getAvailableActions(hand), 1, 4, "invalid action");
    }

    public String getAvailableActions(Hand hand) {
//        actionsCount = 2;
        StringBuilder outPut = new StringBuilder();
        outPut.append("1. Draw from deck\n2. Pick up discarded card\n3. Sort\n4. Knock");
        return outPut.toString();
    }


}
