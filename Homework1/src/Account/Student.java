package Account;


class Student extends User implements studentControls{
	private String studentID;
	
	
	//STUDENT CONSTRUCTORS
	
	Student(){
	}
	
	Student(String username, String password,String studentID, String firstName, String lastName) {
		this.username = username;
		this.password = password; 
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * @return the ID
	 */
	public String getStudentID() {
		return studentID;
	}

	/**
	 * @param password sets the password
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
		
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}
	
	//METHODS
	
	//views all courses (restricted student view)
	@Override
	public void viewAllCourses() {
		School.displayAllCourses();
	}
	
	//views only the open courses
	@Override
	public void viewNotFull() {
		School.displayNotFull();
	}
	
	//register for a course
	@Override
	public void registerCourse(Student student) {
		String[] name = {student.getFirstName(), student.getLastName()};
		School.registerForCourse(name);
	}

	//withdraw from a course
	@Override
	public void withdrawCourse(Student student) {
		String[] name = {student.getFirstName(), student.getLastName()};
		School.withdrawCourse(name);
	}
	
	//view registered courses
	@Override
	public void viewRegistered(Student student) {
		String[] name = {student.getFirstName(), student.getLastName()};
		School.displayStudentClasses(name);
	}
	
	//override of tostring
	@Override
	public String toString() {
		String printable = String.format(
		"First name: %1$s, Last name: %2$s, studentID: %3$s\n",
		this.firstName, this.lastName, this.studentID
		);
		return printable;
	}

}
