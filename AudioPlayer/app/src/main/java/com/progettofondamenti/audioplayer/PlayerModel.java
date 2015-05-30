package com.progettofondamenti.audioplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class aims to be the model for the audio player.
 * No other class should be allowed to see the effective mediaPlayer.
 *
 * This is an implementation of the IPlayer interface, which means
 * that other players can be implemented as long as they possess the same methods.
 *
 * @author CL
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

	/* Declaring objects */
	private MediaPlayer mediaPlayer;
	private String uri;

	/* Empty constructor used to play in streaming */
	public PlayerModel() {
		mediaPlayer = new MediaPlayer();
	}

	/* Constructor used to play a specific file inside the app*/
	public PlayerModel(Context context) {
		mediaPlayer = new MediaPlayer();
		initializeMediaPlayerWithLocalFile(context);
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

		/* aggiunta di prova: ricompongo il file dal path e lo passo allo stream */
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);

		mediaPlayer.setDataSource(fileInputStream.getFD());

		fileInputStream.close();
	}

	/*
	 * This method is specific for a local mp3 file
	 */
	@Override
	public void initializeMediaPlayerWithLocalFile(Context context) {
		mediaPlayer = MediaPlayer.create(context, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
	}

	/*
	 * This method sets the mediaplayer with a specific html address in order to play a song
	 * dowloaded from the server
	 */
	@Override
	public void initializeMPStreaming(String url) throws IOException {

		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setDataSource(url);
		mediaPlayer.prepare(); // might take long! (for buffering, etc)

	}

	@Override
	public void reset() {
		mediaPlayer.reset();
	}

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

	public boolean isPlaying(){
		return mediaPlayer.isPlaying();
	}
}