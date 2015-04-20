package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * The main activity of the program.
 * As of 17/04/2015, it needs to be refactored heavily.
 */
public class MainActivity extends ActionBarActivity {

    private MediaPlayer mp;
    private Handler handler;
    private double timeElapsed = 0;
    private double finalTime;
    private int forwardTime = 2000;
    private int backwardTime = 2000;
    private SeekBar sk;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageButton rewButton;
    private ImageButton ffButton;
    private ImageButton previousButton;
    private ImageButton nextButton;
    public TextView songTitle;
    public TextView songDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAudioFile();

        handler = new Handler();

        sk=(SeekBar) findViewById(R.id.bar);
        sk.setClickable(true);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
//                TODO

            }
        });

        playButton = (ImageButton) findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play(v);

                Toast.makeText(getApplicationContext(),
                        "Playing", Toast.LENGTH_SHORT).show();
            }
        });

        pauseButton = (ImageButton) findViewById(R.id.buttonPause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pause(v);

                Toast.makeText(getApplicationContext(),
                        "Paused", Toast.LENGTH_SHORT).show();
            }
        });

        rewButton = (ImageButton) findViewById(R.id.buttonRew);
        rewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rewind(v);

                Toast.makeText(getApplicationContext(),
                        "Rewinding", Toast.LENGTH_SHORT).show();

            }
        });

        ffButton = (ImageButton) findViewById(R.id.buttonFf);
        ffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward(v);

                Toast.makeText(getApplicationContext(),
                        "Forwarding", Toast.LENGTH_SHORT).show();
            }
        });

        previousButton = (ImageButton) findViewById(R.id.buttonPrevious);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous(v);

                Toast.makeText(getApplicationContext(),
                        "Previous", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton = (ImageButton) findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(v);

                Toast.makeText(getApplicationContext(),
                        "Next", Toast.LENGTH_SHORT).show();
            }
        });

        songTitle = (TextView) findViewById(R.id.songTitle);
        songTitle.setText("test_mp3_0001.mp3");

        songDuration = (TextView) findViewById(R.id.songDuration);
        songDuration.setText("Song duration");

    }

    public void loadAudioFile() {
        mp = MediaPlayer.create(this, R.raw.test_mp3_0001);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * meccanismo di aggiornamento della barra di progresso. Il suo avanzamento rappresenta
     * l’andamento della riproduzione. Si è usato un handler temporizzato che legge ogni 100 millisecondi
     * la posizione attuale di riproduzione, ed attui il conseguente aggiornamento della barra.
     * Il valore massimo cui il progresso può arrivare è la durata totale del brano, e viene
     * impostato all’interno del metodo play utilizzando come fonte di informazione il risultato
     * del metodo getDuration del Mediaplayer.
     */
    private Runnable updateBar = new Runnable() {
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void run()
        {
            timeElapsed = mp.getCurrentPosition();
            sk.setProgress((int) timeElapsed);

            finalTime = mp.getDuration();
            double timeRemaining = finalTime - timeElapsed;

            // il metodo toMinutes richiede una API 9 minima, bisognerebbe cambiare perché attualmente
            // la minima è la 8 per noi, funziona ugualmente essendo stato annotato (seguendo il suggerimento
            // di AS
            songDuration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            handler.postDelayed(this, 100);
        }
    };

    public void play(View v)
    {
        //mp.setLooping(true); // e' fondamentale?
        mp.start();
        sk.setMax(mp.getDuration());
        timeElapsed = mp.getCurrentPosition();
        sk.setProgress((int) timeElapsed);
        handler.postDelayed(updateBar,100);
    }

    public void pause(View v)
    {
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

        playButton.setClickable(false);
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