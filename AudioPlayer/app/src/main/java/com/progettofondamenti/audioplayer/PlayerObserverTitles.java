package com.progettofondamenti.audioplayer;

import android.util.Log;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * With TitlesListTask implemented, this class is now useless.
 * Keeping it anyway because you never know - Saev.
 *
 * Created by claudio on 26/05/15.
 */
public class PlayerObserverTitles implements Observer {

	private IPlayer player;
	private TitlesList titlesList;

	public PlayerObserverTitles(IPlayer player, TitlesList titlesList) {
		this.player = player;
		this.titlesList = titlesList;
		titlesList.addObserver(this);
	}

	@Override
	public void update(Observable observable, Object data) {
		try {
			player.initializeMPStreaming(titlesList.getNextTitle());
		} catch (IOException e) {
			Log.e("--------", e.getMessage());
		}
	}
}
