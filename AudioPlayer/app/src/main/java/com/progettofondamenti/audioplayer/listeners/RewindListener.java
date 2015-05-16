package com.progettofondamenti.audioplayer.listeners;

import android.view.View;

import com.progettofondamenti.audioplayer.iPlayer;

/**
 * Listener for the RewindButton.
 *
 * Created by Saeval on 16/05/2015.
 */
public class RewindListener implements View.OnClickListener{

	private iPlayer player;

	public RewindListener(iPlayer player){
		super();
		this.player = player;
	}

	@Override
	public void onClick(View v) {
		player.rewind();
	}
}
