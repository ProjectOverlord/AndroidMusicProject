package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the RewindButton.
 *
 * @author team
 * @see android.view.View.OnClickListener
 */
public class RewindListener implements View.OnClickListener{

	private IPlayer player;

	/**
	 * RewindListener()
	 * @param player
	 */
	public RewindListener(IPlayer player){
		super();
		this.player = player;
	}

	/**
	 * ovverride
	 * onClick()
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		player.rewind();
	}
}
