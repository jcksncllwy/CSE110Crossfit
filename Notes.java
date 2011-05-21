/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Notes object, which holds all notes
 * pertaining to injuries, exercise, nutrition, etc.
 */
package crossfitPlusPlus;

import java.util.*;

public class Notes {
	
	//FIELDS
    private String injuries;
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
		injuries = "";
		exerciseComments = "";
		nutrition = "";
		other = "";
		tags = new LinkedList<String>();
		date = "";
	}
	
	//METHODS
	/**
	 * Method to autotag a WOD
	 * @return - true if autotag was successful, false otherwise
	 */
	public boolean autoTag(){
		//TODO
		return true;
	}

	/**
	 * Method to get injuries
	 * @return - injuries
	 */
	public String getInjuries() {
		return injuries;
	}

	/**
	 * Method to set injuries
	 * @param - injuries to be set
	 */
	public void setInjuries(String injuries) {
		this.injuries = injuries;
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
	 * @param - exercise comments to be set
	 */
	public void setExerciseComments(String exerciseComments) {
		this.exerciseComments = exerciseComments;
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
	 * @param - nutrition to be set
	 */
	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
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
	 * @param - other type of notes to be set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * Method to get list of tags
	 * @return - list of tags
	 */
	public LinkedList<String> getTags() {
		return tags;
	}

	/**
	 * Method to set list of tags
	 * @param - list of tags to be set
	 */
	public void setTags(LinkedList<String> tags) {
		this.tags = tags;
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
	 * @param - date to be set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Method to convert date from MMDDYY to MM/DD/YY
	 * @return - date (MM/DD/YY)
	 */
	public String toDate() {
		String newDate;
		newDate += date.charAt(0) + date.charAt(1) + "/";
		newDate += date.charAt(2) + date.charAt(3) + "/";
		newDate += date.charAt(4) + date.charAt(5);
		return newDate;
	}
	
	/**
	 * Method to convert all of Notes info to String for text dump
	 * @return - String of Notes info
	 */
	public String toString() {
		String notesInfo;
		
		notesInfo += "Date of Notes: " + toDate() + "\n";
		notesInfo += "Comments on Exercises:" + exerciseComments + "\n";
		notesInfo += "Nutrition:" + nutrition + "\n";
		notesInfo += "Injuries:" + injuries + "\n";
		notesInfo += "Other:" + other + "\n";
		notesInfo += "_________________________________";
	}
}
