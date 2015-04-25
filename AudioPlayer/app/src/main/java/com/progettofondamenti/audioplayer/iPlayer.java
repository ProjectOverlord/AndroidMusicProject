package com.progettofondamenti.audioplayer;

import android.content.Context;

import java.io.IOException;

/**
 * This is the interface for all the possible Player implementations.
 * That way it is possible to offer different implementations, giving
 * the code more flexibility.
 */
public interface iPlayer {

	public void play();

	public void pause();

	public void stop();

	public void rewind();

	public void forward();

	public void previous();

	public void next();

	public void seek(int to);

	public int getPlayerPosition();

	public int getTotalDuration();

	public void setContentToStream(String path) throws IOException;

	public void setLocalFileToPlay(String path) throws IOException;

	public void initializeMediaPlayerProvvisorio(Context context);

	public void die();

}
