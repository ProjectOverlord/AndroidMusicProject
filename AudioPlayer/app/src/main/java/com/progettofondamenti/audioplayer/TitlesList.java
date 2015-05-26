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
 * Created by claudio on 26/05/15.
 */
public class TitlesList extends Observable implements Runnable{

	private String serverSuffix;
	private static String titlesFileName = "song_titles.txt";

	private ArrayList<String> titles = new ArrayList<String>();
	private int index = -1;

	public TitlesList(String serverSuffix) {
		super();
		setServerSuffix(serverSuffix);
		fillTitlesFromServer(serverSuffix);
	}

	public void setServerSuffix(String serverSuffix) {
		this.serverSuffix = serverSuffix;
	}

	public void fillTitlesFromServer(String serverSuffix) {
		Thread thread = new Thread(this);
		thread.start();
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

	@Override
	public void run() {
		try {
			// Create a URL for the desired page
			URL url = new URL(serverSuffix+titlesFileName);
			Log.e("-----URL-----", url.toString());

			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str;
			while ((str = in.readLine()) != null) {
				titles.add(str);
			}
			in.close();

			setChanged();
			notifyObservers();

		} catch (MalformedURLException e) {
			Log.e("--------", e.getMessage());
		} catch (IOException e) {
			Log.e("--------", "fuffaboccia");
		}
	}
}
