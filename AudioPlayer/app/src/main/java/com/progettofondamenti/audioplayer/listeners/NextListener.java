package com.progettofondamenti.audioplayer.listeners;

import android.util.Log;
import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

import java.io.IOException;

/**
 * Listener for the NextButton.
 *
 * @author team
 * @see android.view.View.OnClickListener
 */
public class NextListener implements View.OnClickListener{

    private IPlayer player;


    public NextListener(IPlayer player){
        super();
        this.player=player;
    }


    @Override
    public void onClick(View v) {
        try {
            player.next();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("---------", e.getMessage());
        }
    }
}
