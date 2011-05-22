package com.cs110.stdev.crossfit;

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
			Intent i = new Intent(this,TabHoster.class);
			startActivity(i);
		}
	}

}
