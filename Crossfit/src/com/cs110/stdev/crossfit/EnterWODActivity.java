package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;
import com.cs110.stdev.crossfit.backend.WOD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/* make a linked list of exercise edittexts and then use an i to keep
 * track of how many have been used and we can use that to get the texts
 * from each one by indexing into the linked list
 */

public class EnterWODActivity extends Activity implements OnClickListener {
	Button enterWODButton;
	CheckBox checkBoxBenchmark;
	RadioGroup wodType;
	RadioButton amrap;
	RadioButton tabata;
	RadioButton forTime;
	RadioGroup adjustments;
	RadioButton prescribed;
	RadioButton scaled;
	EditText rounds;
	EditText timeMin;
	EditText timeSec;
	EditText tagsWOD;
	EditText exercise1;
	EditText exercise2;
	EditText exercise3;
	EditText exercise4;
	EditText exercise5;
	EditText exercise6;
	EditText exercise7;
	EditText exercise8;
	EditText exercise1reps;
	EditText exercise2reps;
	EditText exercise3reps;
	EditText exercise4reps;
	EditText exercise5reps;
	EditText exercise6reps;
	EditText exercise7reps;
	EditText exercise8reps;
	EditText exercise1weight;
	EditText exercise2weight;
	EditText exercise3weight;
	EditText exercise4weight;
	EditText exercise5weight;
	EditText exercise6weight;
	EditText exercise7weight;
	EditText exercise8weight;

	// get userListID from intent
	int userListID;
	// get the date of the wod to be entered/edited
	String wod_Date;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterwod);

		enterWODButton = (Button) findViewById(R.id.enterWODButton);
		checkBoxBenchmark = (CheckBox) findViewById(R.id.checkBoxBenchmark);
		amrap = (RadioButton) findViewById(R.id.AMRAP);
		tabata = (RadioButton) findViewById(R.id.Tabata);
		forTime = (RadioButton) findViewById(R.id.forTime);
		wodType = (RadioGroup) findViewById(R.id.wodTypeRadio);
		prescribed = (RadioButton) findViewById(R.id.prescribed);
		scaled = (RadioButton) findViewById(R.id.scaled);
		adjustments = (RadioGroup) findViewById(R.id.prescribedRadio);
		rounds = (EditText) findViewById(R.id.rounds);
		timeMin = (EditText) findViewById(R.id.timeMin);
		timeSec = (EditText) findViewById(R.id.timeSec);
		tagsWOD = (EditText) findViewById(R.id.tagsWOD);
		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		wod_Date = getIntent().getStringExtra("WOD_DATE");

		exercise1 = (EditText) findViewById(R.id.exercise1);
		exercise2 = (EditText) findViewById(R.id.exercise2);
		exercise3 = (EditText) findViewById(R.id.exercise3);
		exercise4 = (EditText) findViewById(R.id.exercise4);
		exercise5 = (EditText) findViewById(R.id.exercise5);
		exercise6 = (EditText) findViewById(R.id.exercise6);
		exercise7 = (EditText) findViewById(R.id.exercise7);
		exercise8 = (EditText) findViewById(R.id.exercise8);
		exercise1reps = (EditText) findViewById(R.id.exercise1reps);
		exercise2reps = (EditText) findViewById(R.id.exercise2reps);
		exercise3reps = (EditText) findViewById(R.id.exercise3reps);
		exercise4reps = (EditText) findViewById(R.id.exercise4reps);
		exercise5reps = (EditText) findViewById(R.id.exercise5reps);
		exercise6reps = (EditText) findViewById(R.id.exercise6reps);
		exercise7reps = (EditText) findViewById(R.id.exercise7reps);
		exercise8reps = (EditText) findViewById(R.id.exercise8reps);
		exercise1weight = (EditText) findViewById(R.id.exercise1weight);
		exercise2weight = (EditText) findViewById(R.id.exercise2weight);
		exercise3weight = (EditText) findViewById(R.id.exercise3weight);
		exercise4weight = (EditText) findViewById(R.id.exercise4weight);
		exercise5weight = (EditText) findViewById(R.id.exercise5weight);
		exercise6weight = (EditText) findViewById(R.id.exercise6weight);
		exercise7weight = (EditText) findViewById(R.id.exercise7weight);
		exercise8weight = (EditText) findViewById(R.id.exercise8weight);

		enterWODButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
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

		// Create a new WOD object to be added
		WOD theWOD = new WOD();
		// Set it's fields from input
		theWOD.setDate(wod_Date);
		theWOD.setBenchmark(checkBoxBenchmark.isChecked());
		// Set each exercise and it's reps and weight
		try {
			String exercise = exercise1.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise1reps.getText().toString()));
				System.out.println(Integer.parseInt(exercise1reps.getText()
						.toString()));
				for (int h = 0; h < theWOD.getReps().size(); h++) {
					System.out.println(theWOD.getReps().get(h));
				}
				theWOD.getWeight().add(
						Double.parseDouble(exercise1weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise2.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise2reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise2weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise3.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise3reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise3weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise4.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise4reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise4weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise5.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise5reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise5weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise6.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise6reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise6weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise7.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise7reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise7weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}
		try {
			String exercise = exercise8.getText().toString().toUpperCase()
					.trim();
			if (!exercise.equals("")) {
				theWOD.getExercises().add(exercise);
				theWOD.getReps().add(
						Integer.parseInt(exercise8reps.getText().toString()));
				theWOD.getWeight().add(
						Double.parseDouble(exercise8weight.getText().toString()));
			}
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(
					this,
					"Please Enter Only Numbers into the Reps and Weight Fields",
					Toast.LENGTH_LONG).show();
		}

		// setting the type of the workout
		switch (wodType.getCheckedRadioButtonId()) {
		case 0:
			theWOD.setType("AMRAP");
		case 1:
			theWOD.setType("Tabata");
		case 2:
			theWOD.setType("For Time");
		}

		// setting the adjustments of the workout
		switch (adjustments.getCheckedRadioButtonId()) {
		case 0:
			theWOD.setScaled(false);
		case 1:
			theWOD.setScaled(true);
		}

		// setting the number of rounds
		try {
			theWOD.setRounds(Integer.parseInt(rounds.getText().toString()));
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(this,
					"Please Enter Only Numbers into Rounds and Time",
					Toast.LENGTH_LONG).show();
		}
		// setting the time in minutes
		try {
			theWOD.setTimeMin(Integer.parseInt(timeMin.getText().toString()));
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(this,
					"Please Enter Only Numbers into Rounds and Time",
					Toast.LENGTH_LONG).show();
		}
		// setting the time in seconds
		try {
			theWOD.setTimeSec(Integer.parseInt(timeSec.getText().toString()));
		} catch (java.lang.NumberFormatException nfe) {
			Toast.makeText(this,
					"Please Enter Only Numbers into Rounds and Time",
					Toast.LENGTH_LONG).show();
		}

		String tags = tagsWOD.getText().toString();
		// set list of tags
		LinkedList<String> theTags = new LinkedList<String>();
		String tokens[] = tags.split(",");

		for (int j = 0; j < tokens.length; j++) {
			theTags.add(tokens[j]);
		}
		
		// Add new wod to the database
		user.getMyLog().getWods().add(theWOD);
		user.getMyLog().sortWodByDate();
		for(int k=0;k<theWOD.getExercises().size();k++){
			System.out.println(theWOD.getExercises().get(k));
		}
		System.out.println("Exercise List Size: " + theWOD.getExercises().size());
		System.out.println("WOD DATE in ENTER WOD: " + wod_Date);
		
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

		Intent intent = new Intent(this, ViewWODActivity.class);
		intent.putExtra("enteredWOD_Date", wod_Date);
		intent.putExtra("USER_LIST_ID", userListID);
		startActivity(intent);
	}

}
