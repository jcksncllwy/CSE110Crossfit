package com.cs110.stdev.crossfit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs110.stdev.crossfit.IChart;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProgressActivity extends ListActivity {
	private IChart[] mCharts = new IChart[] { new HeartRateChart(),
			new BodyFatChart() };

	private String[] mMenuText;

	private String[] mMenuSummary;

	int userListID;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);

		int length = mCharts.length;
		mMenuText = new String[length + 2];
		mMenuSummary = new String[length + 2];
		mMenuText[0] = "Weight Progression";
		mMenuSummary[0] = "Your progress on weight.";
		userListID = getIntent().getIntExtra("USER_LIST_ID", -1);
		for (int i = 0; i < length; i++) {
			mMenuText[i + 1] = mCharts[i].getName();
			mMenuSummary[i + 1] = mCharts[i].getDesc();
		}
		mMenuText[length + 1] = "BMI Progression";
		mMenuSummary[length + 1] = "Your BMI progress.";
		setListAdapter(new SimpleAdapter(this, getListValues(),
				android.R.layout.simple_list_item_2, new String[] {
						IChart.NAME, IChart.DESC }, new int[] {
						android.R.id.text1, android.R.id.text2 }));
	}

	private List<Map<String, String>> getListValues() {
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		int length = mMenuText.length;
		for (int i = 0; i < length; i++) {
			Map<String, String> v = new HashMap<String, String>();
			v.put(IChart.NAME, mMenuText[i]);
			v.put(IChart.DESC, mMenuSummary[i]);
			values.add(v);
		}
		return values;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent intent;

		if (position == 0) {
			intent = new Intent(this, WeightChart2.class);
		} else if (position == 1) {
			intent = new Intent(this, HeartRateChart.class);
		} else if (position == 2) {
			intent = new Intent(this, BodyFatChart.class);
		} else {
			intent = new Intent(this, BMIChart.class);
		}

		intent.putExtra("USER_LIST_ID", userListID);
		startActivity(intent);

	}
}
