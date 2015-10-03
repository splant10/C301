package ca.ualberta.splant.reactiongameshowbuzzer2000;

/**
 * Created by splant on 10/2/15.
 */
public class Player {

    private int _buzzClicks;
    private String name;


    public Player () {
        _buzzClicks = 0;
    }

    public int getBuzzClicks() {
        return _buzzClicks;
    }

    public void setBuzzClicks(int clicks) {
        this._buzzClicks = clicks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
