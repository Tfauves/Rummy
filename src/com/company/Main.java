package com.company;

import com.company.deck.Card;
import com.company.deck.Deck;
import com.company.deck.StandardDeck;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Deck deck = new StandardDeck();
        deck.shuffle();
        deck.draw().flip();
        System.out.println(deck.draw().display());


    }
}
