package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProgressActivity extends Activity implements OnClickListener {
	
	Button testtest;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    //setContentView(R.layout.graph);
	    
	    Grapher g = new Grapher();
		Intent i = g.execute(this);
		startActivity(i);
	}

	@Override
	public void onClick(View v) {
	}

}
