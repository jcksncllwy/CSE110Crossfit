/**
 * Standard Deviation (STDev)
 * DATE
 * 
 * This class is designed to create a User object, which holds all info
 * pertaining to the user's account.
 */
package com.cs110.stdev.crossfit.backend;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class User implements Serializable {

	// FIELDS
	private String firstName;
	private String lastName;
	private String birthday;
	private int age;
	private String username;
	private String password;
	private String secretQ;
	private String secretA;
	private Log myLog;

	// CONSTRUCTORS
	/**
	 * Default Constructor
	 */
	public User() {
		firstName = "";
		lastName = "";
		birthday = "";
		age = 0;
		username = "";
		password = "";
		secretQ = "";
		secretA = "";
		myLog = new Log();
	}

	// METHODS
	/**
	 * Method to check if username is valid
	 * 
	 * @param - username to be validated
	 * @return - true if valid, false otherwise
	 */
	public boolean validateUsername(String username) {
		// length of 3-15, a-z,0-9,_ characters only
		String regex = "^[a-z0-9_-]{3,15}$";
		return username.matches(regex);
	}

	/**
	 * Method to check if password is valid
	 * 
	 * @param - password to be validated
	 * @return - true if valid, false otherwise
	 */
	public boolean validatePassword(String password) {
		// 6-15 length, a-z,A-Z,at least one number
		String passRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15})";
		return password.matches(passRegex);
	}

	/**
	 * Method to get user's first name
	 * 
	 * @return - user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to set user's first name
	 * 
	 * @param - new first name to be set
	 */
	public void setFirstName(String newFirst) {
		this.firstName = newFirst;
	}

	/**
	 * Method to get user's last name
	 * 
	 * @return - user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to set user's last name
	 * 
	 * @param - new last name to be set
	 */
	public void setLastName(String newLast) {
		this.lastName = newLast;
	}

	/**
	 * Method to get user's birthday
	 * 
	 * @return - user's birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Method to print user's birthday
	 */
	public String printBirthday() {
		String birthdayPrint = "";

		if (!birthday.equals("")) {
			birthdayPrint = birthday.substring(0, 2) + "/"
					+ birthday.substring(2, 4) + "/" + birthday.substring(4, 8);
		}
		return birthdayPrint;
	}

	/**
	 * Method to set user's birthday
	 * 
	 * @param - new birthday to be set
	 */
	public void setBirthday(String newBirthday) {
		this.birthday = newBirthday;
	}

	/**
	 * Method to get user's age
	 * 
	 * @return - user's age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Method to set user's age
	 * 
	 * @param - new age to be set
	 * @return - true if valid age set, false otherwise
	 */
	public boolean setAge() {
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int bdayYear = Integer.parseInt(getBirthday().substring(4, 8));
		int month = calendar.get(Calendar.MONTH);
		int bdayMonth = Integer.parseInt(getBirthday().substring(0, 2));
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int bdayDay = Integer.parseInt(getBirthday().substring(2, 4));

		if (month == bdayMonth) {
			if (day < bdayDay)
				this.age = year - bdayYear - 1;
			else
				this.age = year - bdayYear;
		} else if (month < bdayMonth)
			this.age = year - bdayYear - 1;
		else
			this.age = year - bdayYear;

		return true;
	}

	/**
	 * Method to get usernams
	 * 
	 * @return - username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method to set username
	 * 
	 * @param - new username to be set
	 * @return - true if valid username set, false otherwise
	 */
	public boolean setUsername(String newUserName) {
		if (validateUsername(newUserName)) {
			this.username = newUserName;
			return true;
		}
		return false;
	}

	/**
	 * Method to get user's password
	 * 
	 * @return - user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set user's password
	 * 
	 * @param - new password to be set
	 * @return - true if valid password set, false otherwise
	 */
	public boolean setPassword(String newPass) {
		if (validatePassword(newPass)) {
			this.password = newPass;
			return true;
		}
		return false;
	}

	/**
	 * Method to get user's secret question
	 * 
	 * @return - user's secret question
	 */
	public String getSecretQ() {
		return secretQ;
	}

	/**
	 * Method to set user's secret question
	 * 
	 * @param - new secret question to be set
	 */
	public void setSecretQ(String newSecretQ) {
		this.secretQ = newSecretQ;
	}

	/**
	 * Method to get user's secret answer to their secret question
	 * 
	 * @return - user's secret answer
	 */
	public String getSecretA() {
		return secretA;
	}

	/**
	 * Method to set user's secret answer
	 * 
	 * @param - new secret answer to be set
	 */
	public void setSecretA(String newSecretA) {
		this.secretA = newSecretA;
	}

	/**
	 * Method to get user's log
	 * 
	 * @return - user's log
	 */
	public Log getMyLog() {
		return myLog;
	}

	/**
	 * Method to set user's log
	 * 
	 * @param - new log to be set
	 */
	public void setMyLog(Log newLog) {
		this.myLog = newLog;
	}
}
