package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.media.MediaPlayer;
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

    /* Defining constants */
    private static final int forwardTime = 2000;
    private static final int backwardTime = 2000;

    /* Declarations */
    private MediaPlayer mp;
    private Handler handler = new Handler();
    private double timeElapsed = 0;
    private double finalTime;

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

        /* Initializes mp given this context */
        initializeMediaPlayer(this);

        initializeXmlComponents();

        /* Initializes barUpdater, which is a Runnable */
        barUpdater = new BarUpdater(mp, handler, sk, songDuration);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // stops handler in order to act on the seekbar
                handler.removeCallbacks(barUpdater);

                    if (fromUser)
                        mp.seekTo(progress);

                // restarts handler
                handler.postDelayed(barUpdater,100);

            }

            /**
             *
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no implementation  needed!!
            }

            /**
             *
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no implementation  needed!!
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play();

                Toast.makeText(getApplicationContext(),
                        "Playing", Toast.LENGTH_SHORT).show();
            }
        });


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pause();

                Toast.makeText(getApplicationContext(),
                        "Paused", Toast.LENGTH_SHORT).show();
            }
        });


        rewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rewind(v);
            }
        });


        ffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward(v);
            }
        });


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous(v);
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);
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

    /**
     * Initializes a MediaPlayer with a specific mp3 file
     * @param context Represents the context of the activity
     */
    public void initializeMediaPlayer(Context context) {
        mp = MediaPlayer.create(context, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
    }

    public void play() {
            mp.start();

            timeElapsed = mp.getCurrentPosition();
            sk.setProgress((int) timeElapsed);
            sk.setMax(100);
            handler.postDelayed(barUpdater,100);

    }

    public void pause() {
        mp.pause();

    }

    // go backwards at backwardTime seconds
    public void rewind(View view) {
        //check if we can go back at backwardTime seconds after song starts
        if ((timeElapsed - backwardTime) > 0) {
            timeElapsed = timeElapsed - backwardTime;

            //seek to the exact second of the track
            mp.seekTo((int) timeElapsed);
        }
    }

    // go forward at forwardTime seconds
    public void forward(View view) {
        //check if we can go forward at forwardTime seconds before song end
        if ((timeElapsed + forwardTime) <= finalTime) {
            timeElapsed = timeElapsed + forwardTime;

            //seek to the exact second of the track
            mp.seekTo((int) timeElapsed);
        }
    }

    //go previous song
    public void previous(View view) {
        //torna all'inizio della canzone e riparte settando anche la seek bar
        mp.seekTo(0);
        sk.setProgress(0);

        playButton.setClickable(true);
    }

    //go next song
    public void next(View view) {
        //va alla fine della canzone e riparte premendo play
        finalTime=mp.getDuration();
        mp.seekTo((int) finalTime);
        sk.setProgress((int) finalTime);

    }

    @Override
    public void onPause() {

        super.onPause();

        mp.pause();
        timeElapsed = mp.getCurrentPosition();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.release();
    }

}