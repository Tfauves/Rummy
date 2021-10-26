package com.company.rummy;

public interface Actor {
    int DRAW = 1;
    int DISCARD_DRAW = 2;
    int SORT = 3;
    int KNOCK = 4;
    int SET = 1;
    int RUN = 2;
    int PLAY_CARD = 3;

    String getName();
    int getPoints();
    boolean canGoGin = true;
    void setPoints(int points);
    int getAction(Hand hand);
    int getMeldAction(Hand hand);
    boolean getCanGoGin();
}
