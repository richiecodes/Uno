package com.richiecodes;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        deck.printDeck();
        System.out.println("\n" + deck.cards.size() + " cards in deck");
    }
}
