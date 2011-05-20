/**
 * 
 */
package crossfitPlusPlus;
import java.util.*;

/**
 * @author Jackson Callaway
 *
 */
public class Biometric {
	
    //FIELDS
    private double weight;
    private double bFP;		//Body Fat Percentage
    private int heartRate;
    private double bMI;
    private LinkedList<String> tags;
    private GregorianCalendar date;

    
    //CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public Biometric() {
		weight = 0.0;
		bFP = 0.0;
		heartRate = 0;
		bMI = 0.0;
		date = new GregorianCalendar();
		tags = new LinkedList<String>();
	}

	//METHODS
	/**
	 * @return whether or not autoTag was successful
	 */
	public boolean autoTag(){
		
		return true;
	}
	
	/**
	 * @param weight the user's weight
	 * @param height the user's height
	 * @return the calculate BMI
	 */
	public double calculateBMI(double weight, double height){
		return weight/Math.pow(height, 2.0);
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		if(weight > 0.0)
		{
			this.weight = weight;
		}
	}

	/**
	 * @return the bFP
	 */
	public double getbFP() {
		return bFP;
	}

	/**
	 * @param bFP the bFP to set
	 */
	public void setbFP(double bFP) {
		if(bFP > 0.0)
		{
			this.bFP = bFP;
		}
	}

	/**
	 * @return the heartRate
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(int heartRate) {
		if(heartRate > 0)
		{
			this.heartRate = heartRate;
		}
	}

	/**
	 * @return the bMI
	 */
	public double getbMI() {
		return bMI;
	}

	/**
	 * @param bMI the bMI to set
	 */
	public void setbMI(double bMI) {
		{
			if(bMI > 0)
			{
				this.bMI = bMI;
			}
		}
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

	/**
	 * @return the tags
	 */
	public String[] getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tagstring) {
		String[] t = tagstring.split(",");
		for(int i = 0;i < t.size();i++)
		{
			tags.add(t[i].trim());
		}
	}
	
	/**
	 * Method to convert all of Biometric data info to string for text dump
	 * @return - String of Biometric info
	 */
	public String toString()
	{
		String BioInfo;
		BioInfo += "Date: " + date + "\n";
		BioInfo += "Weight " + weight + "\n";
		BioInfo += "Body Fat Percentage " + bFP + "\n";
		BioInfo += "Heart Rate " + heatrate + "\n";
		BioInfo += "Body Mass Index " + bMI + + "\n";
		BioInfo += "\n" + "_________________________________";
	}
	
	
	
	
	
	
}
