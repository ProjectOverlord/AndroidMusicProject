package com.progettofondamenti.audioplayer.listeners;

import android.util.Log;
import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

import java.io.IOException;

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

        // player.next();
        if (player.isPlaying()){
            player.stop();
        }
        player.reset();

        try {
            player.initializeMPStreaming("http://192.168.1.169:8080/aac.m4a");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("---------", "Sticazzi");
        }
    }
}
