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
import android.util.Log;
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

	/** Called when the activity is first created. */
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

		User temp = userlist.get(0);
		/* displaying the profile information */
		thenameText.setText("Name: " + temp.getFirstName() + " "
				+ temp.getLastName());
		birthdayText.setText("Birthday: " + temp.getBirthday());
		theageText.setText("Age: " + temp.getAge());
		heightText.setText("Height: " + temp.getHeight());
		weightText.setText("Weight: " + temp.getWeight());
		thebmiText.setText("BMI: ");
		bodyfatText.setText("Body Fat %: " + temp.getBodyFat());

		// handle clicking events
		editProfileButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (view == editProfileButton) {
			Intent i = new Intent(this, EditProfileActivity.class);
			startActivity(i);
		}
	}

}
