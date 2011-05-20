package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	    
	}
	
	public void onClick(View v) {
		Intent i = new Intent(this, NewProfileActivity.class);
		startActivity(i);
	}

}
