package ca.ualberta.splant.reactiongameshowbuzzer2000;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class ReactionActivity extends MainScreen {

    public Player player1 = new Player("Player 1", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Provide StatsManager with player1
        super.givePlayer(player1);
        setContentView(R.layout.reaction_timer);

        new AlertDialog.Builder(this)
                .setMessage(("Click the button below the box once the box changes color! \n" +
                        "Be sure to not click it too early!"))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with reaction timer; start countdown
                        CountDown();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction, menu);
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

    // Make a countdown that ranges from 10ms to 2000ms. Upon finishing countdown,
    // change the reactionImageView to be green
    public void CountDown() {
        final ImageView reactionImageView = (ImageView)findViewById(R.id.reactionImageView);
        Random r = new Random();
        int timeDelay = r.nextInt(2000 - 10) + 10;
        new CountDownTimer(timeDelay, 10) {

            public void onTick(long millisUntilFinished) {
                View.OnClickListener handler1 = new View.OnClickListener() {
                    public void onClick(View v) {
                        reactionImageView.setImageResource(R.drawable.redbox);
                        cancel();
                        alertOutcome(0, 0);
                    }
                };
                Button reactButton = (Button)findViewById(R.id.reactionButton);
                reactButton.setOnClickListener(handler1);
            }
            public void onFinish() {
                reactionImageView.setImageResource(R.drawable.greenbox);
                // make new system time
                final long startTime = System.currentTimeMillis();
                View.OnClickListener handler1 = new View.OnClickListener() {
                    public void onClick(View v) {
                        long endTime = System.currentTimeMillis();
                        long diff = endTime-startTime;
                        player1.setReactionTime(diff);
                        // save();
                        alertOutcome(1, diff);
                    }
                };
                Button reactButton = (Button)findViewById(R.id.reactionButton);
                reactButton.setOnClickListener(handler1);
            }
        }.start();
    }

    public void alertOutcome(int outcome, long time) {
        final ImageView reactionImageView = (ImageView)findViewById(R.id.reactionImageView);
        switch (outcome) {
            case 0:
                // Alert to a failure
                new AlertDialog.Builder(this)
                        .setMessage(("You clicked too early!"))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with reaction timer; start countdown
                                reactionImageView.setImageResource(R.drawable.greybox);
                                CountDown();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
            case 1:
                // Alert success, and time
                new AlertDialog.Builder(this)
                        .setMessage(("You reacted with a time of " + time + "ms"))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with reaction timer; start countdown
                                reactionImageView.setImageResource(R.drawable.greybox);
                                CountDown();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
    }

    // should access the StatsManager object in the main activity, and save.
    void save() {
        super.mm.saveInFile();
    }
}
