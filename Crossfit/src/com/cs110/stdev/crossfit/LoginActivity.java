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
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

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

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		/* what to do if the login button is clicked */
		if (view == loginButton) {
			/* grabbing the username and password fields from the screen */
			String username = enterUsername.getText().toString();
			String password = enterPassword.getText().toString();
			boolean validLogin = false;
			LinkedList<User> userlist = new LinkedList<User>();
			String filename = "user.ser";
			/* pulling the user from the database */
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

			User user = new User();
			int userListID = -1;
			// making sure there are users
			if (!userlist.isEmpty()) {
				for (int i = 0; i < userlist.size(); i++) {
					user = userlist.get(i);
					// checking username and password are valid
					if (user.getUsername().equals(username)
							&& user.getPassword().equals(password)) {
						// go to the tabs layout
						validLogin = true;
						userListID = i;
					}
				}
				// if the login info is valid, login
				if (validLogin) {
					Intent intent = new Intent(this, TabHosterActivity.class);
					/**************************************
					 * USER_LIST_ID will be passed through* every activity. It
					 * is essentially a* global variable that every activity*
					 * should have access to. *
					 **************************************/
					intent.putExtra("USER_LIST_ID", userListID);
					startActivity(intent);
				}
				// displaying error message if login info doesn't match any in
				// the database
				else
					Toast.makeText(this, R.string.invalidLogin,
							Toast.LENGTH_LONG).show();
			}
			// displaying an error message if the list is empty
			else
				Toast.makeText(this, R.string.invalidLogin, Toast.LENGTH_LONG)
						.show();

		}
		/* if the create account button is clicked */
		else if (view == createAccountButton) {
			// go to the registration page
			int userListID = -1;
			Intent intent = new Intent(this, RegisterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
		/* if forgot password button is clicked */
		else if (view == forgotPasswordButton) {
			// go to the retrieve password page
			int userListID = -1;
			Intent intent = new Intent(this, LostPasswordActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}

}
