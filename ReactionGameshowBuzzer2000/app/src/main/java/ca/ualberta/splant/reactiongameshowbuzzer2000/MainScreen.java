package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    public Player player1 = new Player("Player 1");
    public Player player2 = new Player("Player 2");
    public Player player3 = new Player("Player 3");
    public Player player4 = new Player("Player 4");

    List<Player> Players = new ArrayList<Player>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    public void reactionButtonClick(View view) {
        Intent intent= new Intent(this,ReactionActivity.class);
        startActivity(intent);
    }

    public void gameshowButtonClick(View view) {
        Intent intent= new Intent(this,GameShowActivity.class);
        startActivity(intent);
    }

    public void statsButtonClick(View view) {
        Intent intent= new Intent(this,StatisticsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
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
}
