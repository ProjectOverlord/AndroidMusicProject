package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Listener for the ForwardButton.
 *
 * @author team
 */
public class ForwardListener implements View.OnClickListener{

    private IPlayer player;

    public ForwardListener(IPlayer player){
        super();
        this.player=player;
    }

    @Override
    public void onClick(View v) {
        player.forward();
    }
}
