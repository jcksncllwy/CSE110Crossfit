package com.cs110.stdev.crossfit;

import com.cs110.stdev.crossfit.backend.User;
import java.io.*;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	/* define used components */
	Button createAccountButton;
	EditText usernameEdit;
	EditText passwordEdit;
	EditText confirmPasswordEdit;
	EditText emailEdit;
	EditText confirmEmailEdit;
	EditText securityEdit;
	EditText securityAnsEdit;
	TextView usernameText;
	TextView passwordText;
	TextView confirmPasswordText;
	TextView emailText;
	TextView confirmEmailText;
	TextView securityText;
	TextView securityAnswerText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting the view
		setContentView(R.layout.register);

		/* assign components */
		usernameText = (TextView) findViewById(R.id.usernameText);
		passwordText = (TextView) findViewById(R.id.passwordText);
		confirmPasswordText = (TextView) findViewById(R.id.confirmPasswordText);
		emailText = (TextView) findViewById(R.id.emailText);
		confirmEmailText = (TextView) findViewById(R.id.confirmEmailText);
		securityText = (TextView) findViewById(R.id.securityText);
		securityAnswerText = (TextView) findViewById(R.id.securityAnswerText);

		usernameEdit = (EditText) findViewById(R.id.usernameEdit);
		passwordEdit = (EditText) findViewById(R.id.passwordEdit);
		confirmPasswordEdit = (EditText) findViewById(R.id.confirmPasswordEdit);
		emailEdit = (EditText) findViewById(R.id.emailEdit);
		confirmEmailEdit = (EditText) findViewById(R.id.confirmEmailEdit);
		securityEdit = (EditText) findViewById(R.id.securityEdit);
		securityAnsEdit = (EditText) findViewById(R.id.securityAnsEdit);
		createAccountButton = (Button) findViewById(R.id.createAccountButton);

		// creating an action for when the button is pressed
		createAccountButton.setOnClickListener(this);
	}

	/**
	 * The method to handle when the create account button is clicked.
	 */
	public void onClick(View view) {
		// a variable to check if all the information entered is valid
		boolean valid = true;

		// the user to be added to the database given that everything is valid
		User theuser = new User();

		/* getting the user name and checking that it's valid */
		String username = usernameEdit.getText().toString();

		if (theuser.validateUsername(username))
			theuser.setUsername(username);
		else
			valid = false;

		/* getting the password and checking that it's valid */
		String password = passwordEdit.getText().toString();
		if (theuser.validatePassword(password))
			theuser.setPassword(password);
		else
			valid = false;

		/* getting the security question and checking it's valid */
		String securityQuestion = securityEdit.getText().toString();
		// need to check security question
		theuser.setSecretQ(securityQuestion);

		/* getting the security answer and checking it's valid */
		String securityAnswer = securityAnsEdit.getText().toString();
		// need to check the security answer
		theuser.setSecretA(securityAnswer);

		/*
		 * don't worry about how this works, this just stores it into the
		 * database. just remember to make a LinkedList of Users and then add
		 * the user to it. Also, before adding it, we need to make sure all the
		 * stuff is valid. So if there is a field that isn't valid do not do let
		 * this happen or the intent stuff. Just leave it for now and I'll do
		 * the else part -- basically i'll display a message saying that there's
		 * a field that's invalid.
		 */
		if (valid) {
			LinkedList<User> userlist = new LinkedList<User>();
			userlist.add(theuser);
			String filename = "user.ser";

			try {
				FileOutputStream fos = openFileOutput(filename,
						Context.MODE_PRIVATE);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(userlist);
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			Intent i = new Intent(this, EditProfileActivity.class);
			startActivity(i);
		} else
			// display a message to the user that the fields are invalid
			Toast.makeText(this, R.string.invalidRegistration,
					Toast.LENGTH_LONG).show();
	}

}
