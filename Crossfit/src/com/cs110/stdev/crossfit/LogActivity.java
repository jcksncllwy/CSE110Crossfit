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

	// get userListID from intent
	int userListID;

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

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);

		enterwodButton.setOnClickListener(this);
		viewwodButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view == enterwodButton) {
			Intent intent = new Intent(this, EnterWODActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);

			int day = (int) wodDay.getDayOfMonth();
			int month = (int) wodDay.getMonth() + 1;
			int year = (int) wodDay.getYear();

			String theDay = Integer.toString(day);
			String theMonth = Integer.toString(month);

			if (day < 10)
				theDay = "0" + Integer.toString(day);

			if (month < 10)
				theMonth = "0" + Integer.toString(month);

			String wod_Date = theMonth + theDay + year;

			intent.putExtra("WOD_DATE", wod_Date);
			startActivity(intent);
		} else if (view == viewwodButton) {
			Intent intent = new Intent(this, ViewWODActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			int dayOfMonth = wodDay.getDayOfMonth();
			int month = wodDay.getMonth();
			int year = wodDay.getYear();
			String wod_Date = Integer.toString(month)
					+ Integer.toString(dayOfMonth) + Integer.toString(year);
			intent.putExtra("WOD_DATE", wod_Date);
			startActivity(intent);
		}
	}
}
