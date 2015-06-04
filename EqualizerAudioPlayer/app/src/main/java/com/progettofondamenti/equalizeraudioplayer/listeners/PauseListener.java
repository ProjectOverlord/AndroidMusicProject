package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Listener for the PauseButton.
 *
 * @author team
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
