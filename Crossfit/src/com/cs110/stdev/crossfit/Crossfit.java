package com.cs110.stdev.crossfit;

/**
 * 
 *@author Pengyu Wang
 * 
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;

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
	Button forgotPasswordButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// display the xml formatted page
		setContentView(R.layout.login);

		/* assign components */
		appName = (TextView) findViewById(R.id.appName);
		usernameText = (TextView) findViewById(R.id.usernameText);
		enterUsername = (EditText) findViewById(R.id.enterUsername);
		passwordText = (TextView) findViewById(R.id.passwordText);
		enterPassword = (EditText) findViewById(R.id.enterPassword);
		loginButton = (Button) findViewById(R.id.loginButton);
		createAccountButton = (Button) findViewById(R.id.createAccountButton);
		forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);

		/* button action */
		loginButton.setOnClickListener(this);
		createAccountButton.setOnClickListener(this);
		forgotPasswordButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		if (v == loginButton) {
			String username = enterUsername.getText().toString();
			String password = enterPassword.getText().toString();

			LinkedList<User> userlist = new LinkedList<User>();
			String filename = "user.ser";

			try {
				FileInputStream fis = openFileInput(filename);
				ObjectInputStream in = new ObjectInputStream(fis);
				userlist = (LinkedList<User>) in.readObject();
				in.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}

			User user = userlist.get(0);

			if (user.getUsername().equals(username)
					&& user.getPassword().equals(password)) {
				Intent i = new Intent(this, TabHoster.class);
				startActivity(i);
			}
			
		} else if (v == createAccountButton) {
			Intent i = new Intent(this, Register.class);
			startActivity(i);
		}

		else if (v == forgotPasswordButton) {
			Intent i = new Intent(this, LostPasswordActivity.class);
			startActivity(i);
		}
	}

}
