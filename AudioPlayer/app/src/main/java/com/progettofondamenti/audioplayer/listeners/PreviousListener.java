package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.iPlayer;

/**
 * Listener for the PreviousButton.
 *
 * Created by Filippo on 16/05/15.
 */
public class PreviousListener implements View.OnClickListener{

    private iPlayer player;

    public PreviousListener(iPlayer player){
        super();
        this.player=player;
    }

    @Override
    public void onClick(View v) {
        player.forward();
    }
}
