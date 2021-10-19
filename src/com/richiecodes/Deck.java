package com.richiecodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    /*
    112 cards as follows:
    19 Blue cards - 0 to 9
    19 Green cards - 0 to 9
    19 Red cards - 0 to 9
    19 Yellow cards - 0 to 9
    8 Skip cards - 2 each in Blue, Green, Red and Yellow
    8 Reverse cards - 2 each in Blue, Green, Red and Yellow
    8 Draw 2 cards - 2 each in Blue, Green, Red and Yellow
    4 Wild cards
    4 Wild Draw 4 cards
    4 Blank Cards
     */
    public List<Card> cards = new ArrayList<>();
    private final String[] COLORS = new String[]{"Blue", "Green", "Yellow", "Red"};

    public void buildDeck() {
        dealNumericCards();
        dealSpecialCards();
        Collections.shuffle(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    private void dealNumericCards() {
        for (int i = 0; i < COLORS.length; i++) {
            for (int j = 0; j < 10; j++) {
                cards.add(new Card((byte) j, "number", COLORS[i]));
            }
        }

        for (int i = 0; i < COLORS.length; i++) {
            for (int j = 1; j <= 9; j++) {
                cards.add(new Card((byte) j, "number", COLORS[i]));
            }
        }
    }

    private void dealSpecialCards() {
        //Skip Cards
        for (int i = 0; i < COLORS.length; i++) {
            for (int j = 0; j < 2; j++) {
                cards.add(new Card((byte) 20, "SKIP", COLORS[i]));
            }
        }

        //Reverse cards
        for (int i = 0; i < COLORS.length; i++) {
            for (int j = 0; j < 2; j++) {
                cards.add(new Card((byte) 20, "REVERSE", COLORS[i]));
            }
        }

        //Draw 2 cards
        for (int i = 0; i < COLORS.length; i++) {
            for (int j = 0; j < 2; j++) {
                cards.add(new Card((byte) 20, "DRAW 2", COLORS[i]));
            }
        }

        // Wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card((byte) 50, "WILD", "Black"));
        }

        // Wild draw 4 cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card((byte) 50, "WILD DRAW 4", "Black"));
        }

        // Blank cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card((byte) 0, "BLANK", "Black"));
        }
    }

    public void printDeck() {
        for (var card : cards) {
            System.out.println(card);
        }
    }
}
