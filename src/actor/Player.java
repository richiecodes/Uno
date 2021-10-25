package actor;

public class Player implements Actor {

    public Hand playerHand;
    private String name = "";
    private int score = 0;

    public Player(String name) {
        this.name = name;
        playerHand = new Hand();
    }

    public void showHand() {
        System.out.println(name.substring(0, 1).toUpperCase() + name.substring(1) + "'s hand: ");
        int cardNumber = 1;
        for (var card : playerHand.cards) {
            System.out.print(cardNumber + ". |" + card + "|  ");
            cardNumber++;
        }
        System.out.println();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
