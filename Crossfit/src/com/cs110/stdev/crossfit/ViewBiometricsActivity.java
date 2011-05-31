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
import android.widget.TextView;
import android.widget.Toast;

public class ViewBiometricsActivity extends Activity implements OnClickListener {

	TextView bioWeightText;
	TextView bioBFText;
	TextView bioHRText;
	TextView bioHeightText;
	TextView bioBMIText;
	TextView bioTagsText;

	Button editBioButton;
	Button deleteBioButton;
	Button goBackBioButton;

	int userListID;
	String bio_Date;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewbiometrics);

		bioWeightText = (TextView) findViewById(R.id.bioWeightText);
		bioBFText = (TextView) findViewById(R.id.bioBFText);
		bioHRText = (TextView) findViewById(R.id.bioHRText);
		bioHeightText = (TextView) findViewById(R.id.bioHeightText);
		bioBMIText = (TextView) findViewById(R.id.bioBMIText);
		bioTagsText = (TextView) findViewById(R.id.bioTagsText);

		editBioButton = (Button) findViewById(R.id.editBioButton);
		deleteBioButton = (Button) findViewById(R.id.deleteBioButton);
		goBackBioButton = (Button) findViewById(R.id.goBackBioButton);

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		bio_Date = getIntent().getStringExtra("BIO_DATE");

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

		User user = userlist.get(userListID);
		Biometric theBio = user.getMyLog().getBioAtDate(bio_Date);

		Double theWeight = theBio.getWeight();
		bioWeightText.setText("Weight: " + theWeight.toString());

		Double theBF = theBio.getbodyFat();
		bioBFText.setText("Body Fat Percentage: " + theBF.toString());

		Integer theHR = theBio.getHeartRate();
		bioHRText.setText("Resting Heart Rate: " + theHR.toString());

		Double theHeight = theBio.getHeight();
		bioHeightText.setText("Height: " + theHeight.toString());

		Double theBMI = theBio.getbMI();
		bioBMIText.setText("BMI (Body Mass Index): " + theBMI.toString());

		String tags = "Tags: ";
		for (int i = 0; i < theBio.getTags().size(); i++)
			tags += theBio.getTags().get(i) + " ";
		bioTagsText.setText(tags);

		editBioButton.setOnClickListener(this);
		deleteBioButton.setOnClickListener(this);
		goBackBioButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == editBioButton) {
			Intent intent = new Intent(this, EnterWODActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			intent.putExtra("BIO_DATE", bio_Date);
			intent.putExtra("EDIT", 1);
			startActivity(intent);
		} else if (view == deleteBioButton) {
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

			User user = userlist.get(userListID);

			// iterating and looking for the biometrics -- could maybe do a
			// binary
			// search instead
			for (int g = 0; g < user.getMyLog().getBiometrics().size(); g++) {
				if (bio_Date.equals(user.getMyLog().getBiometrics().get(g)
						.getDate())) {
					Toast.makeText(
							this,
							"Your biometrics for "
									+ user.getMyLog().getBiometrics().get(g)
											.toDate() + " have been deleted!",
							Toast.LENGTH_SHORT).show();
					user.getMyLog().getBiometrics().remove(g);
				}
			}

			// serialize changed data
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
		} else if (view == goBackBioButton) {
			Intent intent = new Intent(this, TabHosterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}
}
