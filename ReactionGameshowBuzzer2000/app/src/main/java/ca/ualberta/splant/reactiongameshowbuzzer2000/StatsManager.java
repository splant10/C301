package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by splant on 10/2/15.
 */
public class StatsManager extends Activity {

    private ArrayList<Player> players = new ArrayList<Player>();

    private static final String FILENAME = "file.sav";
    private ArrayAdapter<Player> adapter;

    private long min10, min100, minAll;
    private long max10, max100, maxAll;
    private long avg10, avg100, avgAll;
    private long med10, med100, medAll;

    public StatsManager() {}

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            //players = new ArrayList<Player>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(players, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearStats(int i) {
        // i == 0: clear reaction stats
        if (i == 0) {
            for (int j = 0; j < players.size(); ++j) {
                players.get(j).setReactionTime(0);
                players.get(j).clearReacTimes();
            }
        } else { // i == 1: clear buzzer stats
            for (int j = 0; j < players.size(); ++i) {
                players.get(j).clearBuzzClicks();
            }
        }
    }

    // Get the last i minimum reaction times; if i = 0, get all
    // Get the last i average reaction times; if i = 0, get all
    // Get the last i median  reaction times; if i = 0, get all
    public void getReacStats(int i) {
        long min = 999999;
        long max = -1;
        long avg = 0;
        int count = 0;
        ArrayList<Long> tempArray = new ArrayList<Long>();
        for (int j = 0; j < players.size(); ++j) {
            if (players.get(j).getType() == 0) { // if current player evaluated is a reaction player
                if (i != 0) {
                    for (int m = 0; m < i; ++m) {
                        long val = players.get(j).getReacTimes().get(players.get(j).getReacTimes().size() - m);
                        tempArray.add(val);
                        if (val < min) {
                            min = val;
                        }
                        if (val > max) {
                            max = val;
                        }
                        avg += val;
                    }
                } else {
                    for (int m = 0; m < players.get(j).getReacTimes().size(); ++m) {
                        long val = players.get(j).getReacTimes().get(m);
                        tempArray.add(val);
                        if (val < min) {
                            min = val;
                        }
                        if (val > max) {
                            max = val;
                        }
                        avg += val;
                        ++count;
                    }
                }
                avg = avg/i;
                Collections.sort(tempArray);
                if (i == 10) {
                    this.min10 = min;
                    this.max10 = max;
                    this.avg10 = avg;
                    this.med10 = tempArray.get(4);
                } else if (i == 100) {
                    this.min100 = min;
                    this.max100 = max;
                    this.avg100 = avg;
                    this.med100 = tempArray.get(49);
                } else { // Getting all
                    this.minAll = min;
                    this.maxAll = max;
                    this.avgAll = avg;
                    if (count == 1) { // corner case
                        this.medAll = tempArray.get(0);
                    } else if (count % 2 == 0) { // even array length
                        long upper = tempArray.get(count/2);
                        long lower = tempArray.get(count/2 - 1);
                        this.medAll = (upper+lower)/2;
                    } else { // Odd array length
                        this.medAll = tempArray.get(count/2);
                    }
                }
            }
        }
    }

    public void addPlayer(Player p) {
        players.add(p);
    }
}
