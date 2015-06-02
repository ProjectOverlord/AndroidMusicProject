package com.progettofondamenti.audioplayer;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class used to access and manipulate the file with the song titles.
 *
 * @author team
 * @see java.util.Observable
 */
public class TitlesList extends Observable {

	private String serverSuffix;
	private static String titlesFileName = "song_titles.txt";

	private ArrayList<String> titles = new ArrayList<String>();
	private int index = -1;

	public TitlesList(String serverSuffix) {
		super();
		setServerSuffix(serverSuffix);
	}

	public void setServerSuffix(String serverSuffix) {
		this.serverSuffix = serverSuffix;
	}

	public String getNextTitle() {
		incrementIndex();
		return titles.get(index);
	}

	public String getUrlOfNextSong() {
		return serverSuffix+getNextTitle();
	}

	public String getPreviousTitle() {
		decrementIndex();
		return titles.get(index);
	}

	public String getUrlOfPreviousSong() {
		return serverSuffix+getPreviousTitle();
	}

	/**
	 * Circular increment
	 * it allows the passage from a music track to the next
	 * pressing forward , once you reach the last index ( titles.size ( ) ) , go to 0
	 */
	private void incrementIndex() {
		if (index + 1 >= titles.size())
			index = 0;
		else
			index++;
	}

	/**
	 * Circular decrement
	 * allows the passage from one musical piece to the previous one
	 */
	private void decrementIndex() {
		if (index - 1 < 0)
			index = titles.size() - 1;
		else
			index--;
	}

	public ArrayList<String> getTitles(){
		return titles;
	}

	public String getServerSuffix(){
		return serverSuffix;
	}
}
