package com.progettofondamenti.audioplayer.listeners;

import android.view.View;
import android.widget.SeekBar;

import com.progettofondamenti.audioplayer.BarUpdater;
import com.progettofondamenti.audioplayer.iPlayer;

import android.os.Handler;

/**
 * Terrible listener. Way too many parameters
 */
public class PlayListener implements View.OnClickListener {

	private iPlayer player;
	private SeekBar seekBar;
	private Handler handler;
	private BarUpdater barUpdater;

	public PlayListener(iPlayer player, SeekBar seekBar, Handler handler, BarUpdater barUpdater) {
		super();
		this.player = player;
		this.handler = handler;
		this.barUpdater = barUpdater;
		this.seekBar = seekBar;
	}

	@Override
	public void onClick(View v) {
		player.play();
		seekBar.setMax(100);
		seekBar.setProgress(player.getPlayerPosition());
		handler.postDelayed(barUpdater, 100);
	}
}