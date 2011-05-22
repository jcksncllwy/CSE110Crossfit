package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProgressActivity extends Activity implements OnClickListener {
	
	Button testtest;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    //setContentView(R.layout.graph);
	    /*
	    TextView textview = new TextView(this);
        textview.setText("This is the Progress tab");
        setContentView(textview);
        */
	    Grapher g = new Grapher();
		Intent i = g.execute(this);
		startActivity(i);
		/*
	    testtest = (Button) findViewById(R.id.testtest);
	    testtest.setOnClickListener(this);
	    */
	}

	@Override
	public void onClick(View v) {
		Grapher g = new Grapher();
		Intent i = g.execute(this);
		startActivity(i);
	}

}
