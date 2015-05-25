package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.progettofondamenti.audioplayer.listeners.SeekBarListener;

import java.util.concurrent.TimeUnit;

/**
 * This class is the view for everything connected with the reproduction of the player.
 * This is a part of the MVC implementation without the observable/observer DP.
 * The update is achieved with the runnable.
 *
 * TODO: Arianna puppa
 *
 * @author CL & MS
 */
public class PlayerView implements Runnable{

	private IPlayer player;

	private SeekBar seekBar;
	private TextView elapsedTime;
	private TextView remainingTime;

	private Handler handler = new Handler();

	public PlayerView(IPlayer player, ActionBarActivity activity) {
		this.player = player;

		seekBar = (SeekBar) activity.findViewById(R.id.bar);
		seekBar.setClickable(true);
		seekBar.setOnSeekBarChangeListener(new SeekBarListener(player));

		elapsedTime = (TextView) activity.findViewById(R.id.elapsedTime);
		updateTime(elapsedTime, 0);
		remainingTime = (TextView) activity.findViewById(R.id.remainingTime);
		updateTime(remainingTime, player.getTotalDuration());
	}

	/*
     * Meccanismo di aggiornamento delle componenti grafiche associate al player, quali barra
     * e etichette di durata.
     * Si è usato un handler temporizzato che legge ogni 100 millisecondi la posizione attuale
     * di riproduzione, ed attua il conseguente aggiornamento della barra.
     */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void run()
	{
		seekBar.setProgress(player.getPlayerPosition());
		seekBar.setMax(player.getTotalDuration());

		updateTime(elapsedTime, player.getPlayerPosition());

		updateTime(remainingTime, (player.getTotalDuration() - player.getPlayerPosition()));

		handler.postDelayed(this, 100);
	}

	/* Il metodo toMinutes abbisogna di un'API minima 9 */
	private void updateTime(TextView textView, int time){
		textView.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(time),
				TimeUnit.MILLISECONDS.toSeconds(time)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))));
	}

}