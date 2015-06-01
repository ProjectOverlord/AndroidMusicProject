package com.progettofondamenti.audioplayer;

import java.io.IOException;

/**
 * This is the interface for all the possible Player implementations.
 * That way it is possible to offer different implementations, giving
 * the code more flexibility.
 * @author team
 */
public interface IPlayer {

	public abstract void play();

	public abstract void pause();

	public abstract void stop();

	public abstract void rewind();

	public abstract void forward();

	public abstract void previous() throws IOException;

	public abstract void next() throws IOException;

	public abstract void seek(int to);

	public abstract int getPlayerPosition();

	public abstract int getTotalDuration();

	public abstract void reset();

	public abstract void die();

	public abstract TitlesList getTitlesList();

	public abstract boolean isPlaying();

}
