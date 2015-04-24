package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class aims to be the model for the audio player.
 * No other class should be allowed to see the effective mediaPlayer.
 *
 * This is an implementation of the iPlayer interface, which means
 * that other players can be implemented as long as they possess the same methods.
 *
 * @author CL
 */
public class PlayerModel implements iPlayer {

	/* Defining constants */
	private static final int forwardTime = 2000;
	private static final int backwardTime = 2000;

	/* Declaring objects */
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
	public void rewind() {
		// check if we can go back at backwardTime seconds after song starts
		mediaPlayer.seekTo(
				((getPlayerPosition() - backwardTime) > 0) ?
						(getPlayerPosition() - backwardTime)
						:
						0
		);
	}

	@Override
	public void forward() {
		// check if we can go forward at forwardTime seconds before song end
		mediaPlayer.seekTo(
				((getPlayerPosition() + forwardTime) <= getTotalDuration()) ?
						(getPlayerPosition() + forwardTime)
						:
						getTotalDuration()
		);
	}

	@Override
	public void seek(int to) {
		mediaPlayer.seekTo(to);
	}

	/*
	 * This method should reset the actual song or go to the previous one
	 * if called when the song is in his first couple of seconds.
	 *
	 * TODO: As of now, this method just restart the song.
	 */
	@Override
	public void previous() {
		mediaPlayer.seekTo(0);
	}

	/*
	 * This method should go to the next song.
	 *
	 * TODO: As of now, this method just skips to the end of the song.
	 */
	@Override
	public void next() {
		mediaPlayer.seekTo(getTotalDuration());
	}

	@Override
	public int getPlayerPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	@Override
	public int getTotalDuration() {
		return mediaPlayer.getDuration();
	}

	/*
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


	/*
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
