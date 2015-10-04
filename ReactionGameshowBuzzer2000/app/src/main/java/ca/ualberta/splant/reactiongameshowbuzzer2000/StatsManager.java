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

/**
 * Created by splant on 10/2/15.
 */
public class StatsManager extends Activity {

    private ArrayList<Player> players = new ArrayList<Player>();

    private static final String FILENAME = "file.sav";
    private ArrayAdapter<Player> adapter;

    private int min10, min100, minAll;
    private int max10, max100, maxAll;
    private int avg10, avg100, avgAll;
    private int med10, med100, medAll;

    public StatsManager() {

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<Player>>() {}.getType();
            //players = gson.fromJson(in, listType);

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
           // gson.toJson(players, writer);
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

        }
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }
}
