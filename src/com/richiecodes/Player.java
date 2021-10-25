package com.richiecodes;

public class Player {

    public Hand playerHand;
    private String name = "";
    public int score = 0;

    public Player(String name) {
        this.name = name;
        playerHand = new Hand();
    }

    public void showHand() {
        System.out.println(name + "'s hand: ");
        int cardNumber = 1;
        for (var card : playerHand.cards) {
            System.out.print(cardNumber + ". |[ " + card + " ]|\t");
            cardNumber++;
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return name;
    }
}
