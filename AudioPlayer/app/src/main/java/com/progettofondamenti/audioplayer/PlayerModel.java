package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class aims to be the model for the audio player.
 * No other class should be allowed to see the effective mediaPlayer.
 */
public class PlayerModel implements iPlayer {

    private MediaPlayer mediaPlayer;


    public PlayerModel(Context context) {
        mediaPlayer = new MediaPlayer();
		initializeMediaPlayerProvvisorio(context);
    }

    @Override
	public void play() {
		mediaPlayer.start();
    }

	@Override
	public void pause() {
		mediaPlayer.pause();
	}

	@Override
	public void stop() {
		mediaPlayer.stop();
	}

	@Override
	public int getPlayerPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	@Override
	public int getTotalDuration() {
		return mediaPlayer.getDuration();
	}

	/**
	 * This method specifies the path to the file (or the URL to the stream!)
	 *
	 * TODO: Do not understand why the initialization doesn't work
	 *
	 * @param path
	 * @throws IOException
	 */
	@Override
	public void setContentToStream(String path) throws IOException {
		mediaPlayer.reset();
		mediaPlayer.setDataSource(path);
	}


	/**
	 * This method is specific for a local file and ensures the file exists
	 * before proceeding.
	 *
	 * TODO: Do not understand why the initialization doesn't work
	 *
	 * @param path
	 * @throws IOException
	 */
	@Override
	public void setLocalFileToPlay(String path) throws IOException {
		// mediaPlayer.reset();
		FileInputStream fileInputStream = new FileInputStream(path);
		mediaPlayer.setDataSource(fileInputStream.getFD());
	}

	@Override
	public void initializeMediaPlayerProvvisorio(Context context) {
		mediaPlayer = MediaPlayer.create(context, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
	}
}
