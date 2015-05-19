package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the ForwardButton.
 *
 * Created by Filippo on 16/05/15.
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
