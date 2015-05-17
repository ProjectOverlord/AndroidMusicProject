package com.progettofondamenti.audioplayer.login.model;

import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This class is the componente whose responsibility is to connect to the server and send the login informations
 *
 * @author claudio
 */
public class LoginClass {

	String charset = null;
	String url = null;
	String username = null;
	String password = null;
	String query = null;

	public LoginClass() {
		// Indirizzo del server
		url = "192.168.1.169"; // "http://79.54.146.214";

		// Charset
		if (Build.VERSION.SDK_INT >= 19)
			charset = java.nio.charset.StandardCharsets.UTF_8.name();
		else
			charset = "UTF-8";

		// Parametri
		username = "value1";
		password = "value2";

		// Definizione della query
		try {
			query = String.format("username=%s&password=%s",
					URLEncoder.encode(username, charset),
					URLEncoder.encode(password, charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
