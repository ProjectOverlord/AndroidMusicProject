package com.progettofondamenti.audioplayer;

import android.content.Context;

import java.io.IOException;

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

	public abstract void setLocalFileToPlay(String path) throws IOException;

	public abstract void initializeMediaPlayerWithLocalFile(Context context);

	public abstract void initializeMPStreaming(String url) throws IOException;

	public abstract void reset();

	public abstract void die();

	public abstract boolean isPlaying();

}