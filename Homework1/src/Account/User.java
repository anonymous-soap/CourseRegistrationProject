package Account;

abstract class User implements java.io.Serializable{
	protected String firstName, lastName, username, password;

	
	//GETTERS AND SETTERS
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username sets the username
	 */
	public abstract void setUsername(String username);

	/**
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName sets the last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName sets the first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password sets the password
	 */
	public abstract void setPassword(String password);
	
	public void viewAllCourses() {
		System.out.println("\nYou must log in first!\n");
	}
	
}
