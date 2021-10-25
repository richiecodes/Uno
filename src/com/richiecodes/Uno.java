package com.richiecodes;

import java.util.ArrayList;
import java.util.List;

public class Uno {
    private List<Player> players;
    private Table table;
    private final int MIN_PLAYERS = 2;
    private final int MAX_PLAYERS = 6;
    boolean running = true;
    private final int WINNING_SCORE = 500;

    //TODO: add skip card and other special card abilities
    //TODO: Clean/refactor code for less redundancy and more readability

    public Uno() {
        players = new ArrayList<>();
        table = new Table();
        setGame();
        Console.cls();
        round();
    }

    public void setGame() {
        int numberOfPlayers = Console.getInt(MIN_PLAYERS, MAX_PLAYERS, "How many players?");

        int playerNumber = 1;
        while (players.size() < numberOfPlayers) {
            players.add(new Player(Console.getString("Enter name for player " + playerNumber)));
            playerNumber++;
        }

        for (Player player : players) {
            dealHandToPlayer(player);
        }
    }

    public void dealHandToPlayer(Player player) {
        for (int i = 0; i < 7; i++) {
            player.playerHand.cards.add((table.pullTopCardFromDeck()));
        }
    }

    private void round() {
        table.setTableCard();
        if (table.pile.get(0).type.equals("WILD") || table.pile.get(0).type.equals("WILD DRAW 4")) {
            table.pile.add(0, table.pullTopCardFromDeck());
        }

        if (table.pile.get(0).type.equals("DRAW 2")) {
            players.get(0).playerHand.cards.add(table.pullTopCardFromDeck());
            players.get(0).playerHand.cards.add(table.pullTopCardFromDeck());
        }

        while(running) {
            for (Player activePlayer : players) {
                turn(activePlayer);
            }
        }
    }

    private void turn(Player activePlayer) {
        if (checkForWinner(activePlayer)) {
            winScreen(activePlayer);
        } else {
            activePlayer.showHand();
            System.out.println("Score: " + activePlayer.score);
            table.showCard();
            System.out.println("\nWhat will " + activePlayer + " do?: ");
            int playerChoice = menuOption();

            switch (playerChoice) {
                case 1:
                    playCard(activePlayer);
                    break;

                default:
                    break;
            }
        }
    }

    private void playCard(Player activePlayer) {
        List<Card> playerHand = activePlayer.playerHand.cards;
        int choice;
        do {
            choice = Console.getInt(1, playerHand.size(), "Which card would you like to play?");
        } while (choice > playerHand.size());

        if (playableCard(playerHand.get(choice - 1))) {
            table.pile.add(0, playerHand.get(choice - 1));
            activePlayer.score += playerHand.get(choice - 1).faceValue;

            playerHand.remove(choice - 1);

            Console.cls();

            System.out.println("new card on table: " + table.pile.get(0));
        } else {
            Console.cls();
            System.out.println("Invalid card");
            turn(activePlayer);
        }
    }

    private boolean checkForWinner(Player activePlayer) {
        return activePlayer.score >= WINNING_SCORE || activePlayer.playerHand.cards.isEmpty();
    }

    private boolean playableCard(Card card) {
        return card.color.equals(table.pile.get(0).color) || card.faceValue == table.pile.get(0).faceValue || card.type.equals("WILD") || card.type.equals("WILD DRAW 4");
    }

    private int menuOption() {
        System.out.println("1. Play");
        System.out.println("2. Quit");
        int choice = Console.getInt(1, 2, "Enter choice:");

        switch (choice) {
            case 1:
                return choice;

            case 2:
                System.out.println("Exiting...");
                System.exit(0);
        }

        return 0;
    }

    private void winScreen(Player activePlayer) {
        Console.cls();
        System.out.println(activePlayer + " wins!!!");
        System.out.println("Score: " + activePlayer.score);
        running = false;
    }
}
