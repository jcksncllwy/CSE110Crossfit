/**
 * 
 */
package crossfitPlusPlus;
import java.util.*;

/**
 * @author Jackson Callaway
 *
 */
public class Notes {
	
	//FIELDS
    private String injuries;
	private String exerciseComments;
	private String nutrition;
	private String other;
	private LinkedList<String> tags;
	private GregorianCalendar date;

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
		date = new GregorianCalendar();
	}
	
	//METHODS
	/**
	 * @return whether or not autoTag was successful
	 */
	public boolean autoTag(){
		//TODO
		return true;
	}

	/**
	 * @return the injuries
	 */
	public String getInjuries() {
		return injuries;
	}

	/**
	 * @param injuries the injuries to set
	 */
	public void setInjuries(String injuries) {
		this.injuries = injuries;
	}

	/**
	 * @return the exerciseComments
	 */
	public String getExerciseComments() {
		return exerciseComments;
	}

	/**
	 * @param exerciseComments the exerciseComments to set
	 */
	public void setExerciseComments(String exerciseComments) {
		this.exerciseComments = exerciseComments;
	}

	/**
	 * @return the nutrition
	 */
	public String getNutrition() {
		return nutrition;
	}

	/**
	 * @param nutrition the nutrition to set
	 */
	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
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
	public void setTags(LinkedList<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the date
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

}
