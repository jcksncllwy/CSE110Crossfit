/**
 * 
 */
package crossfitPlusPlus;
import java.util.*;

/**
 * @author Jackson Callaway
 *
 */
public class User {

	//FIELDS
	private String firstName;
	private String lastName;
	private double height;
	private double weight;
	private GregorianCalendar birthday;
	private int age;
	private double bFP;		//Body Fat Percentage
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
		birthday = new GregorianCalendar();
		age = 0;
		bFP = 0.0;
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
	 * @param username the username to validate
	 * @return whether or not the username is valid
	 */
	public boolean validateUsername(String username){
		
		return true;
	}
	
	/**
	 * @param password the password to validate
	 * @return whether or not the password is valid
	 */
	public boolean validatePassword(String password){
		
		return true;
	}
	
	/**
	 * 
	 * @param email the email to validate
	 * @return whether or not the email is valid
	 */
	public boolean validateEmail(String email){
		
		return true;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
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
		this.weight = weight;
	}

	/**
	 * @return the birthday
	 */
	public GregorianCalendar getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 * @throws Exception 
	 */
	public void setAge(int age) throws Exception {
		GregorianCalendar now = new GregorianCalendar();
		if(now.get(now.YEAR) - birthday.get(birthday.YEAR) == age){
			this.age = age;
		}
		else{
			throw new Exception("Age does not correspond to current Birthday");
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
		this.bFP = bFP;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 * @throws Exception 
	 */
	public void setEmail(String email) throws Exception {
		if(validateEmail(email)){
			this.email = email;
		}
		else{
			throw new Exception("Invalid Email Address");
		}
		
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 * @throws Exception 
	 */
	public void setUsername(String username) throws Exception {
		if(validateUsername(username)){
			this.username = username;
		}
		else{
			throw new Exception("Invalid Username");
		}
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 * @throws Exception 
	 */
	public void setPassword(String password) throws Exception {
		if(validatePassword(password)){
			this.password = password;
		}
		else{
			throw new Exception("Invalid Password");
		}
	}

	/**
	 * @return the secretQ
	 */
	public String getSecretQ() {
		return secretQ;
	}

	/**
	 * @param secretQ the secretQ to set
	 */
	public void setSecretQ(String secretQ) {
		this.secretQ = secretQ;
	}

	/**
	 * @return the secretA
	 */
	public String getSecretA() {
		return secretA;
	}

	/**
	 * @param secretA the secretA to set
	 */
	public void setSecretA(String secretA) {
		this.secretA = secretA;
	}

	/**
	 * @return the myLog
	 */
	public Log getMyLog() {
		return myLog;
	}

	/**
	 * @param myLog the myLog to set
	 */
	public void setMyLog(Log myLog) {
		this.myLog = myLog;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	

}
