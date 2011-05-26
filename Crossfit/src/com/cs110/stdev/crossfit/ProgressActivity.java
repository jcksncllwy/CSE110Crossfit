package com.cs110.stdev.crossfit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cs110.stdev.crossfit.IChart;
import com.cs110.stdev.crossfit.WeightChart;
//import com.cs110.stdev.crossfit.XYChartBuilder;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ProgressActivity extends ListActivity {
	private IChart[] mCharts = new IChart[] { new WeightChart() };

	private String[] mMenuText;

	private String[] mMenuSummary;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int length = mCharts.length;
		mMenuText = new String[length + 2];
		mMenuSummary = new String[length + 2];
		mMenuText[0] = "Weight Chart";
		mMenuSummary[0] = "Your progress on weight";
		for (int i = 0; i < length; i++) {
			mMenuText[i + 1] = mCharts[i].getName();
			mMenuSummary[i + 1] = mCharts[i].getDesc();
		}
		mMenuText[length + 1] = "Random values charts";
		mMenuSummary[length + 1] = "Chart demos using randomly generated values";
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
		Intent intent = null;
		/*if (position == 0) {
			intent = new Intent(this, XYChartBuilder.class);
		} else if (position <= mCharts.length) {
			intent = mCharts[position - 1].execute(this);
		} else {
			intent = new Intent(this, GenerateChart.class);
		}*/
		intent.setClass(this,WeightChart.class);
		startActivity(intent);
	}
}
