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
import android.widget.TextView;
import android.widget.Toast;

//put a delete WOD button in this layout
public class ViewWODActivity extends Activity implements OnClickListener {
	Button editWODButton;
	Button goBackButton;
	Button deleteWODButton;

	int userListID;
	String wod_Date;

	TextView benchmark;
	TextView wodType;
	TextView prescribed;
	TextView exercise1;
	TextView exercise2;
	TextView exercise3;
	TextView exercise4;
	TextView exercise5;
	TextView exercise6;
	TextView exercise7;
	TextView exercise8;
	TextView timeText;
	TextView roundsText;
	TextView tagsText;
	TextView notesText;

	/** Called when the activity is first created. */
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewwod);

		editWODButton = (Button) findViewById(R.id.editWODButton);
		deleteWODButton = (Button) findViewById(R.id.deleteWODButton);
		goBackButton = (Button) findViewById(R.id.goBackButton);

		benchmark = (TextView) findViewById(R.id.benchmark);
		wodType = (TextView) findViewById(R.id.wodType);
		prescribed = (TextView) findViewById(R.id.prescribed);
		exercise1 = (TextView) findViewById(R.id.exercise1);
		exercise2 = (TextView) findViewById(R.id.exercise2);
		exercise3 = (TextView) findViewById(R.id.exercise3);
		exercise4 = (TextView) findViewById(R.id.exercise4);
		exercise5 = (TextView) findViewById(R.id.exercise5);
		exercise6 = (TextView) findViewById(R.id.exercise6);
		exercise7 = (TextView) findViewById(R.id.exercise7);
		exercise8 = (TextView) findViewById(R.id.exercise8);
		timeText = (TextView) findViewById(R.id.timeText);
		roundsText = (TextView) findViewById(R.id.roundsText);
		tagsText = (TextView) findViewById(R.id.tagsText);
		notesText = (TextView) findViewById(R.id.notesText);

		String isBenchmarkString = "Benchmark: No";
		String isPrescribedString = "As Prescribed: Yes";

		String[] exerciseArray = new String[8];
		for (int j = 0; j < exerciseArray.length; j++)
			exerciseArray[j] = "Exercise: ";

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		wod_Date = getIntent().getStringExtra("WOD_DATE");

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
		WOD theWod = user.getMyLog().getWODAtDate(wod_Date);
		if (theWod.isBenchmark()) {
			isBenchmarkString = "Benchmark: Yes";
		}
		if (theWod.isScaled()) {
			isPrescribedString = "As Prescribed: No";
		}

		String theWodTypeString = "WOD Type: " + theWod.getType();
		// Exercise names
		for (int i = 0; i < theWod.getExercises().size(); i++) {
			exerciseArray[i] = exerciseArray[i].concat(theWod.getExercises()
					.get(i)
					+ "  "
					+ theWod.getReps().get(i)
					+ "  "
					+ theWod.getWeight().get(i));
		}

		// Set TextViews text to the strings
		benchmark.setText(isBenchmarkString);
		wodType.setText(theWodTypeString);
		prescribed.setText(isPrescribedString);
		exercise1.setText(exerciseArray[0]);
		exercise2.setText(exerciseArray[1]);
		exercise3.setText(exerciseArray[2]);
		exercise4.setText(exerciseArray[3]);
		exercise5.setText(exerciseArray[4]);
		exercise6.setText(exerciseArray[5]);
		exercise7.setText(exerciseArray[6]);
		exercise8.setText(exerciseArray[7]);

		String timeString = "Time: " + theWod.getTimeMin() + ":"
				+ theWod.getTimeSec() + "\t";
		timeText.setText(timeString);
		String roundsString = "Rounds: " + theWod.getRounds();
		roundsText.setText(roundsString);

		notesText.setText("Notes: " + theWod.getNotes());

		String tags = "Tags: ";
		for (int i = 0; i < theWod.getTags().size(); i++)
			tags += theWod.getTags().get(i) + " ";

		tagsText.setText(tags);

		editWODButton.setOnClickListener(this);
		deleteWODButton.setOnClickListener(this);
		goBackButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == editWODButton) {
			Intent intent = new Intent(this, EnterWODActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			intent.putExtra("WOD_DATE", wod_Date);
			intent.putExtra("EDIT", 1);
			startActivity(intent);
		} else if (view == deleteWODButton) {
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

			// iterating and looking for the WOD -- could maybe do a binary
			// search instead
			for (int g = 0; g < user.getMyLog().getWods().size(); g++) {
				if (wod_Date.equals(user.getMyLog().getWods().get(g).getDate())) {
					Toast.makeText(
							this,
							"Your WOD for "
									+ user.getMyLog().getWods().get(g).toDate()
									+ " has been deleted!", Toast.LENGTH_SHORT)
							.show();
					user.getMyLog().getWods().remove(g);
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
		} else if (view == goBackButton) {
			Intent intent = new Intent(this, TabHosterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}

}
