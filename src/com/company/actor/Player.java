package com.company.actor;

import com.company.rummy.Actor;

public class Player implements Actor {
    private final String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }


}
