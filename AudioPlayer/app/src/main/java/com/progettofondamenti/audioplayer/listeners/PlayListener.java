package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the PlayButton.
 *
 * @author team
 * @see android.view.View.OnClickListener
 */
public class PlayListener implements View.OnClickListener {

	private IPlayer player;

	public PlayListener(IPlayer player) {
		super();
		this.player = player;
	}


	@Override
	public void onClick(View v) {
		player.play();
	}
}