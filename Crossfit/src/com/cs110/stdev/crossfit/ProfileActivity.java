package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity implements OnClickListener {

	/* defining used components */
	TextView thenameText;
	TextView birthdayText;
	TextView theageText;
	TextView heightText;
	TextView weightText;
	TextView thebmiText;
	TextView bodyfatText;
	Button editProfileButton;
	
	//get userListID from intent
	int userListID = getIntent().getIntExtra("USER_LIST_ID", -1);

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		/* assigning components */
		thenameText = (TextView) findViewById(R.id.thenameText);
		birthdayText = (TextView) findViewById(R.id.birthdayText);
		theageText = (TextView) findViewById(R.id.theageText);
		heightText = (TextView) findViewById(R.id.heightText);
		weightText = (TextView) findViewById(R.id.weightText);
		thebmiText = (TextView) findViewById(R.id.thebmiText);
		bodyfatText = (TextView) findViewById(R.id.bodyfatText);
		editProfileButton = (Button) findViewById(R.id.editProfileButton);

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

		User temp = userlist.get(userListID);

		/* displaying the profile information */
		thenameText.setText("Name: " + temp.getFirstName() + " "
				+ temp.getLastName());
		birthdayText.setText("Birthday: " + temp.printBirthday());
		theageText.setText("Age: " + temp.getAge());
		if (!temp.getMyLog().getBiometrics().isEmpty()) {
			heightText.setText("Height: "
					+ temp.getMyLog().getBiometrics().getLast().printHeight());
			weightText.setText("Weight: "
					+ temp.getMyLog().getBiometrics().getLast().getWeight());
			thebmiText.setText("BMI: "
					+ (int) temp.getMyLog().getBiometrics().getLast().getbMI());
			bodyfatText.setText("Body Fat %: "
					+ temp.getMyLog().getBiometrics().getLast().getbodyFat());
		} else {
			heightText.setText("Height: ");
			weightText.setText("Weight: ");
			thebmiText.setText("BMI: ");
			bodyfatText.setText("Body Fat %: ");
		}
		// handle clicking events
		editProfileButton.setOnClickListener(this);
	}

	/* handles clicking events */
	@Override
	public void onClick(View view) {
		// go to the edit profile page when the button is clicked
		if (view == editProfileButton) {
			Intent intent = new Intent(this, EditProfileActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}

}
