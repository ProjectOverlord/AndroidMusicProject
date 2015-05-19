package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.IPlayer;

/**
 * Listener for the RewindButton.
 *
 * Created by Saeval on 16/05/2015.
 */
public class RewindListener implements View.OnClickListener{

	private IPlayer player;

	public RewindListener(IPlayer player){
		super();
		this.player = player;
	}

	@Override
	public void onClick(View v) {
		player.rewind();
	}
}
