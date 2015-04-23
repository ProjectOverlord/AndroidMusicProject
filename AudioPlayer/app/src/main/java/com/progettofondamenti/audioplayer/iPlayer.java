package com.progettofondamenti.audioplayer;

import android.content.Context;

import java.io.IOException;

/**
 * This is the interface for all the possible Player implementations.
 * That way it is possible to offer different implementations, giving
 * the code more flexibility.
 */
public interface iPlayer {
	void play();

	void pause();

	void stop();

	int getPlayerPosition();

	int getTotalDuration();

	void setContentToStream(String path) throws IOException;

	void setLocalFileToPlay(String path) throws IOException;

	void initializeMediaPlayerProvvisorio(Context context);
}
