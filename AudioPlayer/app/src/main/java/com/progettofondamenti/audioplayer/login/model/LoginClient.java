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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}