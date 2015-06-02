package com.progettofondamenti.audioplayer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

/**
 * Class that's launched in MainActivity in background.
 * That being said, MainActivity actually waits for this to be done
 * since it can't continue with its duty otherwise.
 *
 * @author team
 * @see android.os.AsyncTask
 */
public class TitlesListTask extends AsyncTask<URL, Integer, Long> {

	private static String titlesFileName = "song_titles.txt";
	private IPlayer player;

	public TitlesListTask(IPlayer player) {
		super();
		this.player = player;
	}

	/**NON JAVADOC
	 * This method is called when the task execute() method is called.
	 *
	 * @param urls Uselesss stuff in our case. It's required
	 *             for the override though, so no way around it.
	 * @return Useless stuff in our case. See above.
	 */

	@Override
	protected Long doInBackground(URL... urls) {
		try {
			// Create a URL for the desired page
			String suffix = player.getTitlesList().getServerSuffix();
			String titles = titlesFileName;
			URL url = new URL(suffix+titles);

			// TODO: RENDERE MIGLIORE L'ESPRESSIONE SOPRA
			//URL url = new URL(player.getTitlesList().getServerSuffix()+titlesFileName);
			Log.e("-----URL-----", url.toString());

			// Right now, at least on Ubuntu, this takes forever.
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

//			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			urlConnection.connect();
			InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
			BufferedReader in = new BufferedReader(inputStreamReader);
			String str = in.readLine();
			while(str != null) {
				if (!str.trim().isEmpty()) // Does not consider blank lines
					player.getTitlesList().getTitles().add(str);
				str = in.readLine();
			}

		} catch (MalformedURLException e) {
			Log.e("--------", e.getMessage());
		} catch (IOException e) {
			Log.e("--------", "fuffaboccia");
		}
		return (long)player.getTitlesList().getTitles().size();
	}

	@Override
	protected void onPostExecute(Long result) {
		cancel(true);
	}

}
