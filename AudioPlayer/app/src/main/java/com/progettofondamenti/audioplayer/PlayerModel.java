package com.progettofondamenti.audioplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

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

	/* Defining upper and lower limits of forward and backward times.
	 * Ideally there should be an option in the settings
	 * that already define those, but better safe than sorry.
	 */
	private static final int MAX_FORWARD_TIME = 60000;
	private static final int MAX_BACKWORD_TIME = 60000;

	/* Defining parameters */
	private int forwardTime = 2000;
	private int backwardTime = 2000;

	private TitlesList titlesList;

	private MediaPlayer mediaPlayer;
	private String uri;

	public PlayerModel(String serverSuffix) {
		mediaPlayer = new MediaPlayer();
		this.titlesList = new TitlesList(serverSuffix);
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
		mediaPlayer.seekTo(
				((getPlayerPosition() - backwardTime) > 0) ?
						(getPlayerPosition() - backwardTime)
						:
						0
		);
	}

	@Override
	public void forward() {
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
	 * Makes the mediaplayer execute the previous song
	 */
	@Override
	public void previous() throws IOException {
		reset();

		initializeMPStreaming(titlesList.getUrlOfPreviousSong());
	}

	/*
	 * Makes the mediaplayer execute the next song
	 */
	@Override
	public void next() throws IOException {
		reset();

		initializeMPStreaming(titlesList.getUrlOfNextSong());
	}

	/*
	 * retrives the mediaplayer current position
	 */
	@Override
	public int getPlayerPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	/*
	 * retrives the total duration of the song player by the mediaplayer
	 */
	@Override
	public int getTotalDuration() {
		return mediaPlayer.getDuration();
	}

	/*
	 * This method sets the mediaplayer with a specific html address in order to play a song
	 * downloaded from the server
	 */
	private void initializeMPStreaming(String url) throws IOException {

		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(url);
		mediaPlayer.prepare(); // might take long! (for buffering, etc)
	}

	/*
	 * resets the mediaplayer
	 * this is very important because gives the possibility to play different songs
	 */
	@Override
	public void reset() {
		if (isPlaying()){
			stop();
		}
		mediaPlayer.reset();
	}

	/*
	 * releases the mediaplayer after it has been prepared and started
	 */
	@Override
	public void die(){
		mediaPlayer.release();
	}

	// TODO: Remember to put options for these in the settings activity.

	public void setBackwardTime(int backwardTime) {
		if (backwardTime < MAX_BACKWORD_TIME) {
			this.backwardTime = backwardTime;
		} else {
			this.backwardTime = MAX_BACKWORD_TIME;
		}
	}

	public void setForwardTime(int forwardTime) {
		if (forwardTime < MAX_FORWARD_TIME) {
			this.forwardTime = forwardTime;
		} else {
			this.forwardTime = MAX_FORWARD_TIME;
		}
	}

	/*
	 * Retrives the titlesList object which contains all the song names
	 */
	@Override
	public TitlesList getTitlesList() {
		return titlesList;
	}

	/*
	 * Retrives the playing state of the mediaplayer
	 */
	@Override
	public boolean isPlaying(){
		return mediaPlayer.isPlaying();
	}
}