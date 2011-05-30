package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.Biometric;
import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class BiometricActivity extends Activity implements OnClickListener {

	/* define components */
	EditText bioWeightEdit;
	EditText bioBFEdit;
	EditText bioHREdit;
	EditText bioTagEdit;
	EditText bioHeightEdit;
	DatePicker dayPicker;
	Button bioSubmit;

	int userListID;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.biometric);

		bioWeightEdit = (EditText) findViewById(R.id.bioWeightEdit);
		bioBFEdit = (EditText) findViewById(R.id.bioBFEdit);
		bioHREdit = (EditText) findViewById(R.id.bioHREdit);
		bioTagEdit = (EditText) findViewById(R.id.bioTagEdit);
		bioHeightEdit = (EditText) findViewById(R.id.bioHeightEdit);
		dayPicker = (DatePicker) findViewById(R.id.dayPicker);
		bioSubmit = (Button) findViewById(R.id.bioSubmit);

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);

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

		if (!userlist.isEmpty()) {
			if (!userlist.get(userListID).getMyLog().getBiometrics().isEmpty()) {
				Double height = userlist.get(userListID).getMyLog()
						.getBiometrics().getLast().getHeight();
				bioHeightEdit.setText(height.toString());
			}
		}

		bioSubmit.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		boolean valid = true; // variable to keep track of valid entries

		double theWeight = -1;
		double theHeight = -1;
		double theBF = -1;
		int theHR = -1;

		String weight = bioWeightEdit.getText().toString();
		String bodyFat = bioBFEdit.getText().toString();
		String heartRate = bioHREdit.getText().toString();
		String height = bioHeightEdit.getText().toString();
		String tags = bioTagEdit.getText().toString();

		int day = (int) dayPicker.getDayOfMonth();
		int month = (int) dayPicker.getMonth() + 1;
		int year = (int) dayPicker.getYear();
		String date = Integer.toString(month) + Integer.toString(day)
				+ Integer.toString(year);

		// try to parse the fields that are numbers
		try {
			theWeight = Double.parseDouble(weight);
			theBF = Double.parseDouble(bodyFat);
			theHR = Integer.parseInt(heartRate);
			theHeight = Double.parseDouble(height);

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

			User user = new User();

			if (!userlist.isEmpty()) {
				user = userlist.get(userListID);
				Biometric bio = new Biometric();

				// set weight
				if (theWeight > 0) {
					bio.setWeight(theWeight);
				} else {
					valid = false;
				}

				// set body fat percentage
				if (theBF > 0) {
					bio.setBodyFat(theBF);
				} else {
					valid = false;
				}

				// set heart rate
				if (theHR > 0) {
					bio.setHeartRate(theHR);
				} else {
					valid = false;
				}

				if (theHeight > 0)
					bio.setHeight(theHeight);
				else
					valid = false;

				// set list of tags
				LinkedList<String> theTags = new LinkedList<String>();
				String tokens[] = tags.split(",");

				for (int j = 0; j < tokens.length; j++) {
					theTags.add(tokens[j]);
				}

				bio.setTags(theTags);
				bio.setBMI();
				
				// set date
				if (date.length() > 0) {
					bio.setDate(date);
				} else {
					valid = false;
				}

				bio.autoTag();
				/*
				 * putting in the user back in the database
				 */
				if (valid) {
					// add biometrics entry to user's list of biometrics
					// could be a problem?????????????????????????????????
					user.getMyLog().getBiometrics().add(bio);

					try {
						FileOutputStream fos = openFileOutput(filename,
								Context.MODE_PRIVATE);
						ObjectOutputStream out = new ObjectOutputStream(fos);
						out.writeObject(userlist);
						out.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					Toast.makeText(this, "Your biometrics have been recorded!",
							Toast.LENGTH_LONG).show();

					Intent intent = new Intent(this, TabHosterActivity.class);
					intent.putExtra("USER_LIST_ID", userListID);
					startActivity(intent);

				} else
					Toast.makeText(this,
							"Please enter only positive numerical values!",
							Toast.LENGTH_LONG).show();
			}
		}
		// catching for when there is no numerical value entered
		catch (NumberFormatException exception) {
			Toast.makeText(
					this,
					"Enter a numerical"
							+ " value for weight/body fat percentage"
							+ "/heart rate/height!", Toast.LENGTH_LONG).show();
		}
	}

}
