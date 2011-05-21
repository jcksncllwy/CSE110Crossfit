/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a User object, which holds all info
 * pertaining to the user's account.
 */
package crossfitPlusPlus;

import java.util.*;

public class User {

	//FIELDS
	private String firstName;
	private String lastName;
	private double height;
	private double weight;
	private String birthday;
	private int age;
	private double bodyFat;
	private String email;
	private String username;
	private String password;
	private String secretQ;
	private String secretA;
	private Log myLog;
	private boolean admin;
	
	//CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public User() {
		firstName = "";
		lastName = "";
		height = 0.0;
		weight = 0.0;
		birthday = "";
		age = 0;
		bodyFat = 0.0;
		email = "";
		username = "";
		password = "";
		secretQ = "";
		secretA = "";
		myLog = new Log();
		admin = false;
	}
	
	//METHODS
	/**
	 * Method to check if username is valid
	 * @param - username to be validated
	 * @return - true if valid, false otherwise
	 */
	public boolean validateUsername(String username) {
		//TODO
		return true;
	}
	
	/**
	 * Method to check if password is valid
	 * @param - password to be validated
	 * @return - true if valid, false otherwise
	 */
	public boolean validatePassword(String password) {
		//TODO
		return true;
	}
	
	/**
	 * Method to check if email is valid
	 * @param - email to be validated
	 * @return - true if valid, false otherwise
	 */
	public boolean validateEmail(String email) {
		//TODO
		return true;
	}

	/**
	 * Method to get user's first name
	 * @return - user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to set user's first name
	 * @param - new first name to be set
	 */
	public void setFirstName(String newFirst) {
		this.firstName = newFirst;
	}

	/**
	 * Method to get user's last name
	 * @return - user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to set user's last name
	 * @param - new last name to be set
	 */
	public void setLastName(String newLast) {
		this.lastName = newLast;
	}

	/**
	 * Method to get user's height
	 * @return - user's height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Method to set user's height
	 * @param - new height to be set
	 */
	public void setHeight(double newHeight) {
		this.height = newHeight;
	}

	/**
	 * Method to get user's weight
	 * @return - user's weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Method to set user's weight
	 * @param - new weight to be set
	 */
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}

	/**
	 * Method to get user's birthday
	 * @return - user's birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Method to set user's birthday
	 * @param - new birthday to be set
	 */
	public void setBirthday(String newBirthday) {
		this.birthday = newBirthday;
	}

	/**
	 * Method to get user's age
	 * @return - user's age
	 */
	public int getAge() {
		return age;
	}

	//Calculate age based on birthday - Problem = how to get current year to do so?
	/**
	 * Method to set user's age
	 * @param - new age to be set
	 * @return - true if valid age set, false otherwise
	 */
	public boolean setAge(int newAge) {
		GregorianCalendar now = new GregorianCalendar();
		if(now.get(now.YEAR) - birthday.get(birthday.YEAR) == age){
			this.age = newAge;
			return true;
		}
		return false;
	}
	//---------------------------------------------------------
	
	/**
	 * Method to get user's body fat percentage
	 * @return - user's body fat percentage
	 */
	public double getBodyFat() {
		return bodyFat;
	}

	/**
	 * Method to set user's body fat percentage
	 * @param - new body fat percentage to be set
	 */
	public void setBodyFat(double newBodyFat) {
		this.bodyFat = newBodyFat;
	}

	/**
	 * Method to get user's email
	 * @return - user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to set user's email
	 * @param - new email to be set
	 * @return - true if valid email set, false otherwise
	 */
	public boolean setEmail(String newEmail) {
		if(validateEmail(newEmail)){
			this.email = newEmail;
			return true;
		}
		return false;
	}

	/**
	 * Method to get usernams
	 * @return - username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method to set username
	 * @param - new username to be set
	 * @return - true if valid username set, false otherwise
	 */
	public boolean setUsername(String newUserName) {
		if(validateUsername(newUserName)){
			this.username = newUserName;
			return true;
		}
		return false;
	}

	/**
	 * Method to get user's password
	 * @return - user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set user's password
	 * @param - new password to be set
	 * @return - true if valid password set, false otherwise
	 */
	public boolean setPassword(String newPass) {
		if(validatePassword(newPass)) {
			this.password = newPass;
			return true;
		}
		return false;
	}

	/**
	 * Method to get user's secret question
	 * @return - user's secret question
	 */
	public String getSecretQ() {
		return secretQ;
	}

	/**
	 * Method to set user's secret question
	 * @param - new secret question to be set
	 */
	public void setSecretQ(String newSecretQ) {
		this.secretQ = newSecretQ;
	}

	/**
	 * Method to get user's secret answer to their secret question
	 * @return - user's secret answer
	 */
	public String getSecretA() {
		return secretA;
	}

	/**
	 * Method to set user's secret answer
	 * @param - new secret answer to be set
	 */
	public void setSecretA(String newSecretA) {
		this.secretA = newSecretA;
	}

	/**
	 * Method to get user's log
	 * @return - user's log
	 */
	public Log getMyLog() {
		return myLog;
	}

	/**
	 * Method to set user's log
	 * @param - new log to be set
	 */
	public void setMyLog(Log newLog) {
		this.myLog = newLog;
	}

	/**
	 * Method to check whether user is an administrator
	 * @return - true if administrator, false otherwise
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Method to set user to be an administrator
	 * @param - administrator to be set
	 */
	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}
}
