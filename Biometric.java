/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Biometric object, which holds all info
 * pertaining to the user's health. 
 */
package crossfitPlusPlus;

import java.util.*;

public class Biometric {
	
    //FIELDS
    private double weight;
    private double bodyFat;
    private int heartRate;
    private double bMI;
    private LinkedList<String> tags;
    private String date;

    
    //CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public Biometric() {
		weight = 0.0;
		bodyFat = 0.0;
		heartRate = 0;
		bMI = 0.0;
		date = "";
		tags = new LinkedList<String>();
	}

	//METHODS
	/**
	 * Method to autotag a WOD
	 * @return - true if autotag was successful, false otherwise
	 */
	public boolean autoTag() {
		if(tags.add(weight) && tags.add(bodyFat) && tags.add(heartRate)
			&& tags.add(bMI) && tags.add(date)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method to calculate the body mass index (BMI) with
	 * the given weight and height
	 * @param (double weight) - weight used in calculation
	 * @param (double height) - height used in calculation
	 * @return - calculated BMI
	 */
	public double calculateBMI(double weight, double height) {
		return weight/Math.pow(height, 2.0);
	}

	/**
	 * Method to get weight
	 * @return - weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Method to set weight
	 * @param - weight to be set
	 */
	public void setWeight(double newWeight) {
		if(weight > 0.0) {
			this.weight = newWeight;
		}
	}

	/**
	 * Method to get body fat percentage
	 * @return - body fat percentage
	 */
	public double getbodyFat() {
		return bodyFat;
	}

	/**
	 * Method to set body fat percentage
	 * @param - body fat percentage to be set
	 */
	public void setbodyFat(double newBodyFat) {
		if(bodyFat > 0.0) {
			this.bodyFat = newBodyFat;
		}
	}

	/**
	 * Method to get heart rate
	 * @return - heart rate
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * Method to set heart rate
	 * @param - heart rate to be set
	 */
	public void setHeartRate(int newHeartRate) {
		if(newHeartRate > 0) {
			this.heartRate = newHeartRate;
		}
	}

	/**
	 * Method to get BMI
	 * @return - BMI
	 */
	public double getbMI() {
		return bMI;
	}

	/**
	 * Method to set BMI
	 * @param - BMI to be set
	 */
	public void setbMI(double newBMI) {
		if(bMI > 0) {
				this.bMI = newBMI;
		}
	}

	/**
	 * Method to get date
	 * @return - date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Method to set date
	 * @param - new date to be set
	 */
	public void setDate(String newDate) {
		this.date = newDate;
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
	 * @param - tags to be tag
	 */
	public void addTags(String newTag) {
		this.tags.add(newTag);
	}
	
	/**
	 * Method to set new list of tags
	 * @param - tags to be tag
	 */
	public void setTags(LinkedList<String> newTags) {
		this.tags.clear();
		this.tags = newTags;
	}
	
	/**
	 * Method to convert all of Biometric data info to string for text dump
	 * @return - String of Biometric info
	 */
	public String toString() {
		String bioInfo;
		bioInfo += "Date: " + date + "\n";
		bioInfo += "Weight " + weight + "\n";
		bioInfo += "Body Fat Percentage " + bodyFat + "\n";
		bioInfo += "Heart Rate " + heatrate + "\n";
		bioInfo += "Body Mass Index " + bMI + "\n";
		bioInfo += "\n_________________________________";
		
		return bioInfo;
	}	
}
