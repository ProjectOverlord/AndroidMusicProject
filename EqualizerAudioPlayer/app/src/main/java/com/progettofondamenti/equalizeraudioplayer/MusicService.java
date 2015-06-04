package com.progettofondamenti.equalizeraudioplayer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

/**
 * The aim of this class is to operate as a service for a mediaplayer
 * that we want to pass to the EqualizerActivity
 */
public class MusicService extends Service implements 
MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
MediaPlayer.OnCompletionListener {

	private PlayerModel player;

	private final IBinder musicBind = new MusicBinder();

	private String songTitle="";

	private static final int NOTIFY_ID=1;

	public void onCreate(){
		super.onCreate();

		player = new PlayerModel();

	}

	public class MusicBinder extends Binder {
		MusicService getService() { 
			return MusicService.this;
		}
	}

	// activity will bind to service
	@Override
	public IBinder onBind(Intent intent) {
		return musicBind;
	}

	// release resources when unbind
	@Override
	public boolean onUnbind(Intent intent){
		player.stop();
		player.die();
		return false;
	}

	/*public void playSong(){
		player.reset();

		player.initializeMediaPlayer(getApplicationContext());
	}*/


	@Override
	public void onCompletion(MediaPlayer mp) {
		//check if playback has reached the end of a track
		if(player.getMediaPlayer().getCurrentPosition()>0){
			mp.reset();

		}
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.v("MUSIC PLAYER", "Playback Error");
		mp.reset();
		return false;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onPrepared(MediaPlayer mp) {

		mp.start();

		Intent notIntent = new Intent(this, MainActivity.class);
		notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PendingIntent pendInt = PendingIntent.getActivity(this, 0,notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(pendInt)
		.setSmallIcon(R.drawable.app_icon_play)
		.setTicker(songTitle)
		.setOngoing(true)
		.setContentTitle("Playing")
		.setContentText(songTitle);

		Notification not = builder.build();
		startForeground(NOTIFY_ID, not);
	}

	@Override
	public void onDestroy() {
		stopForeground(true);
	}

}
