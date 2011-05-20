package com.cs110.stdev.crossfit;

/**
 * 
 *@author Pengyu Wang
 * 
 * 
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Crossfit extends Activity implements OnClickListener {

	/* define all components */
	TextView appName;
	TextView passwordText;
	TextView usernameText;
	EditText enterUsername;
	EditText enterPassword;
	Button loginButton;
	Button createAccountButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		/* assign components */
		appName = (TextView) findViewById(R.id.appName);
		usernameText = (TextView) findViewById(R.id.usernameText);
		enterUsername = (EditText) findViewById(R.id.enterUsername);
		passwordText = (TextView) findViewById(R.id.passwordText);
		enterPassword = (EditText) findViewById(R.id.enterPassword);
		loginButton = (Button) findViewById(R.id.loginButton);
		createAccountButton = (Button) findViewById(R.id.createAccountButton);

		/* button action */
		loginButton.setOnClickListener(this);
		//loginButton.setOnClickListener(this);
		createAccountButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		if(v==loginButton){
			Intent i = new Intent(this, TabHoster.class);
			startActivity(i);
		}
		else if(v==createAccountButton){
			Intent i = new Intent(this, Register.class);
			startActivity(i);
		}
		/* get and log username and password */
		/*
		 * String usr = enterUsername.getText().toString(); String psswrd =
		 * enterPassword.getText().toString();
		 * Log.d("Crossfit","username is "+usr);
		 * Log.d("Crossfit","password is "+psswrd);
		 */
		/* direct to the welcome page */
		// setContentView(R.layout.register);
	}


}
