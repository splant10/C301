package ca.ualberta.splant.reactiongameshowbuzzer2000;

import java.util.ArrayList;

/**
 * Created by splant on 10/2/15.
 *
 * The Player class is a class that represents the player or players as objects.
 * Each player has a reactionTime, a name, a state which says which type of game they are
 * currently in, the amount of buzzclicks for each type of gameshow activity (2P, 3P or 4P),
 * as well as an ArrayList of reaction times. The data that the player contains is accessed
 * by the StatsManager object created in the main activity.
 */
public class Player {

    private int buzzClicks;
    private int type; // reaction or buzzer player
    private long reactionTime;
    private String name;
    private int state; // 0: in two player game; 1: in three player game; 2: in four player game
    private int twoPlayerClicks, threePlayerClicks, fourPlayerClicks;
    private static ArrayList<Long> reacTimes = new ArrayList<Long>();

    public Player (String name, int type) {
        this.buzzClicks = 0;
        this.name = name;
        if (type == 0) {
            this.type = 0; // Reaction timer player
        } else {
            this.type = 1; // Buzz Click player
        }
    }

    public int getBuzzClicks() {
        return buzzClicks;
    }

    public void incrementBuzzClicks() {
        switch (state) {
            case 0:
                this.twoPlayerClicks += 1;
                break;
            case 1:
                this.threePlayerClicks += 1;
                break;
            case 2:
                this.fourPlayerClicks += 1;
                break;
        }
        this.buzzClicks += 1;
    }

    public void clearBuzzClicks() {
        this.buzzClicks = 0;
        this.twoPlayerClicks = 0;
        this.threePlayerClicks = 0;
        this.fourPlayerClicks = 0;
    }

    public void clearReacTimes() {
        reacTimes.clear();
    }

    // in case the single most recent reaction time needs to be accessed.
    // Currently not used.
    public long getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(long reactionTime) {
        this.reactionTime = reactionTime;
        // add reactionTime to reacTimes array
        reacTimes.add(this.reactionTime);
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return this;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTwoPlayerClicks() {
        return twoPlayerClicks;
    }

    public int getThreePlayerClicks() {
        return threePlayerClicks;
    }

    public int getFourPlayerClicks() {
        return fourPlayerClicks;
    }

    public ArrayList<Long> getReacTimes() {
        return reacTimes;
    }

    public int getType() {
        return type;
    }
}
