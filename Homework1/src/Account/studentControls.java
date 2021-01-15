package Account;

interface studentControls {
	//Course Management
	String getStudentID();
	void setStudentID(String studentID);
	void viewAllCourses();
	void viewNotFull();
	void registerCourse(Student student);
	void withdrawCourse(Student student);
	void viewRegistered(Student student);
}
