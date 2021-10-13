package com.company.rummy;

public interface Actor {
    int DRAW = 1;
    int DISCARD_DRAW = 2;
    int SORT = 3;

    String getName();
    int getScore();
    int getAction(Hand hand);
}
