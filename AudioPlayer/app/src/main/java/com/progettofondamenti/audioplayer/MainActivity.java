package com.progettofondamenti.audioplayer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * The main activity of the program.
 * As of 24/04/2015, it needs to be refactored heavily.
 */
public class MainActivity extends ActionBarActivity {

    /* Declarations */
    private iPlayer player;
    private Handler handler = new Handler();
    private double timeElapsed = 0;

    private SeekBar sk;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageButton rewButton;
    private ImageButton ffButton;
    private ImageButton previousButton;
    private ImageButton nextButton;
    private TextView songTitle;
    private TextView elapsedTime;
    private TextView remainingTime;

    private static LinearLayout layout;

    private BarUpdater barUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default setup and initialization of activity_main.xml */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        /* Initializes the player given the context of this activity */
		player = new PlayerModel(this.getApplicationContext());

        initializeXmlComponents();

        /* Initializes barUpdater, which is a Runnable */
        barUpdater = new BarUpdater(player, handler, sk, elapsedTime, remainingTime);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // stops handler in order to act on the seekbar
                handler.removeCallbacks(barUpdater);

                if (fromUser)
                    player.seek(progress);

                // restarts handler
                handler.postDelayed(barUpdater, 100);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no implementation  needed!!
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no implementation  needed!!
            }
        });


        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.play();

				timeElapsed = player.getPlayerPosition();
				sk.setProgress((int) timeElapsed);
				sk.setMax(100);
				handler.postDelayed(barUpdater,100);
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.pause();
            }
        });


        rewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.rewind();
            }
        });


        ffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.forward();
            }
        });


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.previous();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.next();
            }
        });
    }


    /*
     * Initializes the application's XML components
     */
    private void initializeXmlComponents() {

        layout = (LinearLayout) findViewById(R.id.container);

        elapsedTime = (TextView) findViewById(R.id.elapsedTime);
        elapsedTime.setText("0 min, 0 sec");

        remainingTime = (TextView) findViewById(R.id.remainingTime);
        remainingTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) player.getTotalDuration()),
                TimeUnit.MILLISECONDS.toSeconds((long) player.getTotalDuration())
                    - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) player.getTotalDuration()))));

        songTitle = (TextView) findViewById(R.id.songTitle);
        songTitle.setText("W.A.Mozart - Concerto No.21 - Andante");

        sk=(SeekBar) findViewById(R.id.bar);
        sk.setClickable(true);

        // TODO: How can we get a PlayButton to represent an ImageButton in the XML even though it would need a constructor? (CL)
		playButton = (ImageButton) findViewById(R.id.buttonPlay);
        pauseButton = (ImageButton) findViewById(R.id.buttonPause);
        rewButton = (ImageButton) findViewById(R.id.buttonRew);
        ffButton = (ImageButton) findViewById(R.id.buttonFf);
        previousButton = (ImageButton) findViewById(R.id.buttonPrevious);
        nextButton = (ImageButton) findViewById(R.id.buttonNext);
    }

    /**
     * sets the color of the LinearLayout given a color string
     * @param color
     */
    public static void setBGColor(String color){
        layout.setBackgroundColor(Color.parseColor(color));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            // creates and Intent in order to call the SettingActivity
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
		super.onPause();

        player.pause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        player.die();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        player.pause();
    }

}
