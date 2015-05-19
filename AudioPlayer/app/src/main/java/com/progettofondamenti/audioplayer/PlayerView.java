package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Claudio on 5/19/2015.
 */
public class PlayerView {

    private IPlayer player;

    private SeekBar seekBar;
    private TextView elapsedTime;
    private TextView remainingTime;


    public PlayerView(IPlayer player, ActionBarActivity activity) {
        this.player = player;
        seekBar = (SeekBar) activity.findViewById(R.id.bar);
        elapsedTime = (TextView) activity.findViewById(R.id.elapsedTime);
        remainingTime = (TextView) activity.findViewById(R.id.remainingTime);
    }

}