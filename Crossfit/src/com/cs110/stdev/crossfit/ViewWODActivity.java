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
	TextView exercise1name;
	TextView exercise2name;
	TextView exercise3name;
	TextView exercise4name;
	TextView exercise5name;
	TextView exercise6name;
	TextView exercise7name;
	TextView exercise8name;
	TextView exercise1reps;
	TextView exercise2reps;
	TextView exercise3reps;
	TextView exercise4reps;
	TextView exercise5reps;
	TextView exercise6reps;
	TextView exercise7reps;
	TextView exercise8reps;
	TextView exercise1weight;
	TextView exercise2weight;
	TextView exercise3weight;
	TextView exercise4weight;
	TextView exercise5weight;
	TextView exercise6weight;
	TextView exercise7weight;
	TextView exercise8weight;
	
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
	    exercise1name = (TextView) findViewById(R.id.exercise1);
		exercise2name = (TextView) findViewById(R.id.exercise2);
		exercise3name = (TextView) findViewById(R.id.exercise3);
		exercise4name = (TextView) findViewById(R.id.exercise4);
		exercise5name = (TextView) findViewById(R.id.exercise5);
		exercise6name = (TextView) findViewById(R.id.exercise6);
		exercise7name = (TextView) findViewById(R.id.exercise7);
		exercise8name = (TextView) findViewById(R.id.exercise8);
		exercise1reps = (TextView) findViewById(R.id.exercise1reps);
		exercise2reps = (TextView) findViewById(R.id.exercise2reps);
		exercise3reps = (TextView) findViewById(R.id.exercise3reps);
		exercise4reps = (TextView) findViewById(R.id.exercise4reps);
		exercise5reps = (TextView) findViewById(R.id.exercise5reps);
		exercise6reps = (TextView) findViewById(R.id.exercise6reps);
		exercise7reps = (TextView) findViewById(R.id.exercise7reps);
		exercise8reps = (TextView) findViewById(R.id.exercise8reps);
		exercise1weight = (TextView) findViewById(R.id.exercise1weight);
		exercise2weight = (TextView) findViewById(R.id.exercise2weight);
		exercise3weight = (TextView) findViewById(R.id.exercise3weight);
		exercise4weight = (TextView) findViewById(R.id.exercise4weight);
		exercise5weight = (TextView) findViewById(R.id.exercise5weight);
		exercise6weight = (TextView) findViewById(R.id.exercise6weight);
		exercise7weight = (TextView) findViewById(R.id.exercise7weight);
		exercise8weight = (TextView) findViewById(R.id.exercise8weight);
	    
	    userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
	    try{
	    	wod_Date = getIntent().getStringExtra("enteredWOD_Date");
	    }
	    catch(NullPointerException e){
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
	    
	    
	    
	    
	    
	    
	    
	    editWODButton.setOnClickListener(this);
	    goBackButton.setOnClickListener(this);
	    
	}
	
	@Override
	public void onClick(View v) {
		if(v==editWODButton){
			Intent i = new Intent(this,EnterWODActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			startActivity(i);
		}
		else if(v==goBackButton){
			Intent i = new Intent(this,TabHosterActivity.class);
			i.putExtra("USER_LIST_ID", userListID);
			startActivity(i);
		}
	}

}
