package Account;

import java.util.ArrayList;
import java.util.Comparator;

class Course implements java.io.Serializable{
	private String courseName, courseID, sectionID, instructorName, courseLoc, maxNum, currentNum;
	private ArrayList<String[]> courseStudents = new ArrayList<String[]>();
	
	//COURSE CONSTRUCTORS
	Course() {
	}
	
	Course(String courseName, String courseID, String sectionID, String instructorName, String courseLoc, String maxNum, String currentNum, ArrayList<String[]> students) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.setInstructorName(instructorName);
		this.setSectionID(sectionID);
		this.setCourseLoc(courseLoc);
		this.setMaxNum(maxNum);
		this.setCurrentNum(currentNum);
		this.setStudents(students);
	}
	
	
	//GETTERS AND SETTERS
	
	/**
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName set course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the course ID
	 */
	public String getCourseID() {
		return courseID;
	}
	/**
	 * @param courseID set course ID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	/**
	 * @return the instructor's name
	 */
	
	public String getInstructorName() {
		return instructorName;
	}
	/**
	 * @param instructorName new instructor's name
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	/**
	 * @return the course location
	 */
	public String getCourseLoc() {
		return courseLoc;
	}

	/**
	 * @param courseLoc new course location
	 */
	public void setCourseLoc(String courseLoc) {
		this.courseLoc = courseLoc;
	}

	/**
	 * @return the section ID
	 */
	public String getSectionID() {
		return sectionID;
	}

	/**
	 * @param sectionID new sectionID
	 */
	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}

	/**
	 * @return the max number of students
	 */
	public String getMaxNum() {
		return maxNum;
	}

	/**
	 * @param maxNum2 sets a new max number
	 */
	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}

	/**
	 * @return the current number of students
	 */
	public String getCurrentNum() {
		return currentNum;
	}

	/**
	 * @param currentNum sets the number of students
	 */
	public void setCurrentNum(String currentNum) {
		this.currentNum = currentNum;
	}

	/**
	 * @return a list of the student names
	 */
	public ArrayList<String[]> getStudents() {
		return courseStudents;
	}

	/**
	 * @param studentNames edits to the student names
	 */
	public void setStudents(ArrayList<String[]> students) {
		this.courseStudents = students;
	}

	
	//override of tostring
	@Override
	public String toString() {
//		String readable = printStudents();
		String printable = String.format(
		"The Course Name: %1$s\nCourse ID: %2$s\nSection ID: %3$s"
		+ "\nInstructor's Name: %4$s\nLocation: %5$s\nMax number of students: %6$s"
		+ "\nCurrent number of students: %7$s\nStudent Names: " + printStudents() + "\nStudent IDs: " + printStudentID() + "\n",
		this.courseName, this.courseID, this.sectionID, this.instructorName, 
		this.courseLoc, this.maxNum, this.currentNum
		);
		return printable;
	}
	
	//to string but student view (some information is left out)
	public String forStudents() {
		String printable = String.format(
		"The Course Name: %1$s\nCourse ID: %2$s\nSection ID: %3$s"
		+ "\nInstructor's Name: %4$s\nLocation: %5$s\nMax number of students: %6$s"
		+ "\nCurrent number of students: %7$s\n\n",
		this.courseName, this.courseID, this.sectionID, this.instructorName, 
		this.courseLoc, this.maxNum, this.currentNum
		);
		return printable;
	}
	
	//return a string of the list of students
	public String printStudents() {
		String readable = "";
		int counter = 0;
			if (courseStudents.size() > 0) {
			for(String[] student : courseStudents){
				readable += student[0] + " " + student[1] + " " + student[2] + ", ";
				counter += 1;
				if ((counter == 2) || ((counter > 3) && (counter % 3 == 0))) {
					readable += "\n";
				}
			}
		}
		return readable;
	}
	
	//return a string of the student ids
	public String printStudentID() {
		String readable = "";
		int counter = 0;
		for(String[] student : courseStudents){
			readable += student[2] + ", ";
			counter += 1;
			if (counter % 10 == 0) {
				readable += "\n";
			}
		}
		return readable;
	}
	
}
//compares the current numbers of different courses (to sort by)
class sortByCurrentNum implements Comparator<Course>{
	public int compare(Course a, Course b){ 
        return Integer.parseInt(a.getCurrentNum()) - Integer.parseInt(b.getCurrentNum()); 
    }
}
