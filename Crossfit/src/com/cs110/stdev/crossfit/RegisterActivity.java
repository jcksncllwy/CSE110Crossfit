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
		securityText = (TextView) findViewById(R.id.securityText);
		securityAnswerText = (TextView) findViewById(R.id.securityAnswerText);

		usernameEdit = (EditText) findViewById(R.id.usernameEdit);
		passwordEdit = (EditText) findViewById(R.id.passwordEdit);
		confirmPasswordEdit = (EditText) findViewById(R.id.confirmPasswordEdit);
		securityEdit = (EditText) findViewById(R.id.securityEdit);
		securityAnsEdit = (EditText) findViewById(R.id.securityAnsEdit);
		createAccountButton = (Button) findViewById(R.id.createAccountButton);

		// creating an action for when the button is pressed
		createAccountButton.setOnClickListener(this);
	}

	/**
	 * The method to handle when the create account button is clicked.
	 */
	@SuppressWarnings("unchecked")
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
		String confirmPass = confirmPasswordEdit.getText().toString();
		if (theuser.validatePassword(password)
				&& theuser.validatePassword(confirmPass)
				&& confirmPass.equals(password))
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
		 * pulling user from database
		 */
		if (valid) {
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

			userlist.add(theuser);

			try {
				FileOutputStream fos = openFileOutput(filename,
						Context.MODE_PRIVATE);
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(userlist);
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			int userListID = userlist.indexOf(theuser);
			Intent i = new Intent(this, EditProfileActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			startActivity(i);
		} else
			// display a message to the user that the fields are invalid
			Toast.makeText(this, R.string.invalidRegistration,
					Toast.LENGTH_LONG).show();
	}

}
