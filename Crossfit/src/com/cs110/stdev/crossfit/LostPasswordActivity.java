package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LostPasswordActivity extends Activity implements OnClickListener {

	/* define components */
	EditText enterUsernameLost;
	TextView secQuestText;
	EditText enterSecurityAnswer;
	Button recoverPasswordButton;
	Button goBackButton;
	Button retrieveSQ;
	TextView lostPassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// display the xml formatted page
		setContentView(R.layout.forgotpassword);

		/* assign components */
		enterUsernameLost = (EditText) findViewById(R.id.enterUsernameLost);
		secQuestText = (TextView) findViewById(R.id.secQuestText);
		enterSecurityAnswer = (EditText) findViewById(R.id.enterSecurityAnswer);
		recoverPasswordButton = (Button) findViewById(R.id.recoverPasswordButton);
		goBackButton = (Button) findViewById(R.id.goBackButton);
		retrieveSQ = (Button) findViewById(R.id.retrieveSQ);
		lostPassword = (TextView) findViewById(R.id.lostPassword);

		/* button action */
		retrieveSQ.setOnClickListener(this);
		recoverPasswordButton.setOnClickListener(this);
		goBackButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == retrieveSQ) {
			String username = enterUsernameLost.getText().toString();
			Log.d("username value", username);
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

			boolean usernameExists = false;

			if (!userlist.isEmpty()) {
				for (int i = 0; i < userlist.size(); ++i) {
					if (username.equals(userlist.get(i).getUsername())) {
						secQuestText.setText(userlist.get(i).getSecretQ());
						usernameExists = true;
					}
				}
				if (!usernameExists) {
					Toast.makeText(this, R.string.invalidUsername,
							Toast.LENGTH_LONG).show();
				}
			} else
				Toast.makeText(this, R.string.invalidUsername,
						Toast.LENGTH_LONG).show();

		} else if (view == recoverPasswordButton) {
			String username = enterUsernameLost.getText().toString();
			String securityA = enterSecurityAnswer.getText().toString();

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
			boolean validAnswer = false;

			// check that there is actually a user in the database
			if (!userlist.isEmpty() && username != null) {
				for (int i = 0; i < userlist.size(); i++) {
					user = userlist.get(i);
					// checking that all the fields match
					if (username.equals(user.getUsername())
							&& securityA.equals(user.getSecretA())) {
						lostPassword.setText("Password: " + user.getPassword());
						validAnswer = true;
					}
				}
				// error message
				// else
				if (!validAnswer)
					Toast.makeText(this, R.string.invalidSecretA,
							Toast.LENGTH_LONG).show();
			}
			// error message
			else
				Toast.makeText(this, R.string.invalidUsername,
						Toast.LENGTH_LONG).show();

		}
		/* if the user clicks go back, go back to the login page */
		else if (view == goBackButton) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
	}
}