package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


// This class manages the Statistics Activity screen, by accessing the
// StatisticsManager object created in the superclass MainScreen
public class StatisticsActivity extends MainScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        // super.mm.loadFromFile();

        // Manage the tabs on the Stats page.
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("ReactionStats");
        tabSpec.setContent(R.id.tabReactionStats);
        tabSpec.setIndicator("Reaction Stats");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("BuzzerStats");
        tabSpec.setContent(R.id.tabGameshowBuzzStats);
        tabSpec.setIndicator("Gameshow Buzzer Stats");
        tabHost.addTab(tabSpec);

        // Calculate stats
        super.mm.getReacStats(10);  // Compute reaction stats for previous 10 entries
        super.mm.getReacStats(100); // Compute reaction stats for previous 100 entries
        super.mm.getReacStats(0);   // Compute reaction stats for all entries

        // Update reaction stats.
        TextView text = (TextView)findViewById(R.id.last10Min);
        text.setText(String.valueOf(super.mm.getMin10()));
        text = (TextView)findViewById(R.id.last100Min);
        text.setText(String.valueOf(super.mm.getMin100()));
        text = (TextView)findViewById(R.id.allMin);
        text.setText(String.valueOf(super.mm.getMinAll()));

        text = (TextView)findViewById(R.id.last10Max);
        text.setText(String.valueOf(super.mm.getMax10()));
        text = (TextView)findViewById(R.id.last100Max);
        text.setText(String.valueOf(super.mm.getMax100()));
        text = (TextView)findViewById(R.id.allMax);
        text.setText(String.valueOf(super.mm.getMaxAll()));

        text = (TextView)findViewById(R.id.last10Avg);
        text.setText(String.valueOf(super.mm.getAvg10()));
        text = (TextView)findViewById(R.id.last100Avg);
        text.setText(String.valueOf(super.mm.getAvg100()));
        text = (TextView)findViewById(R.id.allAvg);
        text.setText(String.valueOf(super.mm.getAvgAll()));

        text = (TextView)findViewById(R.id.last10Med);
        text.setText(String.valueOf(super.mm.getMed10()));
        text = (TextView)findViewById(R.id.last100Med);
        text.setText(String.valueOf(super.mm.getMed100()));
        text = (TextView)findViewById(R.id.allMed);
        text.setText(String.valueOf(super.mm.getMedAll()));

        // Update Buzzer Stats:
        text = (TextView)findViewById(R.id.twoPlayerP1);
        text.setText(String.valueOf(super.mm.getTwoPlayerP1()));
        text = (TextView)findViewById(R.id.twoPlayerP2);
        text.setText(String.valueOf(super.mm.getTwoPlayerP2()));

        text = (TextView)findViewById(R.id.threePlayerP1);
        text.setText(String.valueOf(super.mm.getThreePlayerP1()));
        text = (TextView)findViewById(R.id.threePlayerP2);
        text.setText(String.valueOf(super.mm.getThreePlayerP2()));
        text = (TextView)findViewById(R.id.threePlayerP3);
        text.setText(String.valueOf(super.mm.getThreePlayerP3()));

        text = (TextView)findViewById(R.id.fourPlayerP1);
        text.setText(String.valueOf(super.mm.getFourPlayerP1()));
        text = (TextView)findViewById(R.id.fourPlayerP2);
        text.setText(String.valueOf(super.mm.getFourPlayerP2()));
        text = (TextView)findViewById(R.id.fourPlayerP3);
        text.setText(String.valueOf(super.mm.getFourPlayerP3()));
        text = (TextView)findViewById(R.id.fourPlayerP4);
        text.setText(String.valueOf(super.mm.getFourPlayerP4()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // on click of clear stats button on reaction stats page:
    public void clearReacDataClick(View view) {
        // clear reaction stats
        super.mm.clearStats(0);

        TextView text = (TextView)findViewById(R.id.last10Min);
        text.setText("0");
        text = (TextView)findViewById(R.id.last100Min);
        text.setText("0");
        text = (TextView)findViewById(R.id.allMin);
        text.setText("0");

        text = (TextView)findViewById(R.id.last10Max);
        text.setText("0");
        text = (TextView)findViewById(R.id.last100Max);
        text.setText("0");
        text = (TextView)findViewById(R.id.allMax);
        text.setText("0");

        text = (TextView)findViewById(R.id.last10Avg);
        text.setText("0");
        text = (TextView)findViewById(R.id.last100Avg);
        text.setText("0");
        text = (TextView)findViewById(R.id.allAvg);
        text.setText("0");

        text = (TextView)findViewById(R.id.last10Med);
        text.setText("0");
        text = (TextView)findViewById(R.id.last100Med);
        text.setText("0");
        text = (TextView)findViewById(R.id.allMed);
        text.setText("0");

    }

    // on click of clear stats button on buzzer stats page:
    public void clearBuzzDataClick(View view) {
        super.mm.clearStats(1);
        // Buzzer Stats:
        TextView text = (TextView)findViewById(R.id.twoPlayerP1);
        text.setText("0");
        text = (TextView)findViewById(R.id.twoPlayerP2);
        text.setText("0");

        text = (TextView)findViewById(R.id.threePlayerP1);
        text.setText("0");
        text = (TextView)findViewById(R.id.threePlayerP2);
        text.setText("0");
        text = (TextView)findViewById(R.id.threePlayerP3);
        text.setText("0");

        text = (TextView)findViewById(R.id.fourPlayerP1);
        text.setText("0");
        text = (TextView)findViewById(R.id.fourPlayerP2);
        text.setText("0");
        text = (TextView)findViewById(R.id.fourPlayerP3);
        text.setText("0");
        text = (TextView)findViewById(R.id.fourPlayerP4);
        text.setText("0");
    }
}
