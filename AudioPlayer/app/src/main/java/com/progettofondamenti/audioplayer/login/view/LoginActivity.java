package com.progettofondamenti.audioplayer.login.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.progettofondamenti.audioplayer.MainActivity;
import com.progettofondamenti.audioplayer.R;
import com.progettofondamenti.audioplayer.login.model.LoginClient;

/**
 * This login activity has a simple design and clear interface.
 * The experimental login button should send login data to the server and put the app in a waiting state,
 * to recieve authorization.
 *
 * @author CL
 */
public class LoginActivity extends Activity {

	private LoginClient login = new LoginClient();
	private EditText usernameEditText;
	private EditText passwordEditText;
	private String username;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		/* The method declares its onClick behaviour in the XML file. As of now it simply calls the goToNextActivity method) */
		Button loginButton = (Button) findViewById(R.id.loginButton);

		usernameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/* This method is called by pressing the login button and open the MainActivity */
	public void goToNextActivity(View v) { goToNextActivity(); }
	public void goToNextActivity() {
		startActivity(new Intent(LoginActivity.this, MainActivity.class));
	}

	public void sendLoginToServer(View v) { sendLoginToServer(); }
	public void sendLoginToServer() {

		username = usernameEditText.getText().toString();
		password = passwordEditText.getText().toString();

		goToNextActivity();

	}

	public void testInternetConnection() {
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			Log.e("---hola---", "ok");
		} else {
			Log.e("--nope", "no good");
		}
	}
}