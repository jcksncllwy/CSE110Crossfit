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
	
	
	//get userListID from intent
	int userListID;
	//get the date of the wod to be entered/edited
	String wod_Date;
	
	static final int ENTER_WOD_REQUEST = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterwod);

		wod_Date = getIntent().getStringExtra("WOD_DATE");
		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
			
		enterWODButton = (Button) findViewById(R.id.enterWODButton);
		checkBoxBenchmark = (CheckBox) findViewById(R.id.checkBoxBenchmark);
		amrap = (RadioButton) findViewById(R.id.AMRAP);
		tabata = (RadioButton) findViewById(R.id.Tabata);
		forTime = (RadioButton) findViewById(R.id.forTime);
		wodType = (RadioGroup) findViewById(R.id.wodTypeRadio);
		wodType.addView(amrap, 0);
		wodType.addView(tabata, 1);
		wodType.addView(forTime, 2);
		prescribed = (RadioButton) findViewById(R.id.prescribed);
		scaled = (RadioButton) findViewById(R.id.scaled);
		adjustments = (RadioGroup) findViewById(R.id.prescribedRadio);
		adjustments.addView(prescribed, 0);
		adjustments.addView(scaled, 0);
		rounds = (EditText) findViewById(R.id.rounds);
		timeMin = (EditText) findViewById(R.id.timeMin);
		timeSec = (EditText) findViewById(R.id.timeSec);
		
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

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
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
		
		//Create a new WOD object to be added
		WOD theWOD = new WOD();
		//Set it's fields from input
		theWOD.setDate(wod_Date);
		theWOD.setBenchmark(checkBoxBenchmark.isChecked());
		//Set each exercise and it's reps and weight
		theWOD.getExercises().add(exercise1.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise1reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise1weight.getText().toString()));
		theWOD.getExercises().add(exercise2.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise2reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise2weight.getText().toString()));
		theWOD.getExercises().add(exercise3.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise3reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise3weight.getText().toString()));
		theWOD.getExercises().add(exercise4.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise4reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise4weight.getText().toString()));
		theWOD.getExercises().add(exercise5.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise5reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise5weight.getText().toString()));
		theWOD.getExercises().add(exercise6.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise6reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise6weight.getText().toString()));
		theWOD.getExercises().add(exercise7.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise7reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise7weight.getText().toString()));
		theWOD.getExercises().add(exercise8.getText().toString());
		theWOD.getReps().add(Integer.getInteger(exercise8reps.getText().toString()));
		theWOD.getWeight().add(Double.valueOf(exercise8weight.getText().toString()));
		
		switch(wodType.getCheckedRadioButtonId()){
		case 0:
			theWOD.setType("AMRAP");
		case 1:
			theWOD.setType("Tabata");
		case 2:
			theWOD.setType("For Time");
		}
		
		switch(adjustments.getCheckedRadioButtonId()){
		case 0:
			theWOD.setScaled(false);
		case 1:
			theWOD.setScaled(true);
		}
		
		theWOD.setRounds(Integer.parseInt(rounds.getText().toString()));
		theWOD.setTimeMin(Integer.parseInt(timeMin.getText().toString()));
		theWOD.setTimeSec(Integer.parseInt(timeSec.getText().toString()));
		
		//Add new wod to the database
		user.getMyLog().getWods().add(theWOD);
		
		//serialize changed data
		try {
			FileOutputStream fos = openFileOutput(filename,
					Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(userlist);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		Intent intent = new Intent();
		intent.putExtra("enteredWOD_Date", wod_Date);
		setResult(RESULT_OK, intent);
		finish();
	}
	

}
