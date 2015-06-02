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
 * public void run()
 * 		 Update mechanism of graphical components associated with the player,
 * 		such as tick labels duration.
 * 		It is used a timer handler that reads every 100 milliseconds the current position of reproduction,
 * 		and implements the consequent updating of the bar.
 *
 *@author team
 * @see java.lang.Runnable
 *
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


	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void run()
	{
		seekBar.setProgress(player.getPlayerPosition());
		seekBar.setMax(player.getTotalDuration());

		updateTime(elapsedTime, player.getPlayerPosition());

		updateTime(remainingTime, (player.getTotalDuration() - player.getPlayerPosition()));

		handler.postDelayed(this, 100);
	}

	/*The method "toMinutes" requires an API minimum 9 */
	private void updateTime(TextView textView, int time){
		textView.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(time),
				TimeUnit.MILLISECONDS.toSeconds(time)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))));
	}

}