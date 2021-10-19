package com.richiecodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uno {
    private List<Player> players;
    private Deck deck;
    private Deck discardPile;
    private Scanner scanner = new Scanner(System.in);


    public Uno() {
        players = new ArrayList<>();
        deck = new Deck();
        discardPile = new Deck();
        deck.buildDeck();
        setGame();
    }

    public void setGame() {
        System.out.println("Enter number of players: ");
        int playerAmount = scanner.nextInt();
        for (int i = 0; i < playerAmount; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name: ");
            String playerName = scanner.next();
            players.add(new Player(playerName));
            dealHandToPlayer(players.get(i));
        }
        cls();
        players.get(0).showHand();
        tableCard();
    }

    public void dealHandToPlayer(Player player) {
        for (int i = 0; i < 7; i++) {
            player.playerHand.cards.add(pullTopCard());
        }
    }

    public Card pullTopCard() {
        Card pulledCard = deck.cards.get(0);
        deck.cards.remove(pulledCard);
        return pulledCard;
    }

    private void tableCard() {
        System.out.println("\n\n\tCard on table: |" + deck.cards.get(0) + "|");
        discardPile.cards.add(deck.cards.get(0));
        deck.cards.remove(0);
    }

    private void cls() {
        int i = 100;
        while (i > 0) {
            System.out.println();
            i--;
        }
    }
}
