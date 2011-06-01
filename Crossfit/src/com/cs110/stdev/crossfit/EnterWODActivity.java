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
import android.widget.Toast;

/* make a linked list of exercise edittexts and then use an i to keep
 * track of how many have been used and we can use that to get the texts
 * from each one by indexing into the linked list
 */

public class EnterWODActivity extends Activity implements OnClickListener {
	Button enterWODButton;
	Button cancelWOD;
	CheckBox checkBoxBenchmark;
	CheckBox scaledCheck;
	CheckBox amrapCheck;
	EditText rounds;
	EditText timeMin;
	EditText timeSec;
	EditText tagsWOD;
	EditText notesEdit;
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
	int edit;
	// get the date of the wod to be entered/edited
	String wod_Date;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterwod);

		enterWODButton = (Button) findViewById(R.id.enterWODButton);
		cancelWOD = (Button) findViewById(R.id.cancelWOD);
		checkBoxBenchmark = (CheckBox) findViewById(R.id.checkBoxBenchmark);
		scaledCheck = (CheckBox) findViewById(R.id.scaledCheck);
		amrapCheck = (CheckBox) findViewById(R.id.amrapCheck);
		rounds = (EditText) findViewById(R.id.rounds);
		timeMin = (EditText) findViewById(R.id.timeMin);
		timeSec = (EditText) findViewById(R.id.timeSec);
		tagsWOD = (EditText) findViewById(R.id.tagsWOD);
		notesEdit = (EditText) findViewById(R.id.notesEdit);
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

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		wod_Date = getIntent().getStringExtra("WOD_DATE");
		edit = getIntent().getIntExtra("EDIT", -1);

		if (edit > 0) {
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
			WOD editWod = user.getMyLog().getWODsFromTo(wod_Date, wod_Date)
					.getFirst();

			if (editWod.getExercises().size() >= 1
					&& editWod.getExercises().get(0) != null)
				exercise1.setText(editWod.getExercises().get(0));
			if (editWod.getExercises().size() >= 2
					&& editWod.getExercises().get(1) != null)
				exercise2.setText(editWod.getExercises().get(1));
			if (editWod.getExercises().size() >= 3
					&& editWod.getExercises().get(2) != null)
				exercise3.setText(editWod.getExercises().get(2));
			if (editWod.getExercises().size() >= 4
					&& editWod.getExercises().get(3) != null)
				exercise4.setText(editWod.getExercises().get(3));
			if (editWod.getExercises().size() >= 5
					&& editWod.getExercises().get(4) != null)
				exercise5.setText(editWod.getExercises().get(4));
			if (editWod.getExercises().size() >= 6
					&& editWod.getExercises().get(5) != null)
				exercise6.setText(editWod.getExercises().get(5));
			if (editWod.getExercises().size() >= 7
					&& editWod.getExercises().get(6) != null)
				exercise7.setText(editWod.getExercises().get(6));
			if (editWod.getExercises().size() >= 8
					&& editWod.getExercises().get(7) != null)
				exercise8.setText(editWod.getExercises().get(7));

			if (editWod.getReps().size() >= 1
					&& editWod.getReps().get(0) != null)
				exercise1reps.setText(editWod.getReps().get(0).toString());
			if (editWod.getReps().size() >= 2
					&& editWod.getReps().get(1) != null)
				exercise2reps.setText(editWod.getReps().get(1).toString());
			if (editWod.getReps().size() >= 3
					&& editWod.getReps().get(2) != null)
				exercise3reps.setText(editWod.getReps().get(2).toString());
			if (editWod.getReps().size() >= 4
					&& editWod.getReps().get(3) != null)
				exercise4reps.setText(editWod.getReps().get(3).toString());
			if (editWod.getReps().size() >= 5
					&& editWod.getReps().get(4) != null)
				exercise5reps.setText(editWod.getReps().get(4).toString());
			if (editWod.getReps().size() >= 6
					&& editWod.getReps().get(5) != null)
				exercise6reps.setText(editWod.getReps().get(5).toString());
			if (editWod.getReps().size() >= 7
					&& editWod.getReps().get(6) != null)
				exercise7reps.setText(editWod.getReps().get(6).toString());
			if (editWod.getReps().size() >= 8
					&& editWod.getReps().get(7) != null)
				exercise8reps.setText(editWod.getReps().get(7).toString());

			if (editWod.getWeight().size() >= 1
					&& editWod.getWeight().get(0) != null)
				exercise1weight.setText(editWod.getWeight().get(0).toString());
			if (editWod.getWeight().size() >= 2
					&& editWod.getWeight().get(1) != null)
				exercise2weight.setText(editWod.getWeight().get(1).toString());
			if (editWod.getWeight().size() >= 3
					&& editWod.getWeight().get(2) != null)
				exercise3weight.setText(editWod.getWeight().get(2).toString());
			if (editWod.getWeight().size() >= 4
					&& editWod.getWeight().get(3) != null)
				exercise4weight.setText(editWod.getWeight().get(3).toString());
			if (editWod.getWeight().size() >= 5
					&& editWod.getWeight().get(4) != null)
				exercise5weight.setText(editWod.getWeight().get(4).toString());
			if (editWod.getWeight().size() >= 6
					&& editWod.getWeight().get(5) != null)
				exercise6weight.setText(editWod.getWeight().get(5).toString());
			if (editWod.getWeight().size() >= 7
					&& editWod.getWeight().get(6) != null)
				exercise7weight.setText(editWod.getWeight().get(6).toString());
			if (editWod.getWeight().size() >= 8
					&& editWod.getWeight().get(7) != null)
				exercise8weight.setText(editWod.getWeight().get(7).toString());

			if (editWod.isBenchmark())
				checkBoxBenchmark.setChecked(true);

			if (editWod.isScaled())
				scaledCheck.setChecked(true);

			if (editWod.getType().equals("AMRAP"))
				amrapCheck.setChecked(true);

			Integer oldTimeMin = editWod.getTimeMin();
			Integer oldTimeSec = editWod.getTimeSec();
			timeMin.setText(oldTimeMin.toString());
			timeSec.setText(oldTimeSec.toString());

			Integer oldRounds = editWod.getRounds();
			rounds.setText(oldRounds.toString());

			notesEdit.setText(editWod.getNotes());

			String tagsString = "";
			for (int r = 0; r < editWod.getTags().size(); r++)
				tagsString += editWod.getTags().get(r) + ",";

			tagsWOD.setText(tagsString);
		}

		enterWODButton.setOnClickListener(this);
		cancelWOD.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == enterWODButton) {
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

			boolean valid = true;
			boolean validEntry = true;
			// Create a new WOD object to be added
			WOD theWOD = new WOD();
			// Set it's fields from input
			theWOD.setDate(wod_Date);
			theWOD.setBenchmark(checkBoxBenchmark.isChecked());
			theWOD.setScaled(scaledCheck.isChecked());

			if (amrapCheck.isChecked())
				theWOD.setType("AMRAP");
			else
				theWOD.setType("For Time");

			// Set each exercise and it's reps and weight
			try {
				String exercise = exercise1.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise1reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise1weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise2.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise2reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise2weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise3.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise3reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise3weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise4.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise4reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise4weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise5.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise5reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise5weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise6.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise6reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise6weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise7.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise7reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise7weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			try {
				String exercise = exercise8.getText().toString().toUpperCase()
						.trim();
				if (!exercise.equals("")) {
					theWOD.getExercises().add(exercise);
					int reps = Integer.parseInt(exercise8reps.getText()
							.toString());
					double weight = Double.parseDouble(exercise8weight
							.getText().toString());
					if (reps < 0 || weight < 0) {
						validEntry = false;
					} else {
						theWOD.getReps().add(reps);
						theWOD.getWeight().add(weight);
					}
				}
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(
						this,
						"Please Enter Only Numbers into the Reps and Weight Fields",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}

			// setting the number of rounds
			try {
				theWOD.setRounds(Integer.parseInt(rounds.getText().toString()));
			} catch (java.lang.NumberFormatException nfe) {
				if (!rounds.getText().toString().equals("")) {
					Toast.makeText(
							this,
							"Please Enter Only Numbers into Rounds or Leave Empty",
							Toast.LENGTH_SHORT).show();
					valid = false;
				}
			}
			// setting the time in minutes
			try {
				theWOD.setTimeMin(Integer
						.parseInt(timeMin.getText().toString()));
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(this, "Please Enter Only Numbers into Time",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}
			// setting the time in seconds
			try {
				theWOD.setTimeSec(Integer
						.parseInt(timeSec.getText().toString()));
			} catch (java.lang.NumberFormatException nfe) {
				Toast.makeText(this, "Please Enter Only Numbers into Time",
						Toast.LENGTH_SHORT).show();
				valid = false;
			}

			// setting notes
			theWOD.setNotes(notesEdit.getText().toString().trim());

			// auto tagging the workout
			theWOD.autoTag();

			// add additional user inputted tags
			String tags = tagsWOD.getText().toString();
			String tokens[] = tags.split(",");

			for (int j = 0; j < tokens.length; j++) {
				theWOD.addTag(tokens[j]);
			}

			if (valid && validEntry) {
				// delete the old wod if we're editing
				if (edit > 0) {
					for (int g = 0; g < user.getMyLog().getWods().size(); g++) {
						if (wod_Date.equals(user.getMyLog().getWods().get(g)
								.getDate()))
							user.getMyLog().getWods().remove(g);
					}
				}
				// Add new wod to the database
				user.getMyLog().addWodSort(theWOD);

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

				// transition to letting the user view their wod
				Intent intent = new Intent(this, ViewWODActivity.class);
				intent.putExtra("WOD_DATE", wod_Date);
				intent.putExtra("USER_LIST_ID", userListID);
				startActivity(intent);
			} else if (valid && !validEntry)
				Toast.makeText(
						this,
						"Please Enter Only Positive Numbers for Reps and Weight",
						Toast.LENGTH_SHORT).show();
			//don't think we need the last else clause because then that means the exception
			//must have been thrown
		} else if (view == cancelWOD) {
			Intent intent = new Intent(this, TabHosterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}
}
