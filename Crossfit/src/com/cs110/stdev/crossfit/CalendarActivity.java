package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CalendarActivity extends Activity implements OnClickListener {

	Button enterwodButton;
	Button viewwodButton;
	TextView lastcompletedwod;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);

		enterwodButton = (Button) findViewById(R.id.enterwodButton);
		viewwodButton = (Button) findViewById(R.id.viewwodButton);
		
		lastcompletedwod = (TextView) findViewById(R.id.lastcompletedwod);
		lastcompletedwod.setText("Last completed WOD: ");

		enterwodButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==enterwodButton){
			Intent i = new Intent(this, EnterWODActivity.class);
			startActivity(i);
		}
		else if(v==viewwodButton){
			Intent i = new Intent(this, ViewWODActivity.class);
			startActivity(i);
		}
	}
}
