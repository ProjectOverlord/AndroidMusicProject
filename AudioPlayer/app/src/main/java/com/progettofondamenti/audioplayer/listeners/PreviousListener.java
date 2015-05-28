package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the PreviousButton.
 *
 * @author team
 * @see android.view.View.OnClickListener
 */
public class PreviousListener implements View.OnClickListener{

    private IPlayer player;


    public PreviousListener(IPlayer player){
        super();
        this.player=player;
    }


    @Override
    public void onClick(View v) {
        player.previous();
    }
}
