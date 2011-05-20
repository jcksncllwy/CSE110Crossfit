package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class NewProfileActivity extends Activity implements OnClickListener {
	
	/* define all components */
	Button makeProfile;
	EditText fnameEdit;
	EditText mnameEdit;
	EditText lnameEdit;
	EditText weightEdit;
	EditText heightEdit;
	EditText bodyfatEdit;
	DatePicker birthday;
	TextView fname;
	TextView mname;
	TextView lname;
	TextView profInfo;
	TextView birthdayText;
	TextView weightText;
	TextView heightText;
	TextView bodyfatText;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.makeprofile);
	    
	    /* assign used components */
	    makeProfile = (Button) findViewById(R.id.makeProfile);
	    fnameEdit = (EditText) findViewById(R.id.fnameEdit);
	    mnameEdit = (EditText) findViewById(R.id.mnameEdit);
	    lnameEdit = (EditText) findViewById(R.id.lnameEdit);
	    weightEdit = (EditText) findViewById(R.id.weightEdit);
	    heightEdit = (EditText) findViewById(R.id.heightEdit);
	    bodyfatEdit = (EditText) findViewById(R.id.bodyfatEdit);
	    birthday = (DatePicker) findViewById(R.id.birthday);
	    
	    //give the button functionality
	    makeProfile.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(this, TabHoster.class);
		startActivity(i);	
	}

}
