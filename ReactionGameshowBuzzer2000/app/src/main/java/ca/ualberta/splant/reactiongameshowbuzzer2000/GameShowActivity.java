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

public class GameShowActivity extends MainScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void twoPlayerClick(View view) {
        Intent intent= new Intent(this,TwoPlayerBuzzer.class);
        startActivity(intent);
    }

    public void threePlayerClick(View view) {
        Intent intent= new Intent(this,ThreePlayerBuzzer.class);
        startActivity(intent);
    }

    public void fourPlayerClick(View view) {
        Intent intent= new Intent(this,FourPlayerBuzzer.class);
        startActivity(intent);
    }

    // Alert function to display who buzzed first
    // Source: David Hedlund @ stackoverflow:
    // http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    public void alertWhoBuzzed(String name, int count) {
        new AlertDialog.Builder(this)
                .setMessage(name + " was the first to buzz! That's " + count + "clicks for " + name + "!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue playing game
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // return to main screen
                        finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void player1Click() {
        this.player1.incrementBuzzClicks();
        // Alert that player1 buzzed first
        alertWhoBuzzed(player1.getName(),player1.getBuzzClicks());
    }

    public void player2Click() {
        this.player2.incrementBuzzClicks();
        // Alert that player2 buzzed first
        alertWhoBuzzed(player2.getName(),player2.getBuzzClicks());
    }

    public void player3Click() {
        this.player3.incrementBuzzClicks();
        // Alert that player3 buzzed first
        alertWhoBuzzed(player3.getName(),player3.getBuzzClicks());
    }

    public void player4Click() {
        this.player4.incrementBuzzClicks();
        // Alert that player4 buzzed first
        alertWhoBuzzed(player4.getName(),player4.getBuzzClicks());
    }

}
