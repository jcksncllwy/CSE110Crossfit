/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Log object, which holds all info
 * entered by the user stored in the WOD, Biometric, and Notes classes.
 */
package com.cs110.stdev.crossfit.backend;

import java.util.*;
import java.io.*;

public class Log implements Serializable {

	//FIELDS
	private LinkedList<WOD> wods;
	private LinkedList<Biometric> biometrics;
	private LinkedList<Notes> notes;
	
	
	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public Log() {
		wods = new LinkedList<WOD>();
		biometrics = new LinkedList<Biometric>();
		notes = new LinkedList<Notes>();
	}

	//METHODS
	/**
	 * Method to store all of the user's info (WOD, Notes, Biometrics)
	 * in a text file
	 * @return - true if successful, false otherwise
	 */
	public boolean textDump() {
		//TODO
		/*
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("CrossfitLog.txt"));
			writer.write(this.toString());
		} catch (Exception ex) {
			SimpleOutput.showError("Error creating file.");
			System.exit(1);
		}
		*/		
		return true;
	}
	
	/**
	 * Method to clear all log info
	 * @return - true if successful, false otherwise
	 */
	public boolean flush() {
		wods.clear();
		biometrics.clear();
		notes.clear();
		
		if(wods.size() == 0 && biometrics.size() == 0 && notes.size() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method to search through WOD, Notes, or Biometrics by dates
	 * and return all relevant info
	 * @param (String logType) - type of log info to search through
	 * @param (String date) - date to search for
	 * @return - list of WODs, Notes, and Biometrics with said date
	 */
	public LinkedList<?> searchByDate(String logType,String date) {
		LinkedList<?> results =  new LinkedList();
		int i;
		
		if(logType == "WOD") {	
			results = new LinkedList<WOD>();
			for(i = 0; i < wods.size(); i++) {
				if(wods.get(i).getDate() == date) {
					//results.add(wods.get(i));
				}
			}
		}
		
		else if(logType == "Biometric") {
			results = new LinkedList<Biometric>();
			for(i = 0; i < biometrics.size(); i++) {
				if(biometrics.get(i).getDate() == date) {
					//results.add(biometrics.get(i));
				}
			}
		}
		
		else if(logType == "Notes") {
			results = new LinkedList<Notes>();
			for(i = 0; i < notes.size(); i++) {
				if(notes.get(i).getDate() == date) {
					//results.add(notes.get(i));
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Method to search through WOD, Notes, and Biometrics by tags
	 * and return all relevant info
	 * @param (String logType) - type of log info to search through
	 * @param (String[] tags) - list of tags to search for
	 * @return - list of WODs, Notes, and Biometrics with said tags
	 */
	public LinkedList<?> searchByTags(String logType, String[] tags) {
		LinkedList<?> results =  new LinkedList();
		int findTags,infoIndex,infoTags,found = 0;
		
		if(logType == "WOD") {	
			results = new LinkedList<WOD>();
			//loop through list of WODs
			for(infoIndex = 0; infoIndex < wods.size(); infoIndex++) {				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(infoTags = 0; infoTags < wods.get(infoIndex).getTags().size(); infoTags++) {
						//if WOD tag matches String[] tag
						if(wods.get(infoIndex).getTags().get(infoTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					//results.add(wods.get(infoIndex));
				}
			}
		}
		else if(logType == "Biometric") {
			results = new LinkedList<Biometric>();
			found = 0;
			
			//loop through list of WODs
			for(infoIndex = 0; infoIndex < biometrics.size(); infoIndex++) {
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(infoTags = 0; infoTags < biometrics.get(infoIndex).getTags().size(); infoTags++) {
						//if WOD tag matches String[] tag
						if(biometrics.get(infoIndex).getTags().get(infoTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					//results.add(biometrics.get(infoIndex));
				}
			}
		}
		else if(logType == "Notes") {
			results = new LinkedList<Notes>();
			found = 0;
			
			//loop through list of WODs
			for(infoIndex = 0; infoIndex < notes.size(); infoIndex++) {
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(infoTags = 0; infoTags < notes.get(infoIndex).getTags().size(); infoTags++) {
						//if WOD tag matches String[] tag
						if(notes.get(infoIndex).getTags().get(infoTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					//results.add(notes.get(infoIndex));
				}
			}
		}
		return results;
	}
	
	/**
	 * Method to search through WOD, Notes, and Biometrics by content
	 * @param (String logType) - type of log info to search through
	 * @param (String[] keywords) - list of keywords to search for
	 * @return - list of WODs, Notes, and Biometrics with said keywords
	 */
	/*
	public LinkedList<?> searchByContent(String logType, String[] keywords) {
		LinkedList<?> results = new LinkedList();
		int findKeyword,infoIndex,infoKeyword,found = 0;
		
		if(logType == "WOD") {
			//loop through list of WODs
			for(infoIndex = 0; infoIndex < wods.size(); infoIndex++) {
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(infoKeyword = 0; infoKeyword < wods.get(infoIndex).getTags().size(); infoKeyword++) {
						//if WOD tag matches String[] tag
						if(wods.get(infoIndex).getTags().get(infoKeyword) == keywords[findKeyword]) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					//results.add(wods.get(infoIndex));
				}
			}
		}
		else if(logType == "Biometric") {
			found = 0;
			
			//loop through list of WODs
			for(infoIndex = 0; infoIndex < biometrics.size(); infoIndex++) {
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(infoKeyword = 0; infoKeyword < biometrics.get(infoIndex).getTags().size(); infoKeyword++) {
						//if WOD tag matches String[] tag
						if(biometrics.get(infoIndex).getTags().get(infoKeyword) == tags.get(findKeyword)) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					results.add(biometrics.get(infoIndex));
				}
			}
		}
		
		else if(logType == "Notes") {
			found = 0;

			//loop through list of WODs
			for(infoIndex = 0; infoIndex < notes.size(); infoIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(infoKeyword = 0; infoKeyword < notes.get(infoIndex).size(); infoKeyword++) {
						//if WOD tag matches String[] tag
						if(notes.get(infoIndex).getTags().get(infoKeyword) == keywords[findKeyword]) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					results.add(notes.get(infoIndex));
				}
			}
		}
		
		return results;
	}
	
*/
	/**
	 * Method to get list of WODS
	 * @return - list of WODS
	 */
	public LinkedList<WOD> getWods() {
		return wods;
	}

	/**
	 * Method to set list of WODS
	 * @param - new list of WODS to be set
	 */
	public void setWods(LinkedList<WOD> newWods) {
		this.wods = newWods;
	}

	/**
	 * Method to get list of Biometrics
	 * @return - list of Biometrics
	 */
	public LinkedList<Biometric> getBiometrics() {
		return biometrics;
	}

	/**
	 * Method to set list of Biometrics
	 * @param - new list of Biometrics to be set
	 */
	public void setBiometrics(LinkedList<Biometric> newBiometrics) {
		this.biometrics = newBiometrics;
	}

	/**
	 * Method to get list of Notes
	 * @return - list of Notes
	 */
	public LinkedList<Notes> getNotes() {
		return notes;
	}

	/**
	 * Method to set list of Notes
	 * @param - new list of Notes to be set
	 */
	public void setNotes(LinkedList<Notes> newNotes) {
		this.notes = newNotes;
	}
	
	public String toString() {
		String log = "";
		int i;
		
		log += "WODs from " + wods.get(0).toDate() + "to " + wods.get(wods.size() - 1).toDate();
		
		for(i = 0; i < wods.size(); i++) {
			log += "\n";
			log += wods.get(i).toString();
		}
		
		log += "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
		log += "Biometrics from " + biometrics.get(0).toDate() + "to " + biometrics.get(wods.size() - 1).toDate();
		
		for(i = 0; i < biometrics.size(); i++) {
			log += "\n";
			log += biometrics.get(i).toString();
		}
		
		log += "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
		log += "Notes from " + notes.get(0).toDate() + "to " + notes.get(wods.size() - 1).toDate();
		
		for(i = 0; i < notes.size(); i++) {
			log += "\n";
			log += notes.get(i).toString();
		}
		
		log += "\n>>>>>>>>>>>>>END OF LOG CONTENTS>>>>>>>>>>>>>>>>>";
		
		return log;
	}
	
	public void sortWodByDate() {
		LinkedList<WOD> resultWod = new LinkedList<WOD>();
		resultWod.add(wods.get(i));
		for(int i = 1; i < wods.size(); i++) {
			WOD currWod = wods.get(i);
			int currYear = currWod.getYear();
			int currMonth = currWod.getMonth();
			int currDay = currWod.getDay();
			for(int j = 0; j < resultWod.size(); j++) {
				WOD nowWod = resultWod.get(j);
				if(currWod.getYear() < nowWod.getYear()) {
					resultWod.add(j, currWod);
				}
				else if(currWod.getYear() == currWod.getYear()) {
					if(currWod.getMonth() < nowWod.getMonth()) {
						resultWod.add(j, currWod);
					}
					else if(currWod.getMonth() == currWod.getMonth()) {
						if(currWod.getDay() < nowWod.getDay()) {
							resultWod.add(j, currWod);
						}
						else if(currWod.getDay() > currWod.getDay()) {
							if(j == resultWod.size() - 1) {
								resultWod.add(currWod);
							}
						}
					}
					else if(currWod.getMonth() > currWod.getMonth() && j == resultWod.size() - 1) {
						resultWod.add(currWod);
					}
				}
				else if(currWod.getYear() > currWod.getYear() && j == resultWod.size() - 1) {
					resultWod.add(currWod);
				}
			}
		}
	}
}
