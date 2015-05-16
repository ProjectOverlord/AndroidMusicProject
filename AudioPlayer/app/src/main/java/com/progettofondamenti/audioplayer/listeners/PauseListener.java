package com.progettofondamenti.audioplayer.listeners;

import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import com.progettofondamenti.audioplayer.BarUpdater;
import com.progettofondamenti.audioplayer.iPlayer;

/**
 * Created by Saeval on 16/05/2015.
 */
public class PauseListener implements View.OnClickListener {

	iPlayer player;
	SeekBar seekBar;
	Handler handler;
	BarUpdater barUpdater;

	public PauseListener(iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater){
		super();
		this.player = player;
		this.handler = handler;
		this.barUpdater = barUpdater;
		this.seekBar = seekBar;
	}

	@Override
	public void onClick(View v) {
		player.pause();
	}
}
