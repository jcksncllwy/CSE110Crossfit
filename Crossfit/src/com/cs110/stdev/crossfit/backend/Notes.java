/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Notes object, which holds all notes
 * pertaining to injuries, exercise, nutrition, etc.
 */
package com.cs110.stdev.crossfit.backend;

import java.io.Serializable;
import java.util.*;

public class Notes implements Serializable {
	
	//FIELDS
    private String injury;
	private String exerciseComments;
	private String nutrition;
	private String other;
	private LinkedList<String> tags;
	private String date;

	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public Notes() {
		injury = "";
		exerciseComments = "";
		nutrition = "";
		other = "";
		tags = new LinkedList<String>();
		date = "";
	}
	
	//METHODS
	/**
	 * Method to autotag info stored in Notes
	 * @return - true if autotag was successful, false otherwise
	 */
	public boolean autoTag() {
		if(exerciseComments.contains("vomit")) {
			tags.add("vomit");
		}
		return true;
	}

	/**
	 * Method to get injury
	 * @return - injury
	 */
	public String getInjury() {
		return injury;
	}

	/**
	 * Method to set injury
	 * @param - new injury to be set
	 */
	public void setInjury(String newInjury) {
		this.injury = newInjury;
	}

	/**
	 * Method to get exercise comments
	 * @return - exercise comments
	 */
	public String getExerciseComments() {
		return exerciseComments;
	}

	/**
	 * Method to set exercise comments
	 * @param - new exercise comments to be set
	 */
	public void setExerciseComments(String newExComment) {
		this.exerciseComments = newExComment;
	}

	/**
	 * Method to get nutrition
	 * @return - nutrition
	 */
	public String getNutrition() {
		return nutrition;
	}

	/**
	 * Method to set nutrition
	 * @param - new nutrition to be set
	 */
	public void setNutrition(String newNutrition) {
		this.nutrition = newNutrition;
	}

	/**
	 * Method get any other type of notes user added
	 * @return - other type of notes
	 */
	public String getOther() {
		return other;
	}

	/**
	 * Method to set other type of notes
	 * @param - new other type of notes to be set
	 */
	public void setOther(String newOther) {
		this.other = newOther;
	}

	/**
	 * Method to get list of tags
	 * @return - list of tags
	 */
	public LinkedList<String> getTags() {
		return tags;
	}
	
	/**
	 * Method to append tag to list of tags
	 * @param - new tag to be appended
	 */
	public void addTags(String newTag) {
		boolean found = false;
		for(int i = 0; i < tags.size(); i++) {
			if(tags.get(i).equals(newTag)) {
				found = true;
			}
		}
		if(found == false) {
			this.tags.add(newTag.toLowerCase());
		}
	}
	
	/**
	 * Method to set new list of tags
	 * @param - new list of tags to be set
	 */
	public void setTags(LinkedList<String> newTags) {
		this.tags = newTags;
	}

	/**
	 * Method to get date
	 * @return - data
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set data
	 * @param - new date to be set
	 */
	public void setDate(String newDate) {
		this.date = newDate;
	}
	
	/**
	 * Method to set date using month, day, and year
	 * @param (int month) - new month to be set
	 * @param (int day) - new day to be set
	 * @param (int year) - new year to be set
	 */
	public void setDate(int month, int day, int year) {
		this.date = Integer.toString(month) + Integer.toString(day) + Integer.toString(year);
	}

	/**
	 * Method to convert date from MMDDYY to MM/DD/YY
	 * @return - date (MM/DD/YY)
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
	 * Method to convert all of Notes info to String for text dump
	 * @return - String of Notes info
	 */
	public String toString() {
		String notesInfo = "";
		
		notesInfo += "Date of Notes: " + toDate() + "\n";
		notesInfo += "Comments on Exercises:" + exerciseComments + "\n";
		notesInfo += "Nutrition:" + nutrition + "\n";
		notesInfo += "Injuries:" + injury + "\n";
		notesInfo += "Other:" + other + "\n";
		notesInfo += "\n_________________________________";
		
		return notesInfo;
	}
}
