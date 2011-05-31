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
import android.widget.EditText;
import android.widget.Toast;

public class EnterBiometricActivity extends Activity implements OnClickListener {

	/* define components */
	EditText bioWeightEdit;
	EditText bioBFEdit;
	EditText bioHREdit;
	EditText bioTagEdit;
	EditText bioHeightEdit;
	Button bioSubmit;
	Button bioCancel;

	int userListID;
	int edit;
	String bio_Date;
	
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
		bioSubmit = (Button) findViewById(R.id.bioSubmit);
		bioCancel = (Button) findViewById(R.id.bioCancel);

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		bio_Date = getIntent().getStringExtra("BIO_DATE");
		edit = getIntent().getIntExtra("EDIT",-1);
		
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
		
		if(edit>0){
			Biometric editBio = user.getMyLog().getBioAtDate(bio_Date);
			
			Double editWeight = editBio.getWeight();
			bioWeightEdit.setText(editWeight.toString());
			
			Double editBF = editBio.getbodyFat();
			bioBFEdit.setText(editBF.toString());
			
			Integer editHR = editBio.getHeartRate();
			bioHREdit.setText(editHR.toString());
			
			Double editHeight = editBio.getHeight();
			bioHeightEdit.setText(editHeight.toString());
			
			String tagsString = "";
			for (int r = 0; r < editBio.getTags().size(); r++)
				tagsString += editBio.getTags().get(r) + ",";

			bioTagEdit.setText(tagsString);
		}
		else if (!user.getMyLog().getBiometrics().isEmpty()) {
			Double height = userlist.get(userListID).getMyLog().getBiometrics()
					.getLast().getHeight();
			bioHeightEdit.setText(height.toString());
		}

		bioSubmit.setOnClickListener(this);
		bioCancel.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == bioSubmit) {
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

					if (bio_Date.length() > 0) {
						bio.setDate(bio_Date);
					} else {
						valid = false;
					}

					bio.setBMI();

					bio.autoTag();

					// set list of tags
					String tokens[] = tags.split(",");

					for (int j = 0; j < tokens.length; j++) {
						bio.addTag(tokens[j]);
					}

					// checking for a valid entry
					if (valid) {
						
						if (edit > 0) {
							for (int g = 0; g < user.getMyLog().getBiometrics().size(); g++) {
								if (bio_Date.equals(user.getMyLog().getBiometrics().get(g)
										.getDate()))
									user.getMyLog().getBiometrics().remove(g);
							}
						}
						
						user.getMyLog().addBioSort(bio);

						// storing changes in the database
						try {
							FileOutputStream fos = openFileOutput(filename,
									Context.MODE_PRIVATE);
							ObjectOutputStream out = new ObjectOutputStream(fos);
							out.writeObject(userlist);
							out.close();
						} catch (IOException ex) {
							ex.printStackTrace();
						}

						Toast.makeText(this,
								"Your biometrics have been recorded!",
								Toast.LENGTH_SHORT).show();
						// going back to the main tab pages
						Intent intent = new Intent(this,
								ViewBiometricsActivity.class);
						intent.putExtra("USER_LIST_ID", userListID);
						intent.putExtra("BIO_DATE",bio_Date);
						startActivity(intent);

					} else
						Toast.makeText(this,
								"Please enter only positive numerical values!",
								Toast.LENGTH_SHORT).show();
				}
			}
			// catching for when there is no numerical value entered
			catch (NumberFormatException exception) {
				Toast.makeText(
						this,
						"Enter a numerical"
								+ " value for weight/body fat percentage"
								+ "/heart rate/height!", Toast.LENGTH_SHORT)
						.show();
			}
		}
		else if(view == bioCancel){
			Intent intent = new Intent(this,TabHosterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}
}
