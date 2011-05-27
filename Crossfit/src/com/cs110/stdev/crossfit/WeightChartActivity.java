package com.cs110.stdev.crossfit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class WeightChartActivity extends AbstractDemoChart {

	@Override
	public String getName() {
		return "Body Fat %";
	}

	@Override
	public String getDesc() {
		return "The change in body fat %.";
	}

	@Override
	public Intent execute(Context context) {
		String[] titles = new String[] { "Body Fat % from 1995 to 2000" };
		List<Date[]> dates = new ArrayList<Date[]>();
		List<double[]> values = new ArrayList<double[]>();
		Date[] dateValues = new Date[] { new Date(95, 0, 1),
				new Date(95, 3, 1), new Date(95, 6, 1), new Date(95, 9, 1),
				new Date(96, 0, 1), new Date(96, 3, 1), new Date(96, 6, 1),
				new Date(96, 9, 1), new Date(97, 0, 1), new Date(97, 3, 1),
				new Date(97, 6, 1), new Date(97, 9, 1), new Date(98, 0, 1),
				new Date(98, 3, 1), new Date(98, 6, 1), new Date(98, 9, 1),
				new Date(99, 0, 1), new Date(99, 3, 1), new Date(99, 6, 1),
				new Date(99, 9, 1), new Date(100, 0, 1), new Date(100, 3, 1),
				new Date(100, 6, 1), new Date(100, 9, 1), new Date(100, 11, 1) };
		dates.add(dateValues);

		values.add(new double[] { 6.9, 7.3, 9.2, 8.5, 6.5, 6.7, 6.8, 7.3, 8,
				9.3, 7.5, 8.9, 9.2, 6.5, 8.6, 9.4, 12.3, 7.2, 9, 7.4, 12.5,
				13.4, 8.5, 9.3, 20 });
		int[] colors = new int[] { Color.BLUE };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "Body Fat %", "Date", "%",
				dateValues[0].getTime(),
				dateValues[dateValues.length - 1].getTime(), 6, 20, Color.GRAY,
				Color.LTGRAY);
		renderer.setYLabels(10);
		return ChartFactory.getTimeChartIntent(context,
				buildDateDataset(titles, dates, values), renderer, "MMM yyyy");
	}
}
