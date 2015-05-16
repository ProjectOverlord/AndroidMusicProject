package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.iPlayer;

/**
 * Listener for the PauseButton.
 *
 * Created by Saeval on 16/05/2015.
 */
public class PauseListener implements View.OnClickListener {

	iPlayer player;

	public PauseListener(iPlayer player){
		super();
		this.player = player;
	}

	@Override
	public void onClick(View v) {
		player.pause();
	}
}
