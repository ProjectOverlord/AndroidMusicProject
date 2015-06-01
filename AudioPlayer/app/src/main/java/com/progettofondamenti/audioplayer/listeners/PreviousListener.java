package com.progettofondamenti.audioplayer.listeners;

import android.util.Log;
import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

import java.io.IOException;

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
        try {
            player.previous();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("---------", "Sticazzi");
        }
    }
}
