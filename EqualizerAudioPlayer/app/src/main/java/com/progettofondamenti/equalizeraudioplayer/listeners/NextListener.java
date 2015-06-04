package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Listener for the NextButton.
 *
 * @author team
 */
public class NextListener implements View.OnClickListener{

    private IPlayer player;

    public NextListener(IPlayer player){
        super();
        this.player=player;
    }

    @Override
    public void onClick(View v) {
        player.next();
    }
}
