package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by splant on 10/2/15.
 */
public class MeasurementManager {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldTweetsList;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayAdapter<Player> adapter;

    public MeasurementManager() {

    }
}
