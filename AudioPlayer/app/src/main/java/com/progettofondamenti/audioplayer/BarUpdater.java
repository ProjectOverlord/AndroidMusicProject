package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * Created by Claudio on 4/21/2015.
 */
public class BarUpdater implements Runnable {

    private MediaPlayer mp;
    private Handler handler;
    private double timeElapsed = 0;
    private double finalTime;
    private Context context;
    private SeekBar seekBar;
    private TextView songDuration;

    public BarUpdater(Context context, SeekBar seekBar) {
        this.context = context;
        this.seekBar = seekBar;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void run()
    {
        timeElapsed = mp.getCurrentPosition();
        seekBar.setProgress((int) timeElapsed);

        finalTime = mp.getDuration();
        double timeRemaining = finalTime - timeElapsed;

        songDuration.setText("Song duration");

        // il metodo toMinutes richiede una API 9 minima, bisognerebbe cambiare perché attualmente
        // la minima è la 8 per noi, funziona ugualmente essendo stato annotato (seguendo il suggerimento
        // di AS
        songDuration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

        handler.postDelayed((Runnable) context, 100);
    }
}
