package com.progettofondamenti.audioplayer.login.model;

import android.annotation.TargetApi;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * This class is the component whose responsibility is to connect to the server and send the login informations
 *
 * @author CL
 */
public class LoginClient {

	private final String USER_AGENT = "Mozilla/5.0";

	String charset = null;
	String url = null;
	String username = null;
	String password = null;
	String query = null;

	public LoginClient() {
		// Indirizzo del server
		url = "http://www.google.com/"; // "http://79.54.146.214";

		// Charset
		if (Build.VERSION.SDK_INT >= 19)
			charset = java.nio.charset.StandardCharsets.UTF_8.name();
		else
			charset = "UTF-8";

		// Parametri
		username = "nome";
		password = "pass";

		// Definizione della query
		try {
			query = String.format("username=%s&password=%s",
					URLEncoder.encode(username, charset),
					URLEncoder.encode(password, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.e("-------Encoding-------", e.getMessage());
		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void fireHttpPostRequest(){
		URLConnection connection = null;
		try {
			connection = (new URL(url)).openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("---IO-Creation---", e.getMessage());

		}

		connection.setDoOutput(true); // Triggers POST request
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

		Log.e("Checkpoint", "1");

		// OutputStream outputStream = null;
		try {
			try (OutputStream outputStream = connection.getOutputStream()) {
				Log.e("Checkpoint", "2");
				outputStream.write(query.getBytes(charset));
				Log.e("Checkpoint", "3");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("----IO-Outputstream---", e.getMessage());
			Log.e("Checkpoint", "3.5");

		}

		Log.e("Checkpoint", "4");


		try {
			// connection.connect();
			InputStream response = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("---IO-Connection---", e.getMessage());
		}

	}

	public void fireHttpGetRequest() throws IOException {

		String url = "http://www.google.com/search?q=mkyong";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		Log.e("----", "\nSending 'GET' request to URL : " + url);
		Log.e("----", "Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		Log.e("----", response.toString());
	}
}
