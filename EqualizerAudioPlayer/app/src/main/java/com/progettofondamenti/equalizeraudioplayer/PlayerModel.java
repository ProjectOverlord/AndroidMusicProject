package com.progettofondamenti.equalizeraudioplayer;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * This class aims to be the model for the audio player.
 * No other class should be allowed to see the effective mediaPlayer.
 *
 * This is an implementation of the IPlayer interface, which means
 * that other players can be implemented as long as they possess the same methods.
 *
 * @author team
 */
public class PlayerModel implements IPlayer {

	/*
	 * Defining upper and lower limits of forward and backward times.
	 * Ideally there should be an option in the settings
	 * that already define those, but better safe than sorry.
	 */
	private static final int MAX_FORWARD_TIME = 60000;
	private static final int MAX_BACKWORD_TIME = 60000;

	private int forwardTime = 2000;
	private int backwardTime = 2000;

	private MediaPlayer mediaPlayer;

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public PlayerModel() {

	}

    public PlayerModel(Context context) {
        mediaPlayer = new MediaPlayer();
		initializeMediaPlayer(context);
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
	 * This methods makes the song start from the beginning
	 */
	@Override
	public void previous() {
		mediaPlayer.seekTo(0);
	}

	/*
	 * This method seeks the player to the end of the song
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


	@Override
	public void initializeMediaPlayer(Context context) {
		mediaPlayer = MediaPlayer.create(context, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
	}

	@Override
	public void die(){
		mediaPlayer.release();
	}

	@Override
	public int getIdAudioSession() {
		return mediaPlayer.getAudioSessionId();
	}

	@Override
	public void reset() {
		mediaPlayer.reset();
	}

}
