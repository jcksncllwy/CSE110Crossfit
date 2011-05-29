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
//put a delete WOD button in this layout
public class ViewWODActivity extends Activity implements OnClickListener {
	Button editWODButton;
	Button goBackButton;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.viewwod);
	    
	    editWODButton = (Button) findViewById(R.id.editWODButton);
	    goBackButton = (Button) findViewById(R.id.goBackButton);
	    
	    editWODButton.setOnClickListener(this);
	    goBackButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v==editWODButton){
			Intent i = new Intent(this,EnterWODActivity.class);
			startActivity(i);
		}
		else if(v==goBackButton){
			Intent i = new Intent(this,TabHosterActivity.class);
			startActivity(i);
		}
	}

}
