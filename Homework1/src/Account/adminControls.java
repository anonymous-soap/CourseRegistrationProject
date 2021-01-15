package Account;

interface adminControls {
	//Course Management
	void createCourse();
	void deleteCourse();
	void editCourse();
	void editSectionID(String course, String current);
	void editInstructor(String course, String current);
	void editLocation(String course, String current);
	void editMax(String course, String current);
	void editCurrentNumber(String course, String current);
	void editStudents(String course, String current);
	void displayCourseInfo();
	void registerStudent();
	
	//Reports
	void ViewAllFull();
	void viewStudents();
	void viewStudentCourses();
	void sortCourses();
	void submitStudents();
	void submitFullCourses();
	void submitCourses();
	void submitStudentsCSV();
	void submitCoursesCSV();
}
