/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Biometric object, which holds all info
 * pertaining to the user's health. 
 */
package com.cs110.stdev.crossfit.backend;

import java.io.Serializable;
import java.util.*;

public class Biometric implements Serializable {

	// FIELDS
	private double weight;
	private double height;
	private double bodyFat;
	private int heartRate;
	private double bMI;
	private LinkedList<String> tags;
	private String date;

	// CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public Biometric() {
		weight = 0.0;
		bodyFat = 0.0;
		height = 0.0;
		heartRate = 0;
		bMI = 0.0;
		date = "";
		tags = new LinkedList<String>();
	}

	// METHODS
	/**
	 * Method to autotag info stored in Biometrics
	 * 
	 * @return - true if autotag was successful, false otherwise
	 */
	public boolean autoTag() {
		if (tags.add(Double.toString(weight))
				&& tags.add(Double.toString(bodyFat))
				&& tags.add(Double.toString(heartRate))
				&& tags.add(Double.toString(bMI)) && tags.add(this.toDate())) {
			return true;
		}

		return false;
	}

	/**
	 * Method to calculate the body mass index (BMI) with the given weight and
	 * height
	 * 
	 * @param (double weight) - weight used in BMI calculation
	 * @param (double height) - height used in BMI calculation
	 * @return - calculated BMI
	 */
	public double calculateBMI() {
		this.bMI = (weight * 703) / Math.pow(height, 2.0);
		return this.bMI;
	}

	/**
	 * Method to get weight
	 * 
	 * @return - weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Method to set weight
	 * 
	 * @param - new weight to be set
	 */
	public void setWeight(double newWeight) {
		if (newWeight > 0.0) {
			this.weight = newWeight;
		}
	}

	/**
	 * Method to get body fat percentage
	 * 
	 * @return - body fat percentage
	 */
	public double getbodyFat() {
		return bodyFat;
	}

	/**
	 * Method to set body fat percentage
	 * 
	 * @param - new body fat percentage to be set
	 */
	public void setBodyFat(double newBodyFat) {
		if (newBodyFat > 0.0) {
			this.bodyFat = newBodyFat;
		}
	}

	/**
	 * Method to get heart rate
	 * 
	 * @return - heart rate
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * Method to set heart rate
	 * 
	 * @param - new heart rate to be set
	 */
	public void setHeartRate(int newHeartRate) {
		if (newHeartRate > 0) {
			this.heartRate = newHeartRate;
		}
	}

	/**
	 * Method to get user's height
	 * 
	 * @return - user's height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Method to set user's height
	 * 
	 * @param - new height to be set
	 */
	public void setHeight(double newHeight) {
		this.height = newHeight;
	}

	public String printHeight() {
		return ((int) height / 12) + " feet " + ((int) height % 12) + " inches";
	}

	/**
	 * Method to get BMI
	 * 
	 * @return - BMI
	 */
	public double getbMI() {
		return bMI;
	}

	/**
	 * Method to set BMI
	 * 
	 * @param - new BMI to be set
	 */
	public void setBMI() {
		this.bMI = (weight * 703) / Math.pow(height, 2.0);
	}

	/**
	 * Method to get date
	 * 
	 * @return - date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set date
	 * 
	 * @param - new date to be set
	 */
	public void setDate(String newDate) {
		this.date = newDate;
	}

	/**
	 * Method to set date using month, day, and year
	 * 
	 * @param (int month) - new month to be set
	 * @param (int day) - new day to be set
	 * @param (int year) - new year to be set
	 */
	public void setDate(int month, int day, int year) {
		String newDate = "";
		if(month < 10) {
			newDate += "0";
		}
		newDate += Integer.toString(month);
		if(day < 10) {
			newDate += "0";
		}
		newDate += Integer.toString(day);
		newDate += Integer.toString(year);
		this.date = newDate;
	}

	/**
	 * Method to get list of tags
	 * 
	 * @return - list of tags
	 */
	public LinkedList<String> getTags() {
		return tags;
	}

	/**
	 * Method to append tag to list of tags
	 * 
	 * @param - new tag to be appended
	 */
	public void addTag(String newTag) {
		this.tags.add(newTag);
	}

	/**
	 * Method to set new list of tags
	 * 
	 * @param - new list of tags to be set
	 */
	public void setTags(LinkedList<String> newTags) {
		this.tags = newTags;
	}

	/**
	 * Method to convert date from MMDDYY to MM/DD/YYYY
	 * 
	 * @return - date (MM/DD/YYYY)
	 */
	public String toDate() {
		String newDate = "";

		newDate += date.substring(0,2) + "/";
		newDate += date.substring(2,4) + "/";
		newDate += date.substring(4,8);

		return newDate;
	}

	public int getMonth() {
		String strMonth = date.substring(0, 2);
		return Integer.parseInt(strMonth);
	}

	public int getDay() {
		String strDay = date.substring(2, 4);
		return Integer.parseInt(strDay);
	}

	public int getYear() {
		String strYear = date.substring(4, 8);
		return Integer.parseInt(strYear);
	}

	/**
	 * Method to convert all of Biometric data info to string for text dump
	 * 
	 * @return - String of Biometric info
	 */
	public String toString() {
		String bioInfo = "";

		bioInfo += "Date of Biometrics: " + date + "\n";
		bioInfo += "Weight " + weight + "\n";
		bioInfo += "Body Fat Percentage " + bodyFat + "\n";
		bioInfo += "Heart Rate " + heartRate + "\n";
		bioInfo += "Body Mass Index " + bMI + "\n";
		bioInfo += "\n_________________________________";

		return bioInfo;
	}
}