package com.progettofondamenti.audioplayer.listeners;

import android.os.Handler;
import android.widget.SeekBar;

import com.progettofondamenti.audioplayer.BarUpdater;
import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the SeekBar
 * Created by francesco on 16/05/15.
 */
public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private BarUpdater barUpdater;
    private android.os.Handler handler = new Handler();
    private IPlayer player;


    public SeekBarListener(BarUpdater barUpdater, Handler handler, IPlayer player) {
        this.barUpdater = barUpdater;
        this.handler = handler;
        this.player = player;
    }

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
}

