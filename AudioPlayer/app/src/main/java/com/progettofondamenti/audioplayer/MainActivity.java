package com.progettofondamenti.audioplayer;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView songDuration;

    private BarUpdater barUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Default setup and initialization of activity_main.xml */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializes the player given the context of this activity */
		player = new PlayerModel(this.getApplicationContext());

        initializeXmlComponents();

        /* Initializes barUpdater, which is a Runnable */
        barUpdater = new BarUpdater(player, handler, sk, songDuration);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // stops handler in order to act on the seekbar
                handler.removeCallbacks(barUpdater);

                    if (fromUser)
                        player.seek(progress);

                // restarts handler
                handler.postDelayed(barUpdater,100);

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


				Toast.makeText(getApplicationContext(),
                        "Playing", Toast.LENGTH_SHORT).show();
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                player.pause();

                Toast.makeText(getApplicationContext(),
                        "Paused", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(getApplicationContext(),
                        "Previous", Toast.LENGTH_SHORT).show();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.next();

                Toast.makeText(getApplicationContext(),
                        "Next", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
    Initializes all the XML components of the application
     */
    private void initializeXmlComponents() {
        songDuration = (TextView) findViewById(R.id.songDuration);
        songDuration.setText("Song duration");

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

    @Override
    public void onPause() {
		super.onPause();

        player.pause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // TODO: Release resource from the player
    }

}