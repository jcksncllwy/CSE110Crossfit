/**
 * 
 */
package crossfitPlusPlus;

import java.util.*;

/**
 * @author Jackson Callaway
 *
 */
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
	 * @return whether or not text dump was successful
	 */
	public boolean textDump(){
		//TODO
		return true;
	}
	
	/**
	 * 
	 * @return whether or not flush was successful
	 */
	public boolean flush(){
		//TODO
		return true;
	}
	
	/**
	 * 
	 * @param logType the of log to search, "WOD", "Biometric", or "Notes"
	 * @param date the date to search for
	 * @return the results that match the search
	 * @throws Exception 
	 */
	public LinkedList<?> searchByDate(String logType, GregorianCalendar date) throws Exception{
		LinkedList<?> results = new LinkedList();
		if(logType == "WOD"){
			//TODO
		}
		else if(logType == "Biometric"){
			//TODO
		}
		else if(logType == "Notes"){
			//TODO
		}
		else{
			throw new Exception("logType must be \"WOD\", \"Biometric\", or \"Notes\"");
		}
		return results;
	}
	
	/**
	 * 
	 * @param logType the type of log to search
	 * @param tags all the tags to search for
	 * @return the results that match the search
	 * @throws Exception
	 */
	public LinkedList<?> searchByTags(String logType, String[] tags) throws Exception{
		LinkedList<?> results = new LinkedList();
		if(logType == "WOD"){
			//TODO
		}
		else if(logType == "Biometric"){
			//TODO
		}
		else if(logType == "Notes"){
			//TODO
		}
		else{
			throw new Exception("logType must be \"WOD\", \"Biometric\", or \"Notes\"");
		}
		return results;
	}
	
	/**
	 * 
	 * @param logType the type of log to search
	 * @param keyWords the words to search text for
	 * @return the results that match the search
	 * @throws Exception
	 */
	public LinkedList<?> searchByContent(String logType, String[] keyWords) throws Exception{
		LinkedList<?> results = new LinkedList();
		if(logType == "WOD"){
			//TODO
		}
		else if(logType == "Biometric"){
			//TODO
		}
		else if(logType == "Notes"){
			//TODO
		}
		else{
			throw new Exception("logType must be \"WOD\", \"Biometric\", or \"Notes\"");
		}
		return results;
	}
	

	/**
	 * @return the wods
	 */
	public LinkedList<WOD> getWods() {
		return wods;
	}

	/**
	 * @param wods the wods to set
	 */
	public void setWods(LinkedList<WOD> wods) {
		this.wods = wods;
	}

	/**
	 * @return the biometrics
	 */
	public LinkedList<Biometric> getBiometrics() {
		return biometrics;
	}

	/**
	 * @param biometrics the biometrics to set
	 */
	public void setBiometrics(LinkedList<Biometric> biometrics) {
		this.biometrics = biometrics;
	}

	/**
	 * @return the notes
	 */
	public LinkedList<Notes> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(LinkedList<Notes> notes) {
		this.notes = notes;
	}
	
	
	
	

}
