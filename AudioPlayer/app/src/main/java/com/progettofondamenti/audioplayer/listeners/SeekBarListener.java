package com.progettofondamenti.audioplayer.listeners;

import android.widget.SeekBar;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Simplified listener
 *
 * @author team
 * @see android.widget.SeekBar.OnSeekBarChangeListener
 */
public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private IPlayer player;

    public SeekBarListener(IPlayer player) {
        this.player = player;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser)
            player.seek(progress);
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

