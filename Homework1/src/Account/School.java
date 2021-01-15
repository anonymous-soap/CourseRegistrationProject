package Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class School {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Course> listCourses = new ArrayList<Course>();
	static ArrayList<Student> allStudents = new ArrayList<Student>();
	
	//constructor
	School(){
	}
	
//METHODS
	
	//registers new students
	public static void registerNew(Student tempStudent) {
		allStudents.add(tempStudent);
//		System.out.println(tempStudent.getStudentID() + " added to system.");
	}
	
	//returns a list of only the full courses
	public static ArrayList<Course> retrieveAllFull() {
		ArrayList<Course> tempList = new ArrayList<Course>();
		for (Course course: listCourses) {
			if (course.getCurrentNum().equals(course.getMaxNum())) tempList.add(course);
		}
		return tempList;
	}

	//verifies a student login
	public static Student checkStudent(String username, String password) {
		for (Student student : allStudents) {
			if (student.getUsername().equals(username) && (student.getPassword().equals(password))) return student;
		}
		return null;
	}
	
//METHODS FOR COURSE EDITS
	
	//adds a course 
	public static void addCourse(Course newCourse) {
		boolean exists = false;
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(newCourse.getCourseID())) && 
				(a.getSectionID().equals(newCourse.getSectionID()))) {
				System.out.println("\nCourse already exists!\n");
				exists = true;
				break;
			}
		}
		if (!exists) {
			listCourses.add(newCourse);
//			System.out.println(newCourse.getCourseID() + " added to system.");
		}
	}
	
	//deletes a course
	public static void deleteCourse(String courseID) {
		boolean exists = false;
		System.out.println();
		for (int i = 0; i < listCourses.size(); i++) {
			Course course = listCourses.get(i);
			if (course.getCourseID().equals(courseID)) {
				listCourses.remove(course);
				i -=1;
				System.out.println("Removed course: " + course.getCourseName() + ", section: " + course.getSectionID());
				exists = true;
			} 
		}
		if (!exists) {
			System.out.println("\nCourse not found.\n");
		}
		System.out.println();
		
	}
	
	//changes the section number
	public static void changeSection(String course, String current, String replace) {
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(current))) {
				if (!(a.getSectionID().equals(replace))) {
					a.setSectionID(replace);
					System.out.println("replaced section ID: " + current);
				} else System.out.println("Already the section ID!");
				System.out.println();
				break;
			}
		}
	}
	
	//changes the instructor
	public static void changeInstructor(String course, String current, String replace) {
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(current))) {
				if (!(a.getInstructorName().equals(replace))) {
					a.setInstructorName(replace);
					System.out.println("replaced instructor for section: " + current);
				} else System.out.println("Already the instructor!");
				System.out.println();
				break;
			}
		}
	}

	//changes the location
	public static void changeLocation(String course, String current, String replace) {
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(current))){
				if (!(a.getCourseLoc().equals(replace))) {
				a.setCourseLoc(replace);
				System.out.println("replaced location for section: " + current);
				} else System.out.println("Already the location!");
				System.out.println();
				break;
			}
		}
		
	}

	//changes the max number of students
	public static void changeMax(String course, String current, String replace) {
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(current))) {
				if (!(a.getMaxNum().equals(replace))) {
					int temp = Integer.parseInt(a.getMaxNum());
					int temp1 = Integer.parseInt(replace);
					int temp2 = Integer.parseInt(a.getCurrentNum());
					if (temp2 > temp1) {
						int difference = temp - temp1; 
						int count = 0;
						while (count != difference){
							System.out.println("You must remove" + difference + "students in order for the change to apply.");
							String[] name = chooseStudentDelete(a);
							deleteStudent(course, current, name);
							difference -= 1;
						}
					}
					a.setMaxNum(replace);
				} else System.out.println("Already the max!");
				System.out.println("replaced max capacity for section: " + current);
				System.out.println();
				break;
			}
		}
	}
	
	//changes the current number of students by either deleting or adding a student
	public static void changeCurrent(String course, String current, String replace)  {
		for (Course a: listCourses) {
			if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(current))) {
				if (!(a.getCurrentNum().equals(replace))) {
					int temp = Integer.parseInt(a.getCurrentNum());
					int temp1 = Integer.parseInt(a.getMaxNum());
					int temp2 = Integer.parseInt(replace);
					if (temp1 >= temp2){
						if (temp > temp2){
							Course tempCourse = a;
							int difference = temp - temp2; 
							int count = 0;
							while (count != difference){
								System.out.println("You must remove " + difference + " students in order for the change to apply.");
								System.out.println("This is the current list of students:");
								displayStudents(course, current);
								String[] name = chooseStudentDelete(tempCourse);
								while (name[0] == null) {
									System.out.println("Not valid name. Try again.");
									name = chooseStudentDelete(tempCourse);
								}
								deleteStudent(course, current, name);
								difference -= 1;
							}
						}
						if ((temp < temp2)){
							Course tempCourse = a;
							int difference = temp2 - temp; 
							int count = 0;
							while (count != difference){
								System.out.println("You must add " + difference + " students in order for the change to apply.");
								System.out.println("This is the current list of students:");
								displayStudents(course, current);
								String[] name = chooseStudentAdd(tempCourse);
								while (name[0] == null) {
									System.out.println("Not valid name. Try again.");
									name = chooseStudentAdd(tempCourse);
								}
								addStudent(course, current, name);
								difference -= 1;
							}
						}
					} else System.out.println("New number is too large!");
	//				course.setCurrentNum(replace);
				} else System.out.println("Already the current number!");
				System.out.println();
				break;
			}
		}
	}

	//chooses a student to add or delete
		public static String[] chooseStudentDelete(Course current) {
			String[] name = new String[2];
			String tempFirst = "";
			String tempLast = "";
			try {
				System.out.println("Student first name: ");
				tempFirst = input.readLine();
				System.out.println("Student last name: ");
				tempLast = input.readLine();
			} catch (IOException e) {
				System.out.println("Not a valid student name.");
			}
			for (String[] student : current.getStudents()) {
				if ((tempFirst.equals(student[0])) && (tempLast.equals(student[1]))){
					name[0] = tempFirst;
					name[1] = tempLast;
					break;
				}
			}
			return name;
		}
		
	//chooses a student to add or delete
	public static String[] chooseStudentAdd(Course current) {
		String[] name = new String[2];
		String tempFirst = "";
		String tempLast = "";
		try {
			System.out.println("Student first name: ");
			tempFirst = input.readLine();
			System.out.println("Student last name: ");
			tempLast = input.readLine();
		} catch (IOException e) {
			System.out.println("Not a valid student name.");
		}
		for (Student student : allStudents) {
			if ((tempFirst.equals(student.getFirstName())) && (tempLast.equals(student.getLastName()))){
				name[0] = tempFirst;
				name[1] = tempLast;
				break;
			}
		}
		return name;
	}
	
	//either adds or deletes the chosen student
	public static void editStudent(String course, String current, String[] name) {
		try {
			int selection = -1;
			System.out.print("1. Delete this student\n"
					+ "2. Add this student\n"
					+ "0. Exit\n"
					+ "Enter the number corresponding to the action you want to take: ");
			selection = Integer.parseInt(input.readLine());
			if (selection == 1) deleteStudent(course, current, name);
			else if (selection == 2) addStudent(course, current, name);
			else if (selection != 0) {
				System.out.println("Not a valid selection. Please try again.");
				System.out.println();
				editStudent(course, current, name);
			}
			System.out.println();	
		} catch (NumberFormatException | IOException e) {
			System.out.println("Error in selection.");
			e.printStackTrace();
		}
	}
	
	//deletes a student
	public static void deleteStudent(String current, String section, String[] name) {
		for (Course course: listCourses) {
			if ((course.getCourseID().equals(current)) && (course.getSectionID().equals(section))) {
				ArrayList<String[]> studentList = course.getStudents();
				for (String[] student : studentList) {
					if ((name[0].equals(student[0])) && (name[1].equals(student[1]))){
						studentList.remove(student);
						course.setStudents(studentList);
						int temp = Integer.parseInt(course.getCurrentNum()) - 1;
						String temp1 = Integer.toString(temp);
						course.setCurrentNum(temp1);
						break;
					}
				}
				System.out.println("Removed " + name[0] +" "+ name[1]+ " from this section: " + current + "\n");
				break;
			}
		} 
	}
	
	//adds a student
	public static void addStudent(String current, String section, String[] name) {
		boolean enrolled = false;
		boolean full = false;
		boolean added = false;
		for (Course course: listCourses) {
			if ((course.getCourseID().equals(current)) && (course.getSectionID().equals(section))){
				if ((Integer.parseInt(course.getMaxNum())) == (Integer.parseInt(course.getCurrentNum()))){
					full = true;
					System.out.println("This course is full.");
				}
				for (String[] student: course.getStudents()) {
					if((student[0].equals(name[0])) && (student[1].equals(name[1]))) {
						System.out.println("Already enrolled in course!");
						enrolled = true;
						break;
					}
				}
				if (!enrolled && !full){
					ArrayList<String[]> studentList = course.getStudents();
					for (Student student : allStudents) {
						if((student.getFirstName().equals(name[0])) && (student.getLastName().equals(name[1])) ) {
							String[] studentInfo = {student.getFirstName(), student.getLastName(), student.getStudentID()};
							studentList.add(studentInfo);
							course.setStudents(studentList);
							System.out.print(course.printStudents());
							int temp = Integer.parseInt(course.getCurrentNum()) + 1;
							String temp1 = Integer.toString(temp);
							course.setCurrentNum(temp1);
							System.out.println("Added a student into this section: " + current);
							added = true;
							break;
						}
					}
				}
				break;
			}
		}
		if (!added) System.out.println("There was a problem!");
	}
	

//METHODS FOR DISPLAYING INFORMATION
	
	//displays a course's info
	public static void displayInfo(String courseID) {
		for (Course course: listCourses) {
			if (course.getCourseID().equals(courseID)) {
				System.out.print(course);
				System.out.println();
			}
		}
	}

	//displays all courses + details
	public static void displayAllCourses(String show) {
		for (Course course: listCourses) {
			System.out.print(course);
			System.out.println();
		}
	}

	//prints the full courses
	public static void displayAllFull() {
		for (Course course: listCourses) {
			if (course.getCurrentNum().equals(course.getMaxNum())) {
				System.out.print(course);
				System.out.println();
			}
		}
	}

	//displays students in a course
	public static void displayStudents(String courseID, String sectionID) {
		System.out.println("\nStudents enrolled: ");
		for (Course course: listCourses) {
			if ((course.getCourseID().equals(courseID)) && (course.getSectionID().equals(sectionID))) {
				System.out.println(course.printStudents());
				System.out.println();
				break;
			}
		}
	}

	//displays courses a student is enrolled in
	public static void displayStudentClasses(String[] name) {
		System.out.println("\nCourses enrolled: ");
		for (Course course: listCourses) {
			ArrayList<String[]> studentList = course.getStudents();
			for (String[] student : studentList) {
				if ((name[0].equals(student[0])) && (name[1].equals(student[1]))){
					System.out.println(course.getCourseName());
					break;
				}
			}
		}
		System.out.println();
	}

	//sorts courses by number of students
	public static void sortCourses() {
		try {
			int selection = 0;
			System.out.println("Sorting by current number of students:\n"
					+ "1.lowest to highest\n"
					+ "2.highest to lowest");
			selection = Integer.parseInt(input.readLine());
			if (selection == 1) Collections.sort(listCourses, new sortByCurrentNum());
			else if (selection == 2) Collections.sort(listCourses, new sortByCurrentNum().reversed());
			else if (selection != 0) {
				System.out.println("Not a valid selection. Please try again.");
				System.out.println();
				sortCourses();
			}
			for (Course course : listCourses) {
				System.out.println(course);
				System.out.println();
			}
		} catch (NumberFormatException e) {
			System.out.println("Error in selection.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to sort courses.");
		}
	}
	
	
//STUDENT ACCESS
	
	//shows a student certain course info
	public static void displayAllCourses() {
		for (Course course: listCourses) {
			System.out.print(course.forStudents());
		}
	}
	
	//shows available classes 
	public static void displayNotFull() {
		for (Course course: listCourses) {
			if (course.getCurrentNum().equals(course.getMaxNum())) continue;
				else System.out.print(course.forStudents());
		}
	}

	//allows a student to enroll in a course
	public static void registerForCourse(String[] name) {
		try {
			boolean added = false;
			System.out.println("What is the course code?");
			String course = input.readLine();
			System.out.println("Enter in the section ID.");
			String section = input.readLine();
			for (Course a: listCourses) {
				if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(section))) {
					int temp = Integer.parseInt(a.getMaxNum());
					int temp1 = Integer.parseInt(a.getCurrentNum());
					if(temp1 < temp) {
						addStudent(course, section, name);
						added = true;
					}
					break;
				}
			}
			if (!added) System.out.println("Could not enroll in course.");
			else System.out.println("Enrolled!");
		} catch (IOException e) {
			System.out.println("Not a valid course/section ID.");
		}
	}

	//allows a student to withdraw from a class
	public static void withdrawCourse(String[] name) {
		try {
			boolean withdraw = false;
			System.out.println("What is the course code?");
			String course = input.readLine();
			System.out.println("Enter in the section ID.");
			String section = input.readLine();
			for (Course a: listCourses) {
				if ((a.getCourseID().equals(course)) && (a.getSectionID().equals(section))) {
					deleteStudent(course, section, name);
					withdraw = true;
					break;
				}
			}
			if (withdraw) System.out.println("Withdrew successfully.");
			else System.out.println("You can never leave.");
		} catch (IOException e) {
			System.out.println("Not a valid course/section ID.");
		}
	}
	
	
}
