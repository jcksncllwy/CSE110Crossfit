package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.Biometric;
import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class BiometricTabActivity extends Activity implements OnClickListener {

	Button addBiometric;
	Button viewBiometric;
	DatePicker bioDatePicker;
	TextView lastBiometric;
	
	int userListID;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.biometrictab);
		addBiometric = (Button) findViewById(R.id.addBiometric);
		viewBiometric = (Button) findViewById(R.id.viewBiometric);
		bioDatePicker = (DatePicker) findViewById(R.id.bioDatePicker);
		lastBiometric = (TextView) findViewById(R.id.lastBiometric);
		
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

		if (!userlist.get(userListID).getMyLog().getBiometrics().isEmpty()) {
			lastBiometric.setText("Last recorded Biometric: "
					+ userlist.get(userListID).getMyLog().getBiometrics().getLast()
							.toDate());
		}
		
		addBiometric.setOnClickListener(this);
		viewBiometric.setOnClickListener(this);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void onClick(View view) {
		if (view == addBiometric) {
			// getting the day of the biometrics
			int day = (int) bioDatePicker.getDayOfMonth();
			int month = (int) bioDatePicker.getMonth() + 1;
			int year = (int) bioDatePicker.getYear();

			String theDay = Integer.toString(day);
			String theMonth = Integer.toString(month);

			if (day < 10)
				theDay = "0" + Integer.toString(day);

			if (month < 10)
				theMonth = "0" + Integer.toString(month);

			String bio_Date = theMonth + theDay + year;

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

			Biometric theBio = userlist.get(userListID).getMyLog()
					.getBioAtDate(bio_Date);

			if (theBio == null) {
				Intent intent = new Intent(this, EnterBiometricActivity.class);
				intent.putExtra("USER_LIST_ID", userListID);
				intent.putExtra("BIO_DATE", bio_Date);
				startActivity(intent);
			} else
				Toast.makeText(
						this,
						"You have already recorded your biometrics for this day! You can edit it or delete it and enter a new one.",
						Toast.LENGTH_LONG).show();
		} else if (view == viewBiometric) {
			// getting the day of the Biometric to be viewed
			int day = (int) bioDatePicker.getDayOfMonth();
			int month = (int) bioDatePicker.getMonth() + 1;
			int year = (int) bioDatePicker.getYear();

			String theDay = Integer.toString(day);
			String theMonth = Integer.toString(month);

			if (day < 10)
				theDay = "0" + Integer.toString(day);

			if (month < 10)
				theMonth = "0" + Integer.toString(month);

			String bio_Date = theMonth + theDay + year;

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

			Biometric theBio = userlist.get(userListID).getMyLog()
					.getBioAtDate(bio_Date);

			if (theBio != null) {
				Intent intent = new Intent(this, ViewBiometricsActivity.class);
				intent.putExtra("USER_LIST_ID", userListID);
				intent.putExtra("BIO_DATE", bio_Date);
				startActivity(intent);
			}
			else
				Toast.makeText(this, "No biometrics entered for that day!",
						Toast.LENGTH_SHORT).show();
		}
	}
}
