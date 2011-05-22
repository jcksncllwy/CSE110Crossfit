package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProgressActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    TextView textview = new TextView(this);
        textview.setText("This is the Progress tab");
        setContentView(textview);

	}

}
