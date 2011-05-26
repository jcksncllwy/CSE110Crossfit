package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class BiometricActivity extends Activity implements OnClickListener {

	/* define components */
	EditText bioWeightEdit;
	EditText bioBFEdit;
	EditText bioHREdit;
	EditText bioTagEdit;
	DatePicker dayPicker;
	Button bioSubmit;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.biometric);

		bioWeightEdit = (EditText) findViewById(R.id.bioWeightEdit);
		bioBFEdit = (EditText) findViewById(R.id.bioBFEdit);
		bioHREdit = (EditText) findViewById(R.id.bioHREdit);
		bioTagEdit = (EditText) findViewById(R.id.bioTagEdit);
		dayPicker = (DatePicker) findViewById(R.id.dayPicker);
		bioSubmit = (Button) findViewById(R.id.bioSubmit);

		bioSubmit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

	}

}
