package ca.ualberta.splant.reactiongameshowbuzzer2000;

/**
 * Created by splant on 10/2/15.
 */
public class Player {

    private int buzzClicks;
    private int reactionTime;
    private String name;

    public Player (String name) {
        buzzClicks = 0;
        this.name = name;
    }

    public int getBuzzClicks() {
        return buzzClicks;
    }

    public void incrementBuzzClicks() { this.buzzClicks += 1; }

    public void clearBuzzClicks() { this.buzzClicks = 0; }

    public int getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(int reactionTime) {
        this.reactionTime = reactionTime;
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return this;
    }
}
