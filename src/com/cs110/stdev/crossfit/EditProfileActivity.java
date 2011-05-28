package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

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
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends Activity implements OnClickListener {

	/* define all components */
	Button makeProfile;
	Button thebdaybutton;
	Button cancel;
	EditText fnameEdit;
	EditText lnameEdit;
	DatePicker birthday;
	TextView birthdayText;
	
	//get userListID from intent
	int userListID = getIntent().getIntExtra("USER_LIST_ID", -1);

	private boolean changeDate;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editprofile);

		/* assign used components */
		makeProfile = (Button) findViewById(R.id.makeProfile);
		cancel = (Button) findViewById(R.id.cancel);
		fnameEdit = (EditText) findViewById(R.id.fnameEdit);
		lnameEdit = (EditText) findViewById(R.id.lnameEdit);
		birthday = (DatePicker) findViewById(R.id.birthday);
		// could be a problem
		thebdaybutton = (Button) findViewById(R.id.thebdaybutton);
		birthdayText = (TextView) findViewById(R.id.birthdayText);

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
			user = userlist.get(userListID);

			/* displaying his current profile information */
			if (!user.getFirstName().equals(""))
				fnameEdit.setText(user.getFirstName());
			if (!user.getLastName().equals(""))
				lnameEdit.setText(user.getLastName());
			if (!user.getBirthday().equals(""))
				birthdayText.setText("Birthday: " + user.printBirthday());
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
			boolean valid = true; // variable to keep track of valid entries

			String fname = fnameEdit.getText().toString();
			String lname = lnameEdit.getText().toString();

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
				if (fname != null && fname.length() > 1 && fname.length() < 20) {
					userlist.get(userListID).setFirstName(fname);
				} else
					valid = false;

				// checking last name
				if (lname != null && lname.length() > 1 && lname.length() < 20) {
					userlist.get(userListID).setLastName(lname);
				} else
					valid = false;

				if (changeDate) {
					int day = (int) birthday.getDayOfMonth();
					int month = (int) birthday.getMonth() + 1;
					int year = (int) birthday.getYear();

					String theDay = Integer.toString(day);
					String theMonth = Integer.toString(month);

					if (day < 10)
						theDay = "0" + Integer.toString(day);

					if (month < 10)
						theMonth = "0" + Integer.toString(month);

					String theBirthday = theMonth + theDay + year;
					userlist.get(userListID).setBirthday(theBirthday);
					userlist.get(userListID).setAge();
				}

				/*
				 * putting in the user back in the database
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

					Intent intent = new Intent(this, TabHosterActivity.class);
					intent.putExtra("USER_LIST_ID", userListID);
					startActivity(intent);
				}
				// displaying error message to the user
				else
					Toast.makeText(this, R.string.invalidRegistration,
							Toast.LENGTH_LONG).show();
			}
		}

	}

}
