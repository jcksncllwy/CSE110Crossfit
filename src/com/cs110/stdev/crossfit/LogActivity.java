package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LogActivity extends Activity implements OnClickListener {
	LinearLayout calendarLayout;
	Button enterwodButton;
	Button viewwodButton;
	Button searchButton;
	DatePicker wodDay;
	TextView lastcompletedwod;
	
	//get userListID from intent
	int userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
	
	static final int ENTER_WOD_REQUEST = 0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log);

		calendarLayout = (LinearLayout) findViewById(R.id.calendarLayout);
		enterwodButton = (Button) findViewById(R.id.enterwodButton);
		viewwodButton = (Button) findViewById(R.id.viewwodButton);
		searchButton = (Button) findViewById(R.id.searchButton);
		wodDay = (DatePicker) findViewById(R.id.wodDay);
		lastcompletedwod = (TextView) findViewById(R.id.lastcompletedwod);
		lastcompletedwod.setText("Last completed WOD: ");
		
		enterwodButton.setOnClickListener(this);
		viewwodButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==enterwodButton){
			Intent i = new Intent(this, EnterWODActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			int dayOfMonth = wodDay.getDayOfMonth();
			int month = wodDay.getMonth();
			int year = wodDay.getYear();
			String wod_Date = Integer.toString(month) + '/' + Integer.toString(dayOfMonth) + '/' + Integer.toString(year);
			i.putExtra("WOD_DATE", wod_Date);
			startActivityForResult(i, ENTER_WOD_REQUEST);
		}
		else if(v==viewwodButton){
			Intent i = new Intent(this, ViewWODActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			startActivity(i);
		}
	}
	
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == ENTER_WOD_REQUEST){
			Intent i = new Intent(this, ViewWODActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			startActivity(i);
		}
		
	}

	
}
