package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// This class manages the screen in between choosing the gameshow activity on MainScreen,
// and choosing which type of gameshow activity to go to.
public class GameShowActivity extends MainScreen {

    public Player player1 = new Player("Player 1", 1);
    public Player player2 = new Player("Player 2", 1);
    public Player player3 = new Player("Player 3", 1);
    public Player player4 = new Player("Player 4", 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.givePlayer(player1);
        super.givePlayer(player2);
        super.givePlayer(player3);
        super.givePlayer(player4);
        setContentView(R.layout.gameshow_buzzer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_show, menu);
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

    public void twoPlayerGameClick(View view) {
        Intent intent= new Intent(this,TwoPlayerBuzzer.class);
        startActivity(intent);
    }

    public void threePlayerGameClick(View view) {
        Intent intent= new Intent(this,ThreePlayerBuzzer.class);
        startActivity(intent);
    }

    public void fourPlayerGameClick(View view) {
        Intent intent= new Intent(this,FourPlayerBuzzer.class);
        startActivity(intent);
    }

    // Alert function to display who buzzed first
    // Source: David Hedlund @ stackoverflow:
    // http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    public void alertWhoBuzzed(String name, int count) {
        new AlertDialog.Builder(this)
                .setMessage(name + " was the first to buzz! That's " + count + " clicks for " + name + "!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue playing game
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
