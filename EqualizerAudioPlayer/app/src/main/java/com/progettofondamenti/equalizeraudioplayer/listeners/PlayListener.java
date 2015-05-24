package com.progettofondamenti.equalizeraudioplayer.listeners;

import android.view.View;

import com.progettofondamenti.equalizeraudioplayer.IPlayer;

/**
 * Now the player listener seems just fine
 *
 * @author claudio
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