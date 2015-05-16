package com.progettofondamenti.audioplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.progettofondamenti.audioplayer.buttons.PauseButton;
import com.progettofondamenti.audioplayer.buttons.PlayButton;
import com.progettofondamenti.audioplayer.listeners.PauseListener;
import com.progettofondamenti.audioplayer.listeners.PlayListener;

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

    private static SeekBar sk;
	private static PlayButton playButton;
    private static PauseButton pauseButton;
    private static ImageButton rewButton;
    private static ImageButton ffButton;
    private static ImageButton previousButton;
    private static ImageButton nextButton;
    private static TextView songTitle;
    private static TextView elapsedTime;
    private static TextView remainingTime;

    private static LinearLayout layout;

    private BarUpdater barUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default setup and initialization of activity_main.xml */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializes the player given the context of this activity */
		player = new PlayerModel(this.getApplicationContext());

        initializeXmlComponents();

//        showUserSettings();

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


		playButton.setOnClickListener(new PlayListener(player, sk, handler, barUpdater));


        pauseButton.setOnClickListener(new PauseListener(player, sk, handler, barUpdater));


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

        // TODO: Make a dedicated class to every button as we did for PlayButton ?
		playButton = (PlayButton) findViewById(R.id.buttonPlay);
        pauseButton = (PauseButton) findViewById(R.id.buttonPause);
        rewButton = (ImageButton) findViewById(R.id.buttonRew);
        ffButton = (ImageButton) findViewById(R.id.buttonFf);
        previousButton = (ImageButton) findViewById(R.id.buttonPrevious);
        nextButton = (ImageButton) findViewById(R.id.buttonNext);
    }

    /**
     * sets the color of the LinearLayout given a color string
     * @param color stringa che descrive il colore
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

    @Override
    protected void onResume() {
        super.onResume();

//        showUserSettings();
    }

    /**
     * method which retrives informations from choices of the user
     */
    private void showUserSettings() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String color = sharedPreferences.getString("pref_color", "");

        layout.setBackgroundColor(Color.parseColor(color));
    }
}
