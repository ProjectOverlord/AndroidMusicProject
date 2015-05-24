package com.progettofondamenti.audioplayer.login.model;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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

	String charset = null;
	String url = null;
	String username = null;
	String password = null;
	String query = null;

	public LoginClient() {
		// Indirizzo del server
		url = "192.168.1.169"; // "http://79.54.146.214";

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
			Log.e("---------------", e.getMessage());
		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	public void fireHttpPostRequest() throws IOException {
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST request
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

		try(OutputStream outputStream = connection.getOutputStream()) {
			outputStream.write(query.getBytes(charset));
		}

		InputStream response = connection.getInputStream();
	}
}
