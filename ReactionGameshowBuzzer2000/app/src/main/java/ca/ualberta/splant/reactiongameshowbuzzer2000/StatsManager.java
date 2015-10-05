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

    public static ArrayList<Player> players = new ArrayList<Player>();
    private int playersSize = players.size();

    private static final String FILENAME = "file.sav";

    private long min10, min100, minAll;
    private long max10, max100, maxAll;
    private long avg10, avg100, avgAll;
    private long med10, med100, medAll;

    private int twoPlayerP1,   twoPlayerP2,
                threePlayerP1, threePlayerP2, threePlayerP3,
                fourPlayerP1,  fourPlayerP2,  fourPlayerP3,  fourPlayerP4;

    public StatsManager() {}

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
            players = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            players = new ArrayList<Player>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveInFile() {
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

    public void getBuzzerStats() {
        for (int i=0; i < players.size(); ++i) {
            if (players.get(i).getType() == 1) {
                switch (i) {
                    case 1:
                        this.twoPlayerP1 = players.get(i).getTwoPlayerClicks();
                        this.threePlayerP1 = players.get(i).getThreePlayerClicks();
                        this.fourPlayerP1 = players.get(i).getFourPlayerClicks();
                        break;
                    case 2:
                        this.twoPlayerP2 = players.get(i).getTwoPlayerClicks();
                        this.threePlayerP2 = players.get(i).getThreePlayerClicks();
                        this.fourPlayerP2 = players.get(i).getFourPlayerClicks();
                        break;
                    case 3:
                        this.threePlayerP3 = players.get(i).getThreePlayerClicks();
                        this.fourPlayerP3 = players.get(i).getFourPlayerClicks();
                        break;
                    case 4:
                        this.fourPlayerP4 = players.get(i).getFourPlayerClicks();
                        break;
                }
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
                    for (int m = 0; (m < i) && (m < players.get(j).getReacTimes().size()); ++m) {
                        long val = players.get(j).getReacTimes().get(players.get(j).getReacTimes().size()-m-1);
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

                Collections.sort(tempArray);
                if (i == 10) {
                    this.min10 = min;
                    this.max10 = max;
                    if (tempArray.size() >= 10) {
                        this.med10 = tempArray.get(4);
                        this.avg10 = avg/10;
                    } else {
                        this.med10 = tempArray.get(count/2);
                        this.avg10 = avg/count;
                    }

                } else if (i == 100) {
                    this.min100 = min;
                    this.max100 = max;
                    if (tempArray.size() >= 100) {
                        this.med100 = tempArray.get(49);
                        this.avg100 = avg/100;
                    } else {
                        this.med100 = tempArray.get(count/2);
                        this.avg100 = avg/count;
                    }

                } else { // Getting all
                    this.minAll = min;
                    this.maxAll = max;
                    this.avgAll = avg/count;
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
        this.players.add(p);
    }

    public long getMin10() {
        return min10;
    }

    public long getMin100() {
        return min100;
    }

    public long getMinAll() {
        return minAll;
    }

    public long getMax10() {
        return max10;
    }

    public long getMax100() {
        return max100;
    }

    public long getMaxAll() {
        return maxAll;
    }

    public long getAvg10() {
        return avg10;
    }

    public long getAvg100() {
        return avg100;
    }

    public long getAvgAll() {
        return avgAll;
    }

    public long getMed10() {
        return med10;
    }

    public long getMed100() {
        return med100;
    }

    public long getMedAll() {
        return medAll;
    }

    public int getTwoPlayerP1() {
        return twoPlayerP1;
    }

    public int getTwoPlayerP2() {
        return twoPlayerP2;
    }

    public int getThreePlayerP1() {
        return threePlayerP1;
    }

    public int getThreePlayerP2() {
        return threePlayerP2;
    }

    public int getThreePlayerP3() {
        return threePlayerP3;
    }

    public int getFourPlayerP1() {
        return fourPlayerP1;
    }

    public int getFourPlayerP2() {
        return fourPlayerP2;
    }

    public int getFourPlayerP3() {
        return fourPlayerP3;
    }

    public int getFourPlayerP4() {
        return fourPlayerP4;
    }
}
