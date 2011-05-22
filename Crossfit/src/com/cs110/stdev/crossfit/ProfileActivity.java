package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends Activity implements OnClickListener{

	TextView thenameText;
	TextView birthdayText;
	TextView theageText;
	TextView heightText;
	TextView weightText;
	TextView thebmiText;
	TextView bodyfatText;
	Button editProfileButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.profile);
	    
	    thenameText = (TextView) findViewById(R.id.thenameText);
		birthdayText = (TextView) findViewById(R.id.birthdayText);
		theageText = (TextView) findViewById(R.id.theageText);
		heightText = (TextView) findViewById(R.id.heightText);
		weightText = (TextView) findViewById(R.id.weightText);
		thebmiText = (TextView) findViewById(R.id.thebmiText);
		bodyfatText = (TextView) findViewById(R.id.bodyfatText);
		editProfileButton = (Button) findViewById(R.id.editProfileButton);

		
		editProfileButton.setOnClickListener(this);
		/*
		thenameText.setText("Name: ");
		birthdayText.setText("Birthday: ");
		theageText.setText("Age: ");
		heightText.setText("Height: ");
		weightText.setText("Weight: ");
		thebmiText.setText("BMI: ");
		bodyfatText.setText("Body Fat %: ");
		*/
	}

	@Override
	public void onClick(View v) {
		if(v==editProfileButton){
			Intent i = new Intent(this,NewProfileActivity.class);
			startActivity(i);
		}
	}

}
