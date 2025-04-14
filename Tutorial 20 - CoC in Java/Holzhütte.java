package BaseBuilder;

public class Holzhütte {
    private int level;
    private int holzGespeichert = 0;

    public Holzhütte(int level) {
        this.level = level;
    }

    public void produziereHolz() {
        holzGespeichert += 100 * level;
    }
    public int gebeHolz() {
        return holzGespeichert;
    }
    public void leereHütte() {
        this.holzGespeichert = 0;
    }
}
