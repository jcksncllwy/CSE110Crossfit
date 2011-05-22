package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ProfileActivity extends Activity {

	TextView thenameText;
	TextView birthdayText;
	TextView theageText;
	TextView heightText;
	TextView weightText;
	TextView thebmiText;
	TextView bodyfatText;
	
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
		
		thenameText.setText("Name: ");
		birthdayText.setText("Birthday: ");
		theageText.setText("Age: ");
		heightText.setText("Height: ");
		weightText.setText("Weight: ");
		thebmiText.setText("BMI: ");
		bodyfatText.setText("Body Fat %: ");
	}

}
