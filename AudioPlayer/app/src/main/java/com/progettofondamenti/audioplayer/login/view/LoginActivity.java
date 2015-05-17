package com.progettofondamenti.audioplayer.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.progettofondamenti.audioplayer.MainActivity;
import com.progettofondamenti.audioplayer.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		/* The method declares its onClick behaviour in the XML file. As of now it simply calls the goToNextActivity method) */
		Button loginButton = (Button) findViewById(R.id.loginButton);

		Button experimentalLoginButton = (Button) findViewById(R.id.experimentalLogin);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	/* This method is called by pressing the login button and open the MainActivity */
	public void goToNextActivity(View v) {
		goToNextActivity();
	}
	public void goToNextActivity() {
		startActivity(new Intent(LoginActivity.this, MainActivity.class));
	}

	public void sendLoginToServer() {


	}

}
