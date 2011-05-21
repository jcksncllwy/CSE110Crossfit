/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a Log object, which holds all info
 * entered by the user stored in the WOD, Biometric, and Notes classes.
 */
package crossfitPlusPlus;

import java.util.*;

public class Log {

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
		return true;
	}
	
	/**
	 * Method to clear all log info
	 * @return - true if successful, false otherwise
	 */
	public boolean flush() {
		//TODO
		return true;
	}
	
	/**
	 * Method to search through WOD, Notes, and Biometrics by dates
	 * @param (String logType) - type of log info to search through
	 * @param (String date) - date to search for
	 * @return - list of WODs, Notes, and Biometrics with said date
	 */
	public LinkedList<?> searchByDate(String logType,String date) {
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD") {
			int i;
			
			for(i = 0; i < wods.size(); i++) {
				if(wods.get(i).getDate() == date) {
					results.add(wods.get(i);)
				}
			}
		}
		
		else if(logType == "Biometric") {
			int i;
			
			for(i = 0; i < biometrics.size(); i++) {
				if(biometrics.get(i).getDate() == date) {
					results.add(biometrics.get(i));
				}
			}
		}
		
		else if(logType == "Notes") {
			int i;
			
			for(i = 0; i < notes.size(); i++) {
				if(notes.get(i).getDate() == date) {
					results.add(notes.get(i));
				}
			}
		}
		
		return results;
	}
	
	/**
	 * Method to search through WOD, Notes, and Biometrics by tags
	 * @param (String logType) - type of log info to search through
	 * @param (String[] tags) - list of tags to search for
	 * @return - list of WODs, Notes, and Biometrics with said tags
	 */
	public LinkedList<?> searchByTags(String logType, String[] tags) {
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD") {
			int findTags,wodIndex,wodTags;
				
			//loop through list of WODs
			for(wodIndex = 0; wodIndex < wods.size(); wodIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(wodTags = 0; wodTags < wods.get(wodIndex).size(); wodTags++) {
						//if WOD tag matches String[] tag
						if(wods.get(wodIndex).getTags().get(wodTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					results.add(wods.get(wodIndex));
				}
			}
		}
		else if(logType == "Biometric") {
			int findTags,bioIndex,bioTags;
			
			//loop through list of WODs
			for(bioIndex = 0; bioIndex < biometrics.size(); bioIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(bioTags = 0; bioTags < biometrics.get(bioIndex).size(); bioTags++) {
						//if WOD tag matches String[] tag
						if(biometrics.get(bioIndex).getTags().get(bioTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					results.add(biometrics.get(bioIndex));
				}
			}
		}
		else if(logType == "Notes") {
			int findTags,notesIndex,notesTags;

			//loop through list of WODs
			for(notesIndex = 0; notesIndex < notes.size(); notesIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++) {
					//loop through specific WOD's tags
					for(notesTags = 0; notesTags < notes.get(notesIndex).size(); notesTags++) {
						//if WOD tag matches String[] tag
						if(notes.get(notesIndex).getTags().get(notesTags) == tags[findTags]) {
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length) {
					results.add(notes.get(notesIndex));
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
	public LinkedList<?> searchByContent(String logType, String[] keywords) {
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD"){
			int findKeyword,wodIndex,wodKeyword;
			
			//loop through list of WODs
			for(wodIndex = 0; wodIndex < wods.size(); wodIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(wodKeyword = 0; wodKeyword < wods.get(wodIndex).size(); wodKeyword++) {
						//if WOD tag matches String[] tag
						if(wods.get(wodIndex).getTags().get(wodKeyword) == keywords[findKeyword]) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					results.add(wods.get(wodIndex));
				}
			}
		}
		else if(logType == "Biometric") {
			int findKeyword,bioIndex,bioKeyword;
			
			//loop through list of WODs
			for(bioIndex = 0; bioIndex < biometrics.size(); bioIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(bioKeyword = 0; bioKeyword < biometrics.get(bioIndex).size(); bioKeyword++) {
						//if WOD tag matches String[] tag
						if(biometrics.get(bioIndex).getTags().get(bioKeyword) == tags[findKeyword]) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					results.add(biometrics.get(bioIndex));
				}
			}
		}
		
		else if(logType == "Notes") {
			int findKeyword,notesIndex,notesKeyword;

			//loop through list of WODs
			for(notesIndex = 0; notesIndex < notes.size(); notesIndex++) {
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++) {
					//loop through specific WOD's tags
					for(notesKeyword = 0; notesKeyword < notes.get(notesIndex).size(); notesKeyword++) {
						//if WOD tag matches String[] tag
						if(notes.get(notesIndex).getTags().get(notesKeyword) == keywords[findKeyword]) {
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length) {
					results.add(notes.get(notesIndex));
				}
			}
		}
		
		return results;
	}
	

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
}
