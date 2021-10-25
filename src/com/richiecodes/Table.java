package com.richiecodes;

public class Table {
    public Deck deck;
    public Deck pile;
    public Card tableCard;

    public Table() {
        deck = new Deck();
        pile = new Deck();
        deck.buildDeck();
        tableCard = pullTopCard();
    }

    public Card pullTopCard() {
        Card pulledCard = deck.cards.get(0);
        deck.cards.remove(pulledCard);
        return pulledCard;
    }
}
