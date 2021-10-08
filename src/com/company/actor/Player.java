package com.company.actor;

import com.company.rummy.Actor;

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

    public int getAction() {
        return 0;
    }




}
