package com.cs110.stdev.crossfit;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

public class TabHoster extends TabActivity implements OnClickListener{

	TextView logoutTabHost;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tabhost);
	    
	    logoutTabHost = (TextView) findViewById(R.id.logoutTabHost);
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, ProfileActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("profile").setIndicator("Profile",
	                      res.getDrawable(R.drawable.profile))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, CalendarActivity.class);
	    spec = tabHost.newTabSpec("calendar").setIndicator("Calendar",
	                      res.getDrawable(R.drawable.calendar))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, ProgressActivity.class);
	    spec = tabHost.newTabSpec("progress").setIndicator("Progress",
	                      res.getDrawable(R.drawable.graph))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this,Settings.class);
	    spec = tabHost.newTabSpec("settings").setIndicator("Settings",
	    			res.getDrawable(R.drawable.settings)).setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	    
	    logoutTabHost.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v==logoutTabHost){
			Intent i = new Intent(this,Crossfit.class);
			startActivity(i);
		}
	}

}
