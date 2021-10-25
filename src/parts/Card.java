package parts;

public class Card {
    public byte faceValue;
    public String type;
    public String color;

    public Card(byte faceValue, String type, String color) {
        this.faceValue = faceValue;
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        return switch (type) {
            case "number" -> faceValue + " " + color;
            case "WILD", "WILD DRAW 4" -> type;
            default -> type + " " + color;
        };
    }
}
