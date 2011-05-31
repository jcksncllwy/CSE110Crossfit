package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.cs110.stdev.crossfit.backend.Biometric;
import com.cs110.stdev.crossfit.backend.Log;
import com.cs110.stdev.crossfit.backend.User;
import com.cs110.stdev.crossfit.backend.WOD;

public class WeightChart extends AbstractDemoChart {

	int userListID;

	@Override
	public String getName() {
		return "Weight";
	}

	@Override
	public String getDesc() {
		return "The change in weight.";
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public Intent execute(Context context) {
		// String[] titles = new String[] {};
		List<Date[]> dates = new ArrayList<Date[]>();
		List<double[]> values = new ArrayList<double[]>();
		Log log = new Log();
		double[] weight;
		List<double[]> weightRange = null;
		WOD wods = new WOD();
		String start; // start date
		String end; // end date

		/* get the current user */
		LinkedList<User> userlist = new LinkedList<User>();
		String filename = "com/cs110/stdev/crossfit/backend/user.ser";

		/* pulling the user from the database */
		try {
			//FileReader fis = new FileReader(filename);
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(in);
			userlist = (LinkedList<User>) (((ObjectInput) in).readObject());
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		if (!userlist.isEmpty()) {
			if (!userlist.get(userListID).getMyLog().getBiometrics().isEmpty()) {
				Double height = userlist.get(0).getMyLog().getBiometrics()
						.getLast().getHeight();
			}
		}

		/* get the start and end date */
		start = userlist.get(0).getMyLog().getWods().get(0).getDate();
		end = userlist.get(0).getMyLog().getWods()
				.get(userlist.get(0).getMyLog().getWods().size() - 1).getDate();

		String[] titles = new String[] { "Weight from ", start, "to ", end };

		/* get the date range */
		DateFormat df = DateFormat.getInstance();
		//Date startDate = df.parse(start);
		//Date endDate = df.parse(end);
		
		/* get dates */
		LinkedList<Biometric> biolist = userlist.get(0).getMyLog().getBIOsFromTo(start, end);		
		Date[] dateValues = new Date[]{};
		Date biodate;
		int year, month, day;
		//adding the dates from the user's biometric list
		for(int i = 0; i < biolist.size(); i++)
		{
			year = biolist.get(i).getYear() - 1900;
			month = biolist.get(i).getMonth() - 1;
			day = biolist.get(i).getDay();
			biodate = new Date(year,month,day);
			dateValues[i] = biodate;
		}
		dates.add(dateValues);
		
		
		//Date[] dateValues = new Date[] {};
		//dates.add(dateValues);

		/* get the range of weights */
		weight = userlist.get(0).getMyLog().returnWeightRange(start, end);
		weightRange.add(weight);

		/* generate the chart */
		int[] colors = new int[] { Color.BLUE };
		PointStyle[] styles = new PointStyle[] { PointStyle.POINT };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		setChartSettings(renderer, "Weight", "Date", "lbs",
				dateValues[0].getTime(),
				dateValues[dateValues.length - 1].getTime(), 80, 200,
				Color.GRAY, Color.LTGRAY);
		renderer.setYLabels(1);
		return ChartFactory.getTimeChartIntent(context,
				buildDateDataset(titles, dates, weightRange), renderer,
				"MMM yyyy");
	}
}
