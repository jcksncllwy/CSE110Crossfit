package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BiometricTabActivity extends Activity implements OnClickListener{

	Button addBiometric;
	
	int userListID;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.biometrictab);
	    addBiometric = (Button) findViewById(R.id.addBiometric);
	    
	    userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
	    
	    addBiometric.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		if(view == addBiometric){
			Intent intent = new Intent(this,BiometricActivity.class);
			intent.putExtra("USER_LIST_ID", userListID);
			startActivity(intent);
		}
		
	}

}
