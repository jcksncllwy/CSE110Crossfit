package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends Activity implements OnClickListener {

	/* define all components */
	Button makeProfile;
	Button thebdaybutton;
	EditText fnameEdit;
	EditText lnameEdit;
	EditText weightEdit;
	EditText heightEdit;
	EditText bodyfatEdit;
	DatePicker birthday;
	TextView fname;
	TextView lname;
	TextView profInfo;
	TextView birthdayText;
	TextView weightText;
	TextView heightText;
	TextView bodyfatText;

	private boolean changeDate;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editprofile);

		/* assign used components */
		makeProfile = (Button) findViewById(R.id.makeProfile);
		fnameEdit = (EditText) findViewById(R.id.fnameEdit);
		lnameEdit = (EditText) findViewById(R.id.lnameEdit);
		weightEdit = (EditText) findViewById(R.id.weightEdit);
		heightEdit = (EditText) findViewById(R.id.heightEdit);
		bodyfatEdit = (EditText) findViewById(R.id.bodyfatEdit);
		birthday = (DatePicker) findViewById(R.id.birthday);
		// could be a prblem
		thebdaybutton = (Button) findViewById(R.id.thebdaybutton);

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

		// checking if there is a user
		if (!userlist.isEmpty()) {
			user = userlist.get(0);

			/* displaying his current profile information */
			if (!user.getFirstName().equals(""))
				fnameEdit.setText(user.getFirstName());
			if (!user.getLastName().equals(""))
				lnameEdit.setText(user.getLastName());
			if (!(user.getWeight() == 0)) {
				Double userWeight = user.getWeight();
				weightEdit.setText(userWeight.toString());
			}
			if (!((user.getHeight()) == 0)) {
				Double userHeight = user.getHeight();
				heightEdit.setText(userHeight.toString());
			}
			if (!(user.getBodyFat() == 0)) {
				Double userBF = user.getBodyFat();
				bodyfatEdit.setText(userBF.toString());
			}
		}

		// give the button functionality
		makeProfile.setOnClickListener(this);
		thebdaybutton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == thebdaybutton) {
			changeDate = true;
			Toast.makeText(
					this,
					"The changes will be reflected the next time you view your profile.",
					Toast.LENGTH_LONG).show();
		} else if (view == makeProfile) {
			boolean valid = true; // variable to keep track of valid entrie
			double theWeight = -1;
			double theHeight = -1;
			double theBodyFat = -1;

			String fname = fnameEdit.getText().toString();
			String lname = lnameEdit.getText().toString();
			String weight = weightEdit.getText().toString();
			String height = heightEdit.getText().toString();
			String bodyfat = bodyfatEdit.getText().toString();

			// try to parse the fields that are numbers
			try {
				theWeight = Double.parseDouble(weight);
				theHeight = Double.parseDouble(height);
				theBodyFat = Double.parseDouble(bodyfat);

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

				// checking that a user exists
				if (!userlist.isEmpty()) {

					// checking first name
					if (fname != null && fname.length() > 1
							&& fname.length() < 20) {
						userlist.get(0).setFirstName(fname);
					} else
						valid = false;

					// checking last name
					if (lname != null && lname.length() > 1
							&& lname.length() < 20) {
						userlist.get(0).setLastName(lname);
					} else
						valid = false;
					// checking and setting weight
					if (theWeight > 0) {
						userlist.get(0).setWeight(theWeight);
					} else
						valid = false;
					// checking and setting height
					if (theHeight > 0) {
						userlist.get(0).setHeight(theHeight);
					} else
						valid = false;
					// checking and setting body fat percentage
					if (theBodyFat > 0) {
						userlist.get(0).setBodyFat(theBodyFat);
					}

					if (changeDate) {
						int day = (int) birthday.getDayOfMonth();
						int month = (int) birthday.getMonth()+1;
						int year = (int) birthday.getYear();
						Date theBirthday = new Date(year, month, day);
						userlist.get(0).setBirthday(theBirthday);
						userlist.get(0).setAge();
					}

					/*
					 * could be problematic, need to ask about what if you store
					 * again
					 */
					if (valid) {
						try {
							FileOutputStream fos = openFileOutput(filename,
									Context.MODE_PRIVATE);
							ObjectOutputStream out = new ObjectOutputStream(fos);
							out.writeObject(userlist);
							out.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}

						Intent intent = new Intent(this,
								TabHosterActivity.class);
						startActivity(intent);
					}
					// displaying error message to the user
					else
						Toast.makeText(this, R.string.invalidRegistration,
								Toast.LENGTH_LONG).show();
				}
			}
			// catching for when there is no numerical value entered
			catch (NumberFormatException exception) {
				Toast.makeText(
						this,
						"Enter a numerical" + "value for weight/height/body"
								+ "fat percentage!", Toast.LENGTH_LONG).show();
			}

		}
	}
}
