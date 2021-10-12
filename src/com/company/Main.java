package com.company;


import com.company.actor.Player;
import com.company.deck.Deck;
import com.company.deck.PlayingCard;
import com.company.deck.StandardDeck;
import com.company.rummy.Hand;
import com.company.rummy.Table;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Table rummy = new Table();
        rummy.playGame();

    }


//    StringBuilder cardNumbers = new StringBuilder();
//            for(int i = 1; i <= 11; i++)
//            cardNumbers.append(String.format("     %s     ", i < 10 ? i + " " : i));
//            System.out.println(cardNumbers);
//
//            System.out.println("Select a card to get rid of:");
//            discardPile.add(activePlayer.removeCard(Input.getInt(1, 11) - 1));

}
