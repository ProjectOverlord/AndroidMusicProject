package com.progettofondamenti.equalizeraudioplayer;

import android.content.Context;
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

	// NECESSARI PER EQ
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public PlayerModel() {

	}

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

		/* aggiunta di prova: ricompongo il file dal path e lo passo allo stream */
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);

		mediaPlayer.setDataSource(fileInputStream.getFD());

		/* NOTA IMPORTANTE per chiunque voglia provare a sistemare il codice
		 *
		 * premetto che non posso testare nulla in assenza del codice del server..
		 * se vogliamo poter scaricare il file audio ed eseguirlo dovrebbe essere sufficiente chiamare prepare(),
		 * dopo aver settato il dataSource, mentre se vogliamo fare una sorta di streaming,
		 * è necessario impostare il tipo con il metodo setAudioStreamType commentato sotto e
		 * scegliere prepareAsync(). Quanto detto è dovuto al fatto che prepare() non può andar bene per lo streaming,
		 * dato che bisogna attendere lo scaricamento perché esegua correttamente.
		 * Inoltre bisognerebbe anche utilizzare
		 * mPlayer.setOnPreparedListener(new OnPreparedListener() {
		 *
		 * @Override
		 * public void onPrepared(MediaPlayer mp) {
		 * mPlayer.start();
		 * }
		 * });
		 *
		 * Nel caso di non streaming non riesco a capire se basta passare l'uri a setDataSource senza
		 * il FileInputStream o meno, visto che trovo molti esempi che fanno ricorso a soluzioni contrastanti...
		 * (anche perché è impossibile fare prove)
		 *
		 * spero di essere stato d'aiuto a qualcuno,
		 * ho scritto qui per evidenziare nel codice la questione...
		 * by Fra
		 *
		 * */


		// dovrebbe essere meglio per  lo streaming
		//mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		mediaPlayer.prepare();

		// serve per lo streaming
		//mediaPlayer.prepareAsync();

		fileInputStream.close();
	}

	@Override
	public void initializeMediaPlayerProvvisorio(Context context) {
		mediaPlayer = MediaPlayer.create(context, R.raw.wolfgang_amadeus_mozart_piano_concerto_no_21_andante);
	}

	@Override
	public void die(){
		mediaPlayer.release();
	}

	@Override
	public int getIdAudioSession() {
		int id = mediaPlayer.getAudioSessionId();
		return id;
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
}
