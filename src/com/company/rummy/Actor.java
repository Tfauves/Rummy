package com.company.rummy;

public interface Actor {
    int DRAW = 1;
    int DISCARD_DRAW = 2;
    int SORT = 3;
    int KNOCK = 4;

    String getName();
    int getPoints();
    int getAction(Hand hand);
}
