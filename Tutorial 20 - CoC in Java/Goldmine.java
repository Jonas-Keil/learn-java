package BaseBuilder;

public class Goldmine {
    private int level;
    private int goldGespeichert = 0;

    public Goldmine(int level) {
        this.level = level;
    }

    public void produziereGold() {
        goldGespeichert += 400 * level;
    }

    public int gebeGold() {
        return goldGespeichert;
    }

    public void leereMine() {
        this.goldGespeichert = 0;
    }
}
