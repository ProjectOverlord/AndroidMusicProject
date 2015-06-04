package com.progettofondamenti.equalizeraudioplayer;

import android.content.Context;

/**
 * This is the interface for all the possible Player implementations.
 * That way it is possible to offer different implementations, giving
 * the code more flexibility.
 */
public interface IPlayer {

	public abstract void play();

	public abstract void pause();

	public abstract void stop();

	public abstract void rewind();

	public abstract void forward();

	public abstract void previous();

	public abstract void next();

	public abstract void seek(int to);

	public abstract int getPlayerPosition();

	public abstract int getTotalDuration();

	public abstract void initializeMediaPlayer(Context context);

	public abstract void die();

	public abstract int getIdAudioSession();

	public abstract void reset();

}
