package com.progettofondamenti.audioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

/**
 * The main activity of the program.
 * As of 17/04/2015, it needs to be refactored heavily.
 */
public class MainActivity extends Activity {

    private MediaPlayer mp=null;
    private Handler handler = new Handler();
    private double startTime = 0;
    private SeekBar sk=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sk=(SeekBar) findViewById(R.id.bar);

        //mp=MediaPlayer.create(MainActivity.this,R.raw.test_mp3_0001);

        Button button1 = (Button) findViewById(R.id.buttonPlay);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                play(v);
            }
        });

        Button button2 = (Button) findViewById(R.id.buttonPause);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pause(v);
            }
        });

        Button button3 = (Button) findViewById(R.id.buttonStop);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stop(v);

            }
        });

        Button button4 = (Button) findViewById(R.id.loadAudioButton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAudioFile();
                // TODO
            }
        });

    }

    public void loadAudioFile() {
        mp= MediaPlayer.create(this, R.raw.test_mp3_0001);
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
        public void run()
        {
            startTime = mp.getCurrentPosition();
            sk.setProgress((int)startTime);
            handler.postDelayed(this, 100);
        }
    };

    public void play(View v)
    {
        loadAudioFile();
        // mp=MediaPlayer.create(MainActivity.this,R.raw.test_mp3_0001);

        // mp = new MediaPlayer();
        try {
            mp.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            if(true) {
            }
        }

        mp.setLooping(true);
        mp.start();
        sk.setMax(mp.getDuration());
        handler.postDelayed(updateBar,100);
    }

    //per anima

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
}
