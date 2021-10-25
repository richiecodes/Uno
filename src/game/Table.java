package game;

import parts.Card;
import parts.Deck;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public Deck deck;
    public List<Card> pile;

    public Table() {
        deck = new Deck();
        pile = new ArrayList<>();
        deck.buildDeck();
    }

    public Card pullTopCardFromDeck() {
        Card pulledCard = deck.cards.get(0);
        deck.cards.remove(pulledCard);
        return pulledCard;
    }

    public void setTableCard() {
        pile.add(deck.cards.get(0));
        deck.cards.remove(0);
    }

    public void showCard() {
        System.out.println("\nCard on table: |[ " + pile.get(0) + " ]|");
    }
}