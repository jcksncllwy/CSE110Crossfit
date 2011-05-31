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
	
	public void addWodSort(WOD currWod) {
		boolean inserted = false;
		int left = 0;
		int right = wods.size() - 1;
		int mid = wods.size()/2;
		if(wods.size() == 0) {
			wods.add(currWod);
			return;
		}
		while(inserted == false) {
			WOD nowWod = wods.get(mid);
			if(currWod.getYear() < nowWod.getYear()) {
				right = mid;
				mid = (right + left)/2;
				if(left == mid || right == mid) {
					inserted = true;
				}
			}
			else if(currWod.getYear() == nowWod.getYear()) {
				if(currWod.getMonth() < nowWod.getMonth()) {
					right = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						wods.add(mid, currWod);
						inserted = true;
					}
				}
				else if(currWod.getMonth() == nowWod.getMonth()) {
					if(currWod.getDay() < nowWod.getDay()) {
						right = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							wods.add(mid, currWod);
							inserted = true;
						}
					}
					else if(currWod.getDay() > nowWod.getDay()) {
						left = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							wods.add(mid + 1, currWod);
							inserted = true;
						}
						
					}
				}
				else if(currWod.getMonth() > nowWod.getMonth()) {
					left = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						wods.add(mid + 1, currWod);
						inserted = true;
					}
				}
			}
			else if(currWod.getYear() > nowWod.getYear()) {
				left = mid;
				mid = (right + left)/2;
				if(left == mid || right == mid) {
					wods.add(mid + 1, currWod);
					inserted = true;
				}
			}
		}
	}
	
	/**
	 * Method to store all of the user's info (WOD, Notes, Biometrics)
	 * in a text file
	 * @return - true if successful, false otherwise
	 */
	public boolean textDump() {
		//TODO
		/*
		try{
			File cardRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/CrossfitLogs");
			if (!cardRoot.exists()) {
				cardRoot.mkdirs();
			}
			String filename = "Log" + wods.get(0).getDate()
				+ "to" + wods.get(wods.size()-1).getDate() + ".txt";
			File gpxfile = new File(cardRoot, filename);
			if(gpxfile.canWrite()) {		
				FileWriter writer = new FileWriter(gpxfile);
				writer.append(this.toString());
				writer.flush();
				writer.close();
				Toast.makeText(this, "Log information has been saved on your SD card.", Toast.LENGTH_SHORT).show();
			} else {
				return false;
			}
		}
		catch(IOException e)
		{
			 e.printStackTrace();
			 importError = e.getMessage();
			 iError();
			 return false;
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
	 /*
	public LinkedList<?> searchByTags(String logType, String[] tags) {
		LinkedList<?> results;
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
	*/

	public LinkedList<WOD> searchWODTags(String[] tags) {
		LinkedList<WOD> results = new LinkedList<WOD>();
		int findTags,infoIndex,infoTags,found = 0;
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
				results.add(wods.get(infoIndex));
			}
		}
		return results;
	}
	
	public LinkedList<WOD> searchWODTags(String tag) {
		LinkedList<WOD> results = new LinkedList<WOD>();
		int findTags,infoIndex,infoTags = 0;
		boolean found = false;
		//loop through list of WODs
		for(infoIndex = 0; infoIndex < wods.size(); infoIndex++) {
			found = false;
			//loop through specific WOD's tags
			for(infoTags = 0; infoTags < wods.get(infoIndex).getTags().size(); infoTags++) {
				//if a WOD tag matches tag
				if(wods.get(infoIndex).getTags().get(infoTags).equals(tag)) {
					found = true;
					break;
				}
			}
			//Add the WOD if it was found
			if(found == true) {
				results.add(wods.get(infoIndex));
			}
		}
		return results;
	}

	public LinkedList<Biometric> searchBiomTags(String[] tags) {
		LinkedList<Biometric> results = new LinkedList<Biometric>();
		int findTags,infoIndex,infoTags,found = 0;
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
				results.add(biometrics.get(infoIndex));
			}
		}
		return results;
	}

	public LinkedList<Biometric> searchBiomTags(String tag) {
		LinkedList<Biometric> results = new LinkedList<Biometric>();
		int findTags,infoIndex,infoTags = 0;
		boolean found = false;
		//loop through list of Biometrics
		for(infoIndex = 0; infoIndex < biometrics.size(); infoIndex++) {
			found = false;
			//loop through specific Biometric's tags
			for(infoTags = 0; infoTags < biometrics.get(infoIndex).getTags().size(); infoTags++) {
				//if a Biometric tag matches tag
				if(biometrics.get(infoIndex).getTags().get(infoTags).equals(tag)) {
					found = true;
					break;
				}
			}
			//Add the Biometric if it was found
			if(found == true) {
				results.add(biometrics.get(infoIndex));
			}
		}
		return results;
	}
	
	public LinkedList<Notes> searchNoteTags(String[] tags) {
		LinkedList<Notes> results = new LinkedList<Notes>();
		int findTags,infoIndex,infoTags,found = 0;
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
				results.add(notes.get(infoIndex));
			}
		}
		return results;
	}

	public LinkedList<Notes> searchNoteTags(String tag) {
		LinkedList<Notes> results = new LinkedList<Notes>();
		int findTags,infoIndex,infoTags = 0;
		boolean found = false;
		//loop through list of Notess
		for(infoIndex = 0; infoIndex < notes.size(); infoIndex++) {
			found = false;
			//loop through specific Notes's tags
			for(infoTags = 0; infoTags < notes.get(infoIndex).getTags().size(); infoTags++) {
				//if a Notes tag matches tag
				if(notes.get(infoIndex).getTags().get(infoTags).equals(tag)) {
					found = true;
					break;
				}
			}
			//Add the Notes if it was found
			if(found == true) {
				results.add(notes.get(infoIndex));
			}
		}
		return results;
	}

	/*This is a method I wrote up relatively quickly, so there is most likely a problem
	* in the logic. But I figured we need this to plot points on the graph "from" one
	* date "to" another (of couse after sorting the information) - James*/
	/**
	 * Method to get a LinkedList of WODs in a range of dates
	 * @param (String from) - start of date range
	 * @param (String to) - end of date range
	 * @return - list of WODs within the range
	 */
	 
	public LinkedList<WOD> getWODsFromTo(String from, String to)
    {
        LinkedList<WOD> resultList = new LinkedList<WOD>();
        int start, i = 0;
        int end = 0;
        for(i = 0; i < wods.size(); i++)
        {
            if(wods.get(i).getYear() == Integer.parseInt(from.substring(4,8)))
            {
                if(wods.get(i).getMonth() > Integer.parseInt(from.substring(0,2)))
                {
                    break;
                }
                else if(wods.get(i).getMonth() == Integer.parseInt(from.substring(0,2)))
                {
                    if(wods.get(i).getDay() >= Integer.parseInt(from.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(wods.get(i).getYear() > Integer.parseInt(from.substring(4,8)))
            {
                    break;
            }
            
        }
        start = i;
        for(i = 0; i < wods.size(); i++)
        {
            if(wods.get(i).getYear() == Integer.parseInt(from.substring(4,8)))
            {
                if(wods.get(i).getMonth() > Integer.parseInt(to.substring(0,2)))
                {
                    break;
                }
                else if(wods.get(i).getMonth() == Integer.parseInt(to.substring(0,2)))
                {
                    if(wods.get(i).getDay() >= Integer.parseInt(to.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(wods.get(i).getYear() > Integer.parseInt(from.substring(4,8)))
            {
                    break;
            }
        }
        end = i;
        for(i = start; i <= end; i++)
        {
            resultList.add(wods.get(i));
        }
        return resultList;
    }
    
    public LinkedList<Biometric> getBIOsFromTo(String from, String to)
    {
        LinkedList<Biometric> resultList = new LinkedList<Biometric>();
        int start, i = 0;
        int end = 0;
        for(i = 0; i < wods.size(); i++)
        {
            if(biometrics.get(i).getYear() == Integer.parseInt(from.substring(4,8)))
            {
                if(biometrics.get(i).getMonth() > Integer.parseInt(from.substring(0,2)))
                {
                    break;
                }
                else if(biometrics.get(i).getMonth() == Integer.parseInt(from.substring(0,2)))
                {
                    if(biometrics.get(i).getDay() >= Integer.parseInt(from.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(biometrics.get(i).getYear() > Integer.parseInt(from.substring(4,8)))
            {
                    break;
            }
            
        }
        start = i;
        for(i = 0; i < biometrics.size(); i++)
        {
            if(biometrics.get(i).getYear() == Integer.parseInt(from.substring(4,8)))
            {
                if(biometrics.get(i).getMonth() > Integer.parseInt(to.substring(0,2)))
                {
                    break;
                }
                else if(biometrics.get(i).getMonth() == Integer.parseInt(to.substring(0,2)))
                {
                    if(biometrics.get(i).getDay() >= Integer.parseInt(to.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(biometrics.get(i).getYear() > Integer.parseInt(from.substring(4,8)))
            {
                    break;
            }
        }
        end = i;
        for(i = start; i <= end; i++)
        {
            resultList.add(biometrics.get(i));
        }
        return resultList;
    }

	/**
	 * Method to get a LinkedList of Notes in a range of dates
	 * @param (String from) - start of date range
	 * @param (String to) - end of date range
	 * @return - list of WODs within the range
	 */
	public LinkedList<Notes> getNotesFromTo(String from, String to)
    {
        LinkedList<Notes> resultList = new LinkedList<Notes>();
        int start, i = 0;
        int end = 0;
        for(i = 0; i < wods.size(); i++)
        {
            if(notes.get(i).getYear() == Integer.parseInt(from.substring(4,8)))
            {
                if(notes.get(i).getMonth() > Integer.parseInt(from.substring(0,2)))
                {
                    break;
                }
                else if(notes.get(i).getMonth() == Integer.parseInt(from.substring(0,2)))
                {
                    if(notes.get(i).getDay() >= Integer.parseInt(from.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(notes.get(i).getYear() > Integer.parseInt(from.substring(4,8)))
            {
                    break;
            }
            
        }
        start = i;
        for(i = 0; i < notes.size(); i++)
        {
            if(notes.get(i).getYear() == Integer.parseInt(to.substring(4,8)))
            {
                if(notes.get(i).getMonth() > Integer.parseInt(to.substring(0,2)))
                {
                    break;
                }
                else if(notes.get(i).getMonth() == Integer.parseInt(to.substring(0,2)))
                {
                    if(notes.get(i).getDay() >= Integer.parseInt(to.substring(2,4)))
                    {
                        break;
                    }
                }
            }
            if(notes.get(i).getYear() > Integer.parseInt(to.substring(4,8)))
            {
                    break;
            }
        }
        end = i;
        for(i = start; i <= end; i++)
        {
            resultList.add(notes.get(i));
        }
		Notes checkFirst = resultList.get(0);
		if(checkFirst.getYear() < Integer.parseInt(from.substring(4,8))) {
			resultList.clear();
		} 
		else if(checkFirst.getYear() == Integer.parseInt(from.substring(4,8))) {
			if(checkFirst.getMonth() < Integer.parseInt(from.substring(0,2))) {
				resultList.clear();
			} 
			else if(checkFirst.getMonth() == Integer.parseInt(from.substring(0,2))) {
				if(checkFirst.getDay() < Integer.parseInt(from.substring(2,4))) {
					resultList.clear();
				}				
			}
		}
        return resultList;
    }
	
	/**
	 * Method to get an array of recorded body weights within a date range
	 * Used for graphing purposes
	 * @param (String from) - start of date range
	 * @param (String to) - end of date range
	 * @return - list of weights from biometrics
	 */
	public double[] returnWeightRange(String from, String to) {
		LinkedList<Biometric> resultList = getBIOsFromTo(from, to);
		double[] weights = new double[resultList.size()];
		for(int i = 0; i < resultList.size(); i++) {
			weights[i] = resultList.get(i).getWeight();
		}
		
		return weights;
	}
	
	/**
	 * Method to search through WOD, Notes, and Biometrics by content
	 * @param (String logType) - type of log info to search through
	 * @param (String[] keywords) - list of keywords to search for
	 * @return - list of WODs, Notes, and Biometrics with said keywords
	 */
	/*
	public LinkedList<?> searchByContent(String logType, String[] keywords) {
		LinkedList<?> results;
		int findKeyword,infoIndex,infoKeyword,found = 0;
		
		if(logType == "WOD") {
			results = new LinkedList<WOD>();
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
			results = new LinkedList<Biometric>();
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
			results = new LinkedList<Notes>();
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

	/**
	 * Method to sort the list of WODs by their dates (increasing index
	 * for later dates)
	 */
	public void sortWodByDate() {
		LinkedList<WOD> resultWod = new LinkedList<WOD>();
		resultWod.add(wods.get(0));
		for(int i = 1; i < wods.size(); i++) {
			WOD currWod = wods.get(i);
			for(int j = 0; j < resultWod.size(); j++) {
				WOD nowWod = resultWod.get(j);
				if(currWod.getYear() < nowWod.getYear()) {
					resultWod.add(j, currWod);
				}
				else if(currWod.getYear() == nowWod.getYear()) {
					if(currWod.getMonth() < nowWod.getMonth()) {
						resultWod.add(j, currWod);
					}
					else if(currWod.getMonth() == nowWod.getMonth()) {
						if(currWod.getDay() < nowWod.getDay()) {
							resultWod.add(j, currWod);
						}
						else if(currWod.getDay() > nowWod.getDay()) {
							if(j == resultWod.size() - 1) {
								resultWod.add(currWod);
							}
						}
					}
					else if(currWod.getMonth() > nowWod.getMonth() && j == resultWod.size() - 1) {
						resultWod.add(currWod);
					}
				}
				else if(currWod.getYear() > nowWod.getYear() && j == resultWod.size() - 1) {
					resultWod.add(currWod);
				}
			}
		}
	}

	/**
	 * Method to sort the list of Biometricss by their dates (increasing index
	 * for later dates)
	 */
	public void sortBiomByDate() {
		LinkedList<Biometric> resultBiom = new LinkedList<Biometric>();
		resultBiom.add(biometrics.get(0));
		for(int i = 1; i < biometrics.size(); i++) {
			Biometric currBiom = biometrics.get(i);
			for(int j = 0; j < resultBiom.size(); j++) {
				Biometric nowBiom = resultBiom.get(j);
				if(currBiom.getYear() < nowBiom.getYear()) {
					resultBiom.add(j, currBiom);
				}
				else if(currBiom.getYear() == nowBiom.getYear()) {
					if(currBiom.getMonth() < nowBiom.getMonth()) {
						resultBiom.add(j, currBiom);
					}
					else if(currBiom.getMonth() == nowBiom.getMonth()) {
						if(currBiom.getDay() < nowBiom.getDay()) {
							resultBiom.add(j, currBiom);
						}
						else if(currBiom.getDay() > nowBiom.getDay()) {
							if(j == resultBiom.size() - 1) {
								resultBiom.add(currBiom);
							}
						}
					}
					else if(currBiom.getMonth() > nowBiom.getMonth() && j == resultBiom.size() - 1) {
						resultBiom.add(currBiom);
					}
				}
				else if(currBiom.getYear() > nowBiom.getYear() && j == resultBiom.size() - 1) {
					resultBiom.add(currBiom);
				}
			}
		}
	}

	/**
	 * Method to sort the list of Notes by their dates (increasing index
	 * for later dates)
	 */
	public void sortNotesByDate() {
		LinkedList<Notes> resultNote = new LinkedList<Notes>();
		resultNote.add(notes.get(0));
		for(int i = 1; i < notes.size(); i++) {
			Notes currNote = notes.get(i);
			for(int j = 0; j < resultNote.size(); j++) {
				Notes nowNote = resultNote.get(j);
				if(currNote.getYear() < nowNote.getYear()) {
					resultNote.add(j, currNote);
				}
				else if(currNote.getYear() == nowNote.getYear()) {
					if(currNote.getMonth() < nowNote.getMonth()) {
						resultNote.add(j, currNote);
					}
					else if(currNote.getMonth() == nowNote.getMonth()) {
						if(currNote.getDay() < nowNote.getDay()) {
							resultNote.add(j, currNote);
						}
						else if(currNote.getDay() > nowNote.getDay()) {
							if(j == resultNote.size() - 1) {
								resultNote.add(currNote);
							}
						}
					}
					else if(currNote.getMonth() > nowNote.getMonth() && j == resultNote.size() - 1) {
						resultNote.add(currNote);
					}
				}
				else if(currNote.getYear() > nowNote.getYear() && j == resultNote.size() - 1) {
					resultNote.add(currNote);
				}
			}
		}
	}

	//Binary sort methods are below

	/*
	public void sortWodByDate() {
		LinkedList<WOD> resultWod = new LinkedList<WOD>();
		resultWod.add(wods.get(0));
		for(int i = 1; i < wods.size(); i++) {
			WOD currWod = wods.get(i);
			boolean inserted = false;
			int left = 0;
			int right = resultWod.size() - 1;
			int mid = resultWod.size()/2;
			while(inserted == false) {
				WOD nowWod = resultWod.get(mid);
				if(currWod.getYear() < nowWod.getYear()) {
					right = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						inserted = true;
					}
				}
				else if(currWod.getYear() == nowWod.getYear()) {
					if(currWod.getMonth() < nowWod.getMonth()) {
						right = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultWod.add(mid, currWod);
							inserted = true;
						}
					}
					else if(currWod.getMonth() == nowWod.getMonth()) {
						if(currWod.getDay() < nowWod.getDay()) {
							right = mid;
							mid = (right + left)/2;
							if(left == mid || right == mid) {
								resultWod.add(mid, currWod);
								inserted = true;
							}
						}
						else if(currWod.getDay() > nowWod.getDay()) {
							if(j == resultWod.size() - 1) {
								left = mid;
								mid = (right + left)/2;
								if(left == mid || right == mid) {
									resultWod.add(mid + 1, currWod);
									inserted = true;
								}
							}
						}
					}
					else if(currWod.getMonth() > nowWod.getMonth()) {
						left = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultWod.add(mid + 1, currWod);
							inserted = true;
						}
					}
				}
				else if(currWod.getYear() > nowWod.getYear()) {
					left = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						resultWod.add(mid + 1, currWod);
						inserted = true;
					}
				}
			}
		}
		wods = resultWod;
	}
	
	public void sortBiomByDate() {
		LinkedList<Biometric> resultBiom = new LinkedList<Biometric>();
		resultBiom.add(biometrics.get(0));
		for(int i = 1; i < biometrics.size(); i++) {
			Biometric currBiom = biometrics.get(i);
			boolean inserted = false;
			int left = 0;
			int right = resultBiom.size() - 1;
			int mid = resultBiom.size()/2;
			while(inserted == false) {
				Biometric nowBiom = resultBiom.get(mid);
				if(currBiom.getYear() < nowBiom.getYear()) {
					right = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						inserted = true;
					}
				}
				else if(currBiom.getYear() == nowBiom.getYear()) {
					if(currBiom.getMonth() < nowBiom.getMonth()) {
						right = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultBiom.add(mid, currBiom);
							inserted = true;
						}
					}
					else if(currBiom.getMonth() == nowBiom.getMonth()) {
						if(currBiom.getDay() < nowBiom.getDay()) {
							right = mid;
							mid = (right + left)/2;
							if(left == mid || right == mid) {
								resultBiom.add(mid, currBiom);
								inserted = true;
							}
						}
						else if(currBiom.getDay() > nowBiom.getDay()) {
							if(j == resultBiom.size() - 1) {
								left = mid;
								mid = (right + left)/2;
								if(left == mid || right == mid) {
									resultBiom.add(mid + 1, currBiom);
									inserted = true;
								}
							}
						}
					}
					else if(currBiom.getMonth() > nowBiom.getMonth()) {
						left = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultBiom.add(mid + 1, currBiom);
							inserted = true;
						}
					}
				}
				else if(currBiom.getYear() > nowBiom.getYear()) {
					left = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						resultBiom.add(mid + 1, currBiom);
						inserted = true;
					}
				}
			}
		}
		biometrics = resultBiom;
	}
	
	public void sortNoteByDate() {
		LinkedList<Notes> resultNote = new LinkedList<Notes>();
		resultNote.add(notes.get(0));
		for(int i = 1; i < notes.size(); i++) {
			Notes currNote = notes.get(i);
			boolean inserted = false;
			int left = 0;
			int right = resultNote.size() - 1;
			int mid = resultNote.size()/2;
			while(inserted == false) {
				Notes nowNote = resultNote.get(mid);
				if(currNote.getYear() < nowNote.getYear()) {
					right = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						inserted = true;
					}
				}
				else if(currNote.getYear() == nowNote.getYear()) {
					if(currNote.getMonth() < nowNote.getMonth()) {
						right = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultNote.add(mid, currNote);
							inserted = true;
						}
					}
					else if(currNote.getMonth() == nowNote.getMonth()) {
						if(currNote.getDay() < nowNote.getDay()) {
							right = mid;
							mid = (right + left)/2;
							if(left == mid || right == mid) {
								resultNote.add(mid, currNote);
								inserted = true;
							}
						}
						else if(currNote.getDay() > nowNote.getDay()) {
							if(j == resultNote.size() - 1) {
								left = mid;
								mid = (right + left)/2;
								if(left == mid || right == mid) {
									resultNote.add(mid + 1, currNote);
									inserted = true;
								}
							}
						}
					}
					else if(currNote.getMonth() > nowNote.getMonth()) {
						left = mid;
						mid = (right + left)/2;
						if(left == mid || right == mid) {
							resultNote.add(mid + 1, currNote);
							inserted = true;
						}
					}
				}
				else if(currNote.getYear() > nowNote.getYear()) {
					left = mid;
					mid = (right + left)/2;
					if(left == mid || right == mid) {
						resultNote.add(mid + 1, currNote);
						inserted = true;
					}
				}
			}
		}
		notes = resultNote;
	}
	*/
}
