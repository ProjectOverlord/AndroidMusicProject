package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Listener for the PauseButton.
 *
 * Created by Saeval on 16/05/2015.
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
