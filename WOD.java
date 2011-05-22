/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Workout of the Day (WOD) object, 
 * which holds all of the info for a WOD.
 */
package crossfitPlusPlus;

import java.util.*;

public class WOD {
	
	//FIELDS
    private String title;
	private LinkedList<String> exercises;
	private LinkedList<int> reps;
	private LinkedList<double> weight;
	private boolean benchmark;
	private boolean scaled;
	private String type;
	private int timeMin;
	private int timeSec;
	private int rounds;
	private LinkedList<String> tags;
	private String date;
    
	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public WOD() {
		title = "";
		exercises = new LinkedList();
		reps = new LinkedList();
		weight = newLinkedList();
		benchmark = true;
		scaled = true;
		date = "";
		type = "";
		timeSec = 0;
		rounds = 0;
		tags = new LinkedList<String>();
	}
	
	//METHODS
	/**
	 * Method to autotag a WOD
	 * @return - true if autotag was successful, false otherwise
	 */
	public boolean autoTag() {
		tags.add(title);
		tags.add(type);
		
		for(int i = 0; i < exercises.size(); i++) {
			tags.add(exercises.get(i));
		}
		
		if(benchmark == true) {
			tags.add("benchmark");
		}
		else {
			tags.add("non-benchmark");
		}
		
		if(rounds >= 0) {
			tags.add("AMRAP");
		}
		
		if(tags.size() == (4 + exercises.size()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Method to get name of WOD
	 * @return - name of WOD
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method to set name of WOD
	 * @param - new name to be set
	 */
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}
	
	/**
	 * Method to get list of exercises associated with WOD
	 * @return - linked list of exercises
	 */
	public LinkedList<String> getExercises() {
		return exercises;
	}

	/**
	 * Method to append an exercise associated with WOD
	 * @param - new exercise to be appended
	 */
	public void addExercise(String newExercise) {
		this.exercises.add(newExercise);
	}
	
	/**
	 * Method to set new list of exercise associated with WOD
	 * @param - new list of exercises to be set
	 */
	public void setExercises(LinkedList<String> newExercises) {
		this.exercises = newExercise;
	}
	
	/**
	 * Method to get reps used in each exercise for WOD
	 * @return - linked list of reps
	 */
	public LinkedList<int> getReps() {
		return reps;
	}

	/**
	 * Method to append a rep for an exercise in WOD
	 * @param - new rep to be appended
	 */
	public void addRep(int newRep) {
		this.reps.add(newRep);
	}
	
	/**
	 * Method to set number of reps for exercise in WOD
	 * @param - number of reps to be set
	 */
	public void setReps(LinkedList<int> newReps) {
		this.reps = newRep;
	}
	
	/**
	 * Method to get weights used for each exercise for WOD
	 * @return - linked list of weights
	 */
	public LinkedList<double> getWeight() {
		return weight;
	}
	
	/**
	 * Method to append weight used for exercise in WOD
	 * @param - new weight to be appended
	 */
	public void addWeight(double newWeight) {
		this.weight.add(newWeight);
	}
	
	/**
	 * Method to set new list of weights used for exercise in WOD
	 * @param - new list of weights to be set
	 */
	public void setWeights(LinkedList<double> newWeights) {
		this.weight = newWeights;
	}
	
	/**
	 * Method to check if WOD is a benchmark
	 * @return - true if WOD is benchmark, false otherwise
	 */
	public boolean isBenchmark() {
		return benchmark;
	}
	
	/**
	 * Method to set WOD to be a benchmark
	 * @param - benchmark to be set
	 */
	public void setBenchmark(boolean isBenchmark) {
		this.benchmark = isBenchmark;
	}
	
	/**
	 * Method to check if scaled weights were used for WOD
	 * @return - true if scaled weights used, false otherwise
	 */
	public boolean isScaled() {
		return scaled;
	}
	
	/**
	 * Method to set WOD to have used scaled weights
	 * @param - scaled weights to be set
	 */
	public void setScaled(boolean isScaled) {
		this.scaled = isScaled;
	}

	/**
	 * Method to get date of WOD
	 * @return - date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set date of WOD
	 * @param - date to be set
	 */
	public void setDate(String newDate) {
		this.date = newDate;
	}

	/**
	 * Method to set date of WOD
	 * @param - date to be set
	 */
	public void setDate(int month, int day, int year) {
		this.date = Integer.toString(month) + Integer.toString(day) + Integer.toString(year);
	}
	
	/**
	 * Method to get the type of WOD
	 * @return - type of WOD
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method to set the type of WOD
	 * @param - type to be set
	 */
	public void setType(String newType) {
		this.type = newType;
	}

	/**
	 * Method to get seconds portion of time to complete WOD
	 * @return - seconds portion of time
	 */
	public int getTimeSec() {
		return timeSec;
	}

	/**
	 * Method to set seconds portion of time to complete WOD
	 * @param - seconds to be set
	 */
	public void setTimeSec(int newTimeSec) {
		this.timeSec = newTimeSec;
	}

	/**
	 * Method to get minutes portion of time to complete WOD
	 * @return - minutes portion of time
	 */
	public int getTimeMin() {
		return timeMin;
	}

	/**
	 * Method to set minutes portion of time to complete WOD
	 * @param - minutes to be set
	 */
	public void setTimeMin(int newTimeMin) {
		this.timeMin = newTimeMin;
	}
		
	/**
	 * Method to get number of rounds for WOD
	 * @return - number of rounds
	 */
	public int getRounds() {
		return rounds;
	}

	/**
	 * Method to set number of rounds for WOD
	 * @param - number of rounds to be set
	 */
	public void setRounds(int newRounds) {
		this.rounds = newRounds;
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
		this.tags.add(newTag);
	}
	
	/**
	 * Method to set new list of tags
	 * @param - tags to be tag
	 */
	public void setTags(LinkedList<String> newTags) {
		this.tags = newTags;
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
	 * Method to convert all of WOD info to String for text dump
	 * @return - String of WOD info
	 */
	public String toString() {
		String wodInfo;
		
		wodInfo += "WOD Title: " + title + "\n";
		wodInfo += "Date of WOD: " + toDate() + "\n";
		wodInfo += "Exercises Done:" + "\n";
		
		for(int i = 0; i < exercises.size(); i++) {
			wodInfo += "\n\t" + "Name: " + exercises.get(i) + "-";
			wodInfo += "\t" + "Reps: " + reps.get(i) + ",";
			wodInfo += "\t" + "Weight: " + weight.get(i) + ";";
		}
		
		if(benchmark == true) {
			wodInfo += "\n" + "Benchmark workout.";
		}
		
		if(prescribed == true) {
			wodInfo += "\n" + "Prescribed workout.";
		}
		
		else {
			wodInfo += "\n" + "Scaled workout.";
		}
		
		if(timeMin > 0 || timeSec > 0) {
			wodInfo += "Time taken: " + timeMin + ":" + timeSec;
		}
		
		if(rounds >= 0) {
			wodInfo += "Rounds completed " + rounds;
		}
		
		wodInfo += "\n" + "_________________________________";
		
		return wodInfo;
	}
}
