package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * The main activity of the program.
 * As of 17/04/2015, it needs to be refactored heavily.
 */
public class MainActivity extends Activity {

    private static final int DEFAULT_START_TIME = 0;

    private MediaPlayer mp;
    private Handler handler;
    private double startTime;
    private double finalTime;
    private SeekBar sk;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private Button stopButton;
    private Button loadButton;
    public TextView songTitle;
    public TextView songDuration;

    /**
     * Default constructor. Initialize the execution variables
     * NOT NEEDED IN AN ACTIVITY!! -> WILL BE REMOVED SOON
     */
    public MainActivity() {
        startTime = DEFAULT_START_TIME;
        handler = new Handler();
        mp = null;
        sk=null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sk=(SeekBar) findViewById(R.id.bar);

        playButton = (ImageButton) findViewById(R.id.buttonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                play(v);
            }
        });

        pauseButton = (ImageButton) findViewById(R.id.buttonPause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pause(v);
            }
        });

        /*
        this button will be deleted because it's not useful, we just need button play and pause to
        handel our application
         */
        stopButton = (Button) findViewById(R.id.buttonStop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop(v);

            }
        });

        loadButton = (Button) findViewById(R.id.loadAudioButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAudioFile();
                /* TODO Warning:
                 * When launching this method while another stream is playing,
                 * there is no way to stop the stream as the "stop" control switches
                 * to the new stream.
                 */
            }
        });

        songTitle = (TextView) findViewById(R.id.songTitle);
        songTitle.setText("test_mp3_0001.mp3");

        songDuration = (TextView) findViewById(R.id.songDuration);
        songDuration.setText("song duration");

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
            startTime = mp.getCurrentPosition();
            sk.setProgress((int)startTime);

            finalTime = mp.getDuration();
            double timeRemaining = finalTime - startTime;

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
        loadAudioFile();

        try {
            mp.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.setLooping(true); // e' fondamentale?
        mp.start();
        sk.setMax(mp.getDuration());
        handler.postDelayed(updateBar,100);
    }


    public void pause(View v)
    {
        mp.pause();
    }

    public void stop(View v)
    {
        mp.pause();         // fermo la riproduzione del media player
        mp.seekTo(0);       // sposto la riproduzione a zero
        sk.setProgress(0);  // imposta la barra all'inizio
        mp.stop();
    }
    
    @Override
	 public void onDestroy(){
	 super.onDestroy();
	    mp.release();
	 }
	 
}
