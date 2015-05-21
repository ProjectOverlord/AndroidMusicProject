package com.progettofondamenti.audioplayer;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * This class is the view for everything connected with the reproduction of the player.
 * This is a part of the MVC implementation without the observable/observer DP.
 * The update is achieved with the runnable.
 *
 * TODO: Arianna puppa
 *
 * @author CL
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
        elapsedTime = (TextView) activity.findViewById(R.id.elapsedTime);
        remainingTime = (TextView) activity.findViewById(R.id.remainingTime);
    }

	/*
     * Meccanismo di aggiornamento delle componenti grafiche associate al player, quali barra e etichette di durata.
     * Si è usato un handler temporizzato che legge ogni 100 millisecondi
     * la posizione attuale di riproduzione, ed attua il conseguente aggiornamento della barra.
     */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void run()
	{
		double timeElapsed = player.getPlayerPosition();
		seekBar.setProgress((int) timeElapsed);
		seekBar.setMax(player.getTotalDuration());

		double finalTime = player.getTotalDuration();
		double timeRemaining = finalTime - timeElapsed;


		// il metodo toMinutes richiede una API 9 minima, bisognerebbe cambiare perché attualmente
		// la minima è la 8 per noi, funziona ugualmente essendo stato annotato (seguendo il suggerimento
		// di AS
		elapsedTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed),
				TimeUnit.MILLISECONDS.toSeconds((long) timeElapsed)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed))));

		remainingTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
				TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

		handler.postDelayed(this, 100);
	}

}