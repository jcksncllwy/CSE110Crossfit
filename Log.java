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
	 */
	public LinkedList<?> searchByDate(String logType, GregorianCalendar date){
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD"){
			int i;
			
			for(i = 0; i < wods.size(); i++)
			{
				if(wods.get(i).getDate() == date)
				{
					results.add(wods.get(i);)
				}
			}
		}
		
		else if(logType == "Biometric"){
			int i;
			
			for(i = 0; i < biometrics.size(); i++)
			{
				if(biometrics.get(i).getDate() == date)
				{
					results.add(biometrics.get(i));
				}
			}
		}
		
		else if(logType == "Notes"){
			int i;
			
			for(i = 0; i < notes.size(); i++)
			{
				if(notes.get(i).getDate() == date)
				{
					results.add(notes.get(i));
				}
			}
		}
		
		return results;
	}
	
	/**
	 * 
	 * @param logType the type of log to search
	 * @param tags all the tags to search for in WOD (and)
	 * @return the results that match the search
	 */
	public LinkedList<?> searchByTags(String logType, String[] tags){
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD"){
			int findTags,wodIndex,wodTags;
				
			//loop through list of WODs
			for(wodIndex = 0; wodIndex < wods.size(); wodIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++)
				{
					//loop through specific WOD's tags
					for(wodTags = 0; wodTags < wods.get(wodIndex).size(); wodTags++)
					{
						//if WOD tag matches String[] tag
						if(wods.get(wodIndex).getTags().get(wodTags) == tags[findTags])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length)
				{
					results.add(wods.get(wodIndex));
				}
			}
		}
		else if(logType == "Biometric"){
			int findTags,bioIndex,bioTags;
			
			//loop through list of WODs
			for(bioIndex = 0; bioIndex < biometrics.size(); bioIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++)
				{
					//loop through specific WOD's tags
					for(bioTags = 0; bioTags < biometrics.get(bioIndex).size(); bioTags++)
					{
						//if WOD tag matches String[] tag
						if(biometrics.get(bioIndex).getTags().get(bioTags) == tags[findTags])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length)
				{
					results.add(biometrics.get(bioIndex));
				}
			}
		}
		else if(logType == "Notes"){
			int findTags,notesIndex,notesTags;

			//loop through list of WODs
			for(notesIndex = 0; notesIndex < notes.size(); notesIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findTags = 0; findTags < tags.length; findTags++)
				{
					//loop through specific WOD's tags
					for(notesTags = 0; notesTags < notes.get(notesIndex).size(); notesTags++)
					{
						//if WOD tag matches String[] tag
						if(notes.get(notesIndex).getTags().get(notesTags) == tags[findTags])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == tags.length)
				{
					results.add(notes.get(notesIndex));
				}
			}
		}
		return results;
	}
	
	/**
	 * 
	 * @param logType the type of log to search
	 * @param keyWords the words to search text for
	 * @return the results that match the search
	 */
	public LinkedList<?> searchByContent(String logType, String[] keywords){
		LinkedList<?> results = new LinkedList();
		
		if(logType == "WOD"){
			int findKeyword,wodIndex,wodKeyword;
			
			//loop through list of WODs
			for(wodIndex = 0; wodIndex < wods.size(); wodIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++)
				{
					//loop through specific WOD's tags
					for(wodKeyword = 0; wodKeyword < wods.get(wodIndex).size(); wodKeyword++)
					{
						//if WOD tag matches String[] tag
						if(wods.get(wodIndex).getTags().get(wodKeyword) == keywords[findKeyword])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length)
				{
					results.add(wods.get(wodIndex));
				}
			}
		}
		else if(logType == "Biometric"){
			int findKeyword,bioIndex,bioKeyword;
			
			//loop through list of WODs
			for(bioIndex = 0; bioIndex < biometrics.size(); bioIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++)
				{
					//loop through specific WOD's tags
					for(bioKeyword = 0; bioKeyword < biometrics.get(bioIndex).size(); bioKeyword++)
					{
						//if WOD tag matches String[] tag
						if(biometrics.get(bioIndex).getTags().get(bioKeyword) == tags[findKeyword])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length)
				{
					results.add(biometrics.get(bioIndex));
				}
			}
		}
		
		else if(logType == "Notes"){
			int findKeyword,notesIndex,notesKeyword;

			//loop through list of WODs
			for(notesIndex = 0; notesIndex < notes.size(); notesIndex++)
			{
				int found = 0;
				
				//loop through tags to be found
				for(findKeyword = 0; findKeyword < keywords.length; findKeyword++)
				{
					//loop through specific WOD's tags
					for(notesKeyword = 0; notesKeyword < notes.get(notesIndex).size(); notesKeyword++)
					{
						//if WOD tag matches String[] tag
						if(notes.get(notesIndex).getTags().get(notesKeyword) == keywords[findKeyword])
						{
							found++;
							break;
						}
					}
				}
				
				if(found == keywords.length)
				{
					results.add(notes.get(notesIndex));
				}
			}
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
