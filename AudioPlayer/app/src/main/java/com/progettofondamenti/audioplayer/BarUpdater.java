package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Specific class that keeps a seekbar up to date
 */
public class BarUpdater implements Runnable {

    private MediaPlayer mp;
    private Handler handler;
    private double timeElapsed = 0;
    private double finalTime;
    private SeekBar seekBar;
    private TextView songDuration;


    public BarUpdater(MediaPlayer mp, Handler handler, SeekBar seekBar, TextView songDuration) {
        this.seekBar = seekBar;
        this.mp = mp;
        this.songDuration = songDuration;
        this.handler = handler;
    }

    /*
     * Meccanismo di aggiornamento della barra di progresso. Il suo avanzamento rappresenta
     * l’andamento della riproduzione. Si è usato un handler temporizzato che legge ogni 100 millisecondi
     * la posizione attuale di riproduzione, ed attui il conseguente aggiornamento della barra.
     *
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void run()
    {
        timeElapsed = mp.getCurrentPosition();
        seekBar.setProgress((int) timeElapsed);

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
}
