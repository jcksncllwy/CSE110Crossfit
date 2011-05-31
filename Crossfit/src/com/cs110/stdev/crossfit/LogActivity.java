package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;
import com.cs110.stdev.crossfit.backend.WOD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	@SuppressWarnings("unchecked")
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

		LinkedList<User> userlist = new LinkedList<User>();
		String filename = "user.ser";
		// pulling the user from the database
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

		if (!userlist.get(userListID).getMyLog().getWods().isEmpty()) {
			lastcompletedwod.setText("Last recorded WOD: "
					+ userlist.get(userListID).getMyLog().getWods().getLast()
							.toDate());
		}
		enterwodButton.setOnClickListener(this);
		viewwodButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == enterwodButton) {
			// getting the day of the WOD
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

			WOD theWOD = userlist.get(userListID).getMyLog()
					.getWODAtDate(wod_Date);
			// checking if a WOD has already been entered for that day or not
			if (theWOD == null) {
				Intent intent = new Intent(this, EnterWODActivity.class);
				intent.putExtra("USER_LIST_ID", userListID);
				intent.putExtra("WOD_DATE", wod_Date);
				intent.putExtra("USER_LIST_ID", userListID);
				startActivity(intent);
			} else
				Toast.makeText(
						this,
						"You have already recorded a WOD for this day! You can edit it or delete it and enter a new one.",
						Toast.LENGTH_LONG).show();
		} else if (view == viewwodButton) {
			// getting the day of the WOD to be viewed
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

			LinkedList<User> userlist = new LinkedList<User>();
			String filename = "user.ser";
			// pulling the user from the database
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

			WOD theWOD = userlist.get(userListID).getMyLog()
					.getWODAtDate(wod_Date);

			// checking that there is a WOD entered for the day to be viewed
			if (theWOD != null) {
				Intent intent = new Intent(this, ViewWODActivity.class);
				intent.putExtra("WOD_DATE", wod_Date);
				intent.putExtra("USER_LIST_ID", userListID);
				startActivity(intent);
			} else
				Toast.makeText(this, "No WODS entered for that day!",
						Toast.LENGTH_SHORT).show();
		}
	}
}
