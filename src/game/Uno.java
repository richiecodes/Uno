package game;

import actor.Player;
import parts.Card;
import ui.Console;

import java.util.ArrayList;
import java.util.List;

public class Uno {
    private final List<Player> players;
    private final Table table;
    private boolean running = true;
    private boolean firstTurn = true;

    public Uno() {
        players = new ArrayList<>();
        table = new Table();
        setGame();
        Console.cls();
        round();
    }

    public void setGame() {
        Console.cls();
        System.out.println("--------U N O--------\n\n");
        int MAX_PLAYERS = 6;
        int MIN_PLAYERS = 2;
        int numberOfPlayers = Console.getInt(MIN_PLAYERS, MAX_PLAYERS, "How many players?");

        int playerNumber = 1;
        while (players.size() < numberOfPlayers) {
            Console.cls();
            players.add(new Player(Console.getString("Enter name for player " + playerNumber)));
            playerNumber++;
        }

        for (Player player : players) {
            dealHandToPlayer(player);
        }
    }

    public void dealHandToPlayer(Player player) {
        int HAND_SIZE = 7;
        for (int i = 0; i < HAND_SIZE; i++) {
            player.playerHand.cards.add((table.pullTopCardFromDeck()));
        }
    }

    private void round() {
        table.setTableCard();
        List<Card> pile = table.pile;
        Card pileCard = pile.get(0);
        Player firstPlayer = players.get(0);
        //Player lastPlayer = players.get(players.size() - 1);

        switch (pileCard.type) {
            case "WILD", "WILD DRAW 4" -> table.pile.add(0, table.pullTopCardFromDeck());
            case "SKIP" -> turn(players.get(1));
            case "DRAW 2" -> {
                addCardsToPlayerHand(firstPlayer, 2);
                firstTurn = false;
            }
        }
        while(running) {
            for (Player activePlayer : players) {
                turn(activePlayer);
                checkForWinner();
            }
        }
    }

    private void turn(Player activePlayer) {
        checkForWinner();
        if (table.deck.cards.isEmpty()) {
            table.deck.cards.addAll(table.pile);
            table.pile.clear();
        }
        switch (table.pile.get(0).type) {
            case "DRAW 2" -> {
                if (!firstTurn) addCardsToPlayerHand(activePlayer, 2);
            }
            case "WILD DRAW 4" -> {
                if (!firstTurn) addCardsToPlayerHand(activePlayer, 4);
            }
        }
        Console.cls();
        activePlayer.showHand();
        System.out.println("Score: " + activePlayer.getScore());
        table.showCard();
        System.out.println("Cards left in deck: " + table.deck.cards.size());
        System.out.println("\nWhat will " + enhancedPlayerName(activePlayer) + " do?: ");
        int playerChoice = menuOption();

        switch (playerChoice) {
            case 1 -> playCard(activePlayer);
            case 2 -> {
                if (running) drawCard(activePlayer);
                System.out.println(enhancedPlayerName(activePlayer) + " draws a card");
            }
        }
    }

    private void addCardsToPlayerHand(Player activePlayer, int amount) {
        for (int count = 0; count < amount; count++) {
            activePlayer.playerHand.cards.add(table.pullTopCardFromDeck());
        }
    }

    private void playCard(Player activePlayer) {
        List<Card> playerHand = activePlayer.playerHand.cards;
        int choice;
        do {
            choice = Console.getInt(1, playerHand.size(), "Which card would you like to play?");
        } while (choice > playerHand.size());

        if (validCard(playerHand.get(choice - 1))) {
            table.pile.add(0, playerHand.get(choice - 1));
            activePlayer.setScore(playerHand.get(choice - 1).faceValue);
            playerHand.remove(choice - 1);
        } else {
            Console.cls();
            System.out.println("Invalid card");
            turn(activePlayer);
        }
    }

    private void drawCard(Player activePlayer) {
        activePlayer.playerHand.cards.add(table.pullTopCardFromDeck());
    }

    private void checkForWinner() {
        for (Player player : players) {
            if (player.playerHand.cards.isEmpty() || player.getScore() >= 500) {
                running = false;
                winScreen(player);
            }
        }
    }

    private boolean validCard(Card card) {
        return card.color.equals(table.pile.get(0).color) || card.faceValue == table.pile.get(0).faceValue ||
                card.type.equals("WILD") || card.type.equals("WILD DRAW 4");
    }

    private int menuOption() {
        System.out.println("1. Play");
        System.out.println("2. Draw card");
        System.out.println("99. Quit");
        int choice = Console.getInt(1, 3, "Enter choice:");

        switch (choice) {
            case 1, 2 -> {
                return choice;
            }

            case 99 -> {
                System.out.println("Exiting...");
                System.exit(0);
            }
        }

        return 0;
    }

    private void winScreen(Player activePlayer) {
        Console.cls();
        System.out.println(enhancedPlayerName(activePlayer) + " wins!!!\n");
        System.out.println("Score: " + activePlayer.getScore());
        running = false;
    }

    private String enhancedPlayerName(Player activePlayer) {
        return activePlayer.getName().substring(0, 1).toUpperCase() + activePlayer.getName().substring(1);
    }
}
