package ca.ualberta.splant.reactiongameshowbuzzer2000;

import java.util.ArrayList;

/**
 * Created by splant on 10/2/15.
 */
public class PlayerList {
    private ArrayList<Player> players = new ArrayList<Player>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public ArrayList<Player> getplayers() {
        return players;
    }

    public int getCount() {
        return players.size();
    }
}

