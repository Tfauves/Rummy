package com.company;


import com.company.actor.Player;
import com.company.deck.Deck;
import com.company.deck.PlayingCard;
import com.company.deck.StandardDeck;
import com.company.rummy.Hand;

public class Main {

    public static void main(String[] args) {
        Deck rumDeck = new StandardDeck();
        Hand myHand = new Hand(new Player("travis"));
        myHand.addCard(rumDeck.draw());
        System.out.println(myHand.displayHand());

    }
}
