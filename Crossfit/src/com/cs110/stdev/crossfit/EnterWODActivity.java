package com.cs110.stdev.crossfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

/* make a linked list of exercise edittexts and then use an i to keep
 * track of how many have been used and we can use that to get the texts
 * from each one by indexing into the linked list
 */

public class EnterWODActivity extends Activity implements OnClickListener {
	Button enterWODButton;
	CheckBox checkBoxBenchmark;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterwod);

		enterWODButton = (Button) findViewById(R.id.enterWODButton);
		checkBoxBenchmark = (CheckBox) findViewById(R.id.checkBoxBenchmark);

		enterWODButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(this, TabHosterActivity.class);
		startActivity(i);
	}

}
