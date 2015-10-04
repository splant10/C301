package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainScreen extends Activity {

    public StatsManager mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mm = new StatsManager();
    }

    public void reactionButtonClick(View view) {
        Intent intentReaction= new Intent(this,ReactionActivity.class);
        startActivity(intentReaction);
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

    public void givePlayer(Player p) {
        this.mm.addPlayer(p);
    }

}
