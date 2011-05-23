package com.cs110.stdev.crossfit;

import com.cs110.stdev.crossfit.backend.User;
import java.io.*;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity implements OnClickListener {
	
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
	    setContentView(R.layout.register);
	    
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
	    
	    createAccountButton.setOnClickListener(this);
	    
	    String filename = "user.ser";
	    
		try {
			FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(/*insert the list here*/);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void onClick(View v) {
		
		User theuser = new User();
		String username = usernameEdit.getText().toString();
		theuser.setUsername(username);
		
		LinkedList<User> userlist = new LinkedList<User>();
		userlist.add(theuser);
		
		
		
		Intent i = new Intent(this, NewProfileActivity.class);
		startActivity(i);
	}

}
