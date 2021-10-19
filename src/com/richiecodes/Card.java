package com.richiecodes;

public class Card {
    byte faceValue;
    String type;
    String color;

    public Card(byte faceValue, String type, String color) {
        this.faceValue = faceValue;
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        return switch (type) {
            case "number" -> faceValue + " " + color;
            case "WILD", "WILD DRAW 4", "BLANK" -> type;
            default -> type + " " + color;
        };
    }
}
