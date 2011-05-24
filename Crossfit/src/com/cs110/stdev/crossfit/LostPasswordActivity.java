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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LostPasswordActivity extends Activity implements OnClickListener {

	/* define components */
	EditText enterUsernameLost;
	EditText enterSecurityQuestion;
	EditText enterSecurityAnswer;
	Button recoverPasswordButton;
	Button goBackButton;
	TextView lostPassword;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// display the xml formatted page
		setContentView(R.layout.forgotpassword);

		/* assign components */
		enterUsernameLost = (EditText) findViewById(R.id.enterUsernameLost);
		enterSecurityQuestion = (EditText) findViewById(R.id.enterSecurityQuestion);
		enterSecurityAnswer = (EditText) findViewById(R.id.enterSecurityAnswer);
		recoverPasswordButton = (Button) findViewById(R.id.recoverPasswordButton);
		goBackButton = (Button) findViewById(R.id.goBackButton);
		lostPassword = (TextView) findViewById(R.id.lostPassword);

		/* button action */
		recoverPasswordButton.setOnClickListener(this);
		goBackButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == recoverPasswordButton) {
			/* getting the entries from the edit text boxes */
			String username = enterUsernameLost.getText().toString();
			String securityQ = enterSecurityQuestion.getText().toString();
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
			// check that there is actually a user in the database
			if (!userlist.isEmpty()) {
				user = userlist.get(0);
				// checking that all the fields match
				if (username.equals(user.getUsername())
						&& securityQ.equals(user.getSecretQ())
						&& securityA.equals(user.getSecretA()))
					lostPassword.setText("Password: " + user.getPassword());
			}
			// error message
			else
				Toast.makeText(this, R.string.invalidRegistration,
						Toast.LENGTH_LONG).show();

		}
		/* if the user clicks go back, go back to the login page */
		else if (view == goBackButton) {
			Intent i = new Intent(this, LoginActivity.class);
			startActivity(i);
		}
	}

}
