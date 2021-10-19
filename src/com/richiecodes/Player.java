package com.richiecodes;

public class Player {

    public Hand playerHand;
    private String name;
    public int score;

    public Player(String name) {
        this.name = name;
        playerHand = new Hand();
    }

    public void showHand() {
        System.out.println(name + "'s hand: ");
        for (var card : playerHand.cards) {
            System.out.print("|" + card + "|   ");
        }
    }
}
