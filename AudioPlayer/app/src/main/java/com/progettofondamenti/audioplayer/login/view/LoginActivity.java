package com.progettofondamenti.audioplayer.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.progettofondamenti.audioplayer.MainActivity;
import com.progettofondamenti.audioplayer.R;


public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		/* The method declares its onClick behaviour in the XML file. As of now it simply calls the goToNextActivity method) */
		Button loginButton = (Button) findViewById(R.id.loginButton);
	}

	/* This method is called by pressing the login button and open the MainActivity */
	public void goToNextActivity(View v) {
		startActivity(new Intent(LoginActivity.this, MainActivity.class));
	}

}
