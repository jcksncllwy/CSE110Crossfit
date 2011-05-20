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
	private boolean prescribed;
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
		prescribed = true;
		date = "";
		type = "";
		timeSec = 0;
		rounds = 0;
		tags = new LinkedList<String>();
	}
	
	//METHODS
	/**
	 * @return whether or not autoTag was successful
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LinkedList<String> getExercises() {
		return exercises;
	}

	public setExercises(String exerName) {
		this.exercises.add(exerName);
	}
	
	public LinkedList<int> getReps() {
		return reps;
	}

	public void setReps(int repNum) {
		this.reps.add(repNum);
	}
	
	public LinkedList<double> getWeight() {
		return weight;
	}
	
	public void setWeight(double wei) {
		this.weight.add(wei);
	}
	
	public boolean getBenchmark() {
		return benchmark;
	}
	
	public void setBenchmark(boolean isBenchmark) {
		this.benchmark = isBenchmark;
	}
	
	public boolean getPrescribed() {
		return prescribed;
	}
	
	public void setPrescribed(boolean isPrescribed) {
		this.prescribed = isPrescribed;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the timeSec
	 */
	public int getTimeSec() {
		return timeSec;
	}

	/**
	 * @param timeSec the timeSec to set
	 */
	public void setTimeSec(int timeSec) {
		this.timeSec = timeSec;
	}

	/**
	 * @return the timeMin
	 */
	public int getTimeMin() {
		return timeMin;
	}

	/**
	 * @param timeMin the timeMin to set
	 */
	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}
		
	/**
	 * @return the rounds
	 */
	public int getRounds() {
		return rounds;
	}

	/**
	 * @param rounds the rounds to set
	 */
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	/**
	 * @return the tags
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
