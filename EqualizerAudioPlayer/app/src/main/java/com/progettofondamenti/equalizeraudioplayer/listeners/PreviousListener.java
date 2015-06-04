package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Listener for the PreviousButton.
 *
 * @author team
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
