package com.progettofondamenti.audioplayer;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Class used to access and manipulate the file with the song titles.
 *
 * Created by claudio on 26/05/15.
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

	/**
	 * Circular increment
	 */
	private void incrementIndex() {
		if (index + 1 >= titles.size())
			index++;
		else
			index = 0;
	}

	public ArrayList<String> getTitles(){
		return titles;
	}

	public String getServerSuffix(){
		return serverSuffix;
	}
}
