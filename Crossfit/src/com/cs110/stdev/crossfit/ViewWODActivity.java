package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;
import com.cs110.stdev.crossfit.backend.WOD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//put a delete WOD button in this layout
public class ViewWODActivity extends Activity implements OnClickListener {
	Button editWODButton;
	Button goBackButton;
	int userListID;
	String wod_Date;

	TextView isBenchmark;
	TextView theWodType;
	TextView isPrescribed;
	TextView exercise1;
	TextView exercise2;
	TextView exercise3;
	TextView exercise4;
	TextView exercise5;
	TextView exercise6;
	TextView exercise7;
	TextView exercise8;
	String isBenchmarkString;
	String theWodTypeString;
	String isPrescribedString;
	String exercise1String;
	String exercise2String;
	String exercise3String;
	String exercise4String;
	String exercise5String;
	String exercise6String;
	String exercise7String;
	String exercise8String;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewwod);

		editWODButton = (Button) findViewById(R.id.editWODButton);
		goBackButton = (Button) findViewById(R.id.goBackButton);

		isBenchmark = (TextView) findViewById(R.id.isBenchmark);
		theWodType = (TextView) findViewById(R.id.theWodType);
		isPrescribed = (TextView) findViewById(R.id.isPrescribed);
		exercise1 = (TextView) findViewById(R.id.exercise1);
		exercise2 = (TextView) findViewById(R.id.exercise2);
		exercise3 = (TextView) findViewById(R.id.exercise3);
		exercise4 = (TextView) findViewById(R.id.exercise4);
		exercise5 = (TextView) findViewById(R.id.exercise5);
		exercise6 = (TextView) findViewById(R.id.exercise6);
		exercise7 = (TextView) findViewById(R.id.exercise7);
		exercise8 = (TextView) findViewById(R.id.exercise8);

		String isBenchmarkString = "";
		String theWodTypeString = "";
		String isPrescribedString = "";
		String[] exerciseArray = new String[8];
		for (int j = 0; j < exerciseArray.length; j++)
			exerciseArray[j] = "";

		/*
		 * String exercise1String = ""; String exercise2String = ""; String
		 * exercise3String = ""; String exercise4String = ""; String
		 * exercise5String = ""; String exercise6String = ""; String
		 * exercise7String = ""; String exercise8String = "";
		 */
		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		try {
			wod_Date = getIntent().getStringExtra("enteredWOD_Date");
		} catch (NullPointerException e) {
			wod_Date = "none";
		}

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
		WOD theWod = user.getMyLog().getWODsFromTo(wod_Date, wod_Date)
				.getFirst();
		if (theWod.isBenchmark()) {
			isBenchmarkString = "Yes";
		}
		if (theWod.isScaled()) {
			isPrescribedString = "No";
		}

		System.out.println(theWod.getExercises().size());
		for (int k = 0; k < theWod.getExercises().size(); k++) {
			System.out.println(theWod.getExercises().get(k));
		}
		System.out.println(wod_Date);
		System.out.println(theWod.getDate());
		theWodTypeString = theWod.getType();
		System.out.println(theWod.getExercises().size());
		// Exercise names
		for (int i = 0; i < theWod.getExercises().size(); i++) {
			System.out.println("iteration" + i);
			exerciseArray[i] = theWod.getExercises().get(i) + "  "
					+ theWod.getReps().get(i) + "  "
					+ theWod.getWeight().get(i);
			System.out.println("WOD REPS SIZE: " + theWod.getReps().size());
			System.out.println(theWod.getReps().get(i));
			/*
			 * exercise2String.concat(theWod.getExercises().get(1) + "  ");
			 * exercise3String.concat(theWod.getExercises().get(2) + "  ");
			 * exercise4String.concat(theWod.getExercises().get(3) + "  ");
			 * exercise5String.concat(theWod.getExercises().get(4) + "  ");
			 * exercise6String.concat(theWod.getExercises().get(5) + "  ");
			 * exercise7String.concat(theWod.getExercises().get(6) + "  ");
			 * exercise8String.concat(theWod.getExercises().get(7) + "  ");
			 */
		}
		// Exercise Reps
		/*
		 * exercise1String .concat(Integer.toString(theWod.getReps().get(0)) +
		 * "  "); exercise2String
		 * .concat(Integer.toString(theWod.getReps().get(1)) + "  ");
		 * exercise3String .concat(Integer.toString(theWod.getReps().get(2)) +
		 * "  "); exercise4String
		 * .concat(Integer.toString(theWod.getReps().get(3)) + "  ");
		 * exercise5String .concat(Integer.toString(theWod.getReps().get(4)) +
		 * "  "); exercise6String
		 * .concat(Integer.toString(theWod.getReps().get(5)) + "  ");
		 * exercise7String .concat(Integer.toString(theWod.getReps().get(6)) +
		 * "  "); exercise8String
		 * .concat(Integer.toString(theWod.getReps().get(7)) + "  ");
		 * 
		 * // Exercise Weights
		 * exercise1String.concat(Double.toString(theWod.getWeight().get(0)) +
		 * "  ");
		 * exercise2String.concat(Double.toString(theWod.getWeight().get(1)) +
		 * "  ");
		 * exercise3String.concat(Double.toString(theWod.getWeight().get(2)) +
		 * "  ");
		 * exercise4String.concat(Double.toString(theWod.getWeight().get(3)) +
		 * "  ");
		 * exercise5String.concat(Double.toString(theWod.getWeight().get(4)) +
		 * "  ");
		 * exercise6String.concat(Double.toString(theWod.getWeight().get(5)) +
		 * "  ");
		 * exercise7String.concat(Double.toString(theWod.getWeight().get(6)) +
		 * "  ");
		 * exercise8String.concat(Double.toString(theWod.getWeight().get(7)) +
		 * "  ");
		 */
		// Set TextViews text to the strings
		isBenchmark.setText(isBenchmarkString);
		theWodType.setText(theWodTypeString);
		isPrescribed.setText(isPrescribedString);
		exercise1.setText(exerciseArray[0]);
		exercise2.setText(exerciseArray[1]);
		exercise3.setText(exerciseArray[2]);
		exercise4.setText(exerciseArray[3]);
		exercise5.setText(exerciseArray[4]);
		exercise6.setText(exerciseArray[5]);
		exercise7.setText(exerciseArray[6]);
		exercise8.setText(exerciseArray[7]);

		editWODButton.setOnClickListener(this);
		goBackButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		if (view == editWODButton) {
			Intent intent = new Intent(this, EnterWODActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			intent.putExtra("WOD_DATE", wod_Date);
			startActivity(intent);
		} else if (view == goBackButton) {
			Intent intent = new Intent(this, TabHosterActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
	}

}
