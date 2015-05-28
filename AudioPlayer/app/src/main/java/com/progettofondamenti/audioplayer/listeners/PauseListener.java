package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the PauseButton.
 *
 *@author team
 * @see android.view.View.OnClickListener
 */
public class PauseListener implements View.OnClickListener {

	private IPlayer player;


	public PauseListener(IPlayer player){
		super();
		this.player = player;
	}


	@Override
	public void onClick(View v) {
		player.pause();
	}
}
