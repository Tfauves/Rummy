package com.company.rummy;

public interface Actor {
    int DRAW = 1;
    int DISCARD_DRAW = 2;
    int SORT = 3;
    int KNOCK = 4;
    int LAY_DOWN_SET = 5;

    String getName();
    int getPoints();
    void setPoints(int points);
    int getAction(Hand hand);
}
