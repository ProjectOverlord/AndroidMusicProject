package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the NextButton.
 *
 * Created by Filippo on 16/05/15.
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
