/**
 * 
 */
package crossfitPlusPlus;

import java.util.*;

/**
 * @author Jackson Callaway
 *
 */
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
	public boolean autoTag(){
		tags.add(title);
		tags.add(type);
		for(int i = 0; i < exercises.size(); i++) {
			tags.add(exercises.get(i));
		}
		if(benchmark == true) {
			tags.add("benchmark");
		}
		if(rounds != 0) {
			tags.add("AMRAP");
		}
		return true;
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
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Method to get list of exercises associated with WOD
	 * @return - linked list of exercises
	 */
	public LinkedList<String> getExercises() {
		return exercises;
	}

	/**
	 * Method to set list of exercises associated with WOD
	 */
	public void setExercises(String exerName) {
		this.exercises.add(exerName);
	}
	
	/**
	 * Method to get reps used in each exercise for WOD
	 * @return - linked list of reps
	 */
	public LinkedList<int> getReps() {
		return reps;
	}

	/**
	 * Method to set reps for each exercise for WOD
	 */
	public void setReps(int repNum) {
		this.reps.add(repNum);
	}
	
	/**
	 * Method to get weights used for each exercise for WOD
	 * @return - linked list of weights
	 */
	public LinkedList<double> getWeight() {
		return weight;
	}
	
	/**
	 * Method to set weights used for each exercise for WOD
	 */
	public void setWeight(double wei) {
		this.weight.add(wei);
	}
	
	/**
	 * Method to check if WOD is a benchmark
	 * @return - true if WOD is benchmark, false otherwise
	 */
	public boolean checkBenchmark() {
		return benchmark;
	}
	
	/**
	 * Method to set WOD to be a benchmark
	 */
	public void setBenchmark(boolean isBenchmark) {
		this.benchmark = isBenchmark;
	}
	
	/**
	 * Method to check if scaled weights were used for WOD
	 * @return - true if scaled weights used, false otherwise
	 */
	public boolean checkScaled() {
		return scaled;
	}
	
	/**
	 * Method to set WOD to have used scaled weights
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
	 */
	public void setDate(String date) {
		this.date = date;
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
	 */
	public void setType(String type) {
		this.type = type;
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
	 */
	public void setTimeSec(int timeSec) {
		this.timeSec = timeSec;
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
	 */
	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}
		
	/**
	 * Method to get number of rounds of WOD
	 * @return - number of rounds
	 */
	public int getRounds() {
		return rounds;
	}

	/**
	 * Method to set number of rounds 
	 * @param - number of rounds to be set
	 */
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	/**
	 * Method to autotag a WOD
	 * @return - true if autotag was successful, false otherwise
	 */
	public LinkedList<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		String[] tagArr = tags.split(",");
		for(int i = 0; i < tagArr.size(); i++) {
			this.tags.add(tagArr[i].trim());
	}

	/**
	 * Changes date String from MMDDYY to MM/DD/YY
	 */
	public String toDate() {
		String newDate;
		newDate += date.charAt(0) + date.charAt(1) + "/";
		newDate += date.charAt(2) + date.charAt(3) + "/";
		newDate += date.charAt(4) + date.charAt(5);
		return newDate;
	}
	/**
	 * toString() method to be used for text dump;
	 */
	public String toString()
		String wodInfo;
		wodInfo += "WOD Title: " + title + "\n";
		wodInfo += "Date of WOD: " + toDate() + "\n";
		wodInfo += "Exercises Done:" + "\n";
		for(int i = 0; i < exercises.size(); i++) {
			wodInfo += "\n\t" + "Name: " + exercises.get(i) + "-";
			wodInfo += "\t" + "Reps: " + reps.get(i) + ",";
			wodInfo += "\t" + "Weight: " + weight.get(i) + ";";
		}
		if(benchmark == true){
			wodInfo += "\n" + "Benchmark workout.";
		}
		if(prescribed == true){
			wodInfo += "\n" + "Prescribed workout.";
		}
		else {
			wodInfo += "\n" + "Scaled workout.";
		}
		if(timeMin > 0 || timeSec > 0) {
			wodInfo += "Time taken: " + timeMin + ":" + timeSec;
		}
		if(rounds > 0) {
			wodInfo += "Rounds completed " + rounds;
		}
		wodInfo += "\n" + "_________________________________";
}
