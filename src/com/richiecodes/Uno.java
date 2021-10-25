package com.richiecodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uno {
    private List<Player> players;
    private Table table;
    private Scanner scanner = new Scanner(System.in);
    private int activePlayer = 0;

    public Uno() {
        players = new ArrayList<>();
        table = new Table();
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
        players.get(activePlayer).showHand();
    }

    public void dealHandToPlayer(Player player) {
        for (int i = 0; i < 7; i++) {
            player.playerHand.cards.add((table.pullTopCard()));
        }
    }

    private void turn() {
        System.out.println("Card on table: |[ " + table.tableCard + " ]|");
        System.out.println("\nWhat will " + players.get(activePlayer).name + " do?: ");
        int playerChoice = menuOption();

        switch (playerChoice) {

        }
    }

    private int menuOption() {
        System.out.println("1. Play");
        System.out.println("2. Quit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //TODO add functionality
                System.out.println("play");
                return choice;

            default:
                System.exit(0);
        }

        return 0;
    }

    private void cls() {
        int i = 100;
        while (i > 0) {
            System.out.println();
            i--;
        }
    }
}
