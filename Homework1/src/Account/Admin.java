package Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Admin extends User implements adminControls{
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	//ADMIN CONSTRUCTORS
	Admin(){}
	
	Admin(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName; 
		this.lastName = lastName;
	}
	
	//SETTERS
	@Override
	public void setUsername(String a) {
		username = "Admin";
	}

	@Override
	public void setPassword(String a) {
		password = "Admin001";
		
	}
	
//METHODS
	
	//registers a student
	@Override
	public void registerStudent() {
		try {
			System.out.println("Student ID:");
			String tempID;
			tempID = input.readLine();
			System.out.println("First name:");
			String tempFirst = input.readLine();
			System.out.println("Last name:");
			String tempLast = input.readLine();
			System.out.println("Username:");
			String tempUser = input.readLine();
			System.out.println("Password:");
			String tempPass = input.readLine();
			Student tempStudent = new Student(tempUser, tempPass, tempID, tempFirst,tempLast);
			School.registerNew(tempStudent);
		} catch (IOException e) {
			System.out.println("Entry not valid. Please try again.");
			e.printStackTrace();
		}
	}
	
	
//METHODS FOR COURSE EDITS
	
	//creates a course
	@Override
	public void createCourse() {
		Course newCourse = new Course();
		try {
			System.out.println("What is the course name?");
			newCourse.setCourseName(input.readLine());
			System.out.println("What is the courseID?");
			newCourse.setCourseID(input.readLine());
			System.out.println("What is the instructor's name?");
			newCourse.setInstructorName(input.readLine());
			System.out.println("What is the section ID?");
			newCourse.setSectionID(input.readLine());
			System.out.println("Where is the course located?");
			newCourse.setCourseLoc(input.readLine());
			System.out.println("What is the maximum number of students?");
			newCourse.setMaxNum(input.readLine());
			Integer.parseInt(newCourse.getMaxNum());
			newCourse.setCurrentNum("0");
//			newCourse.setStudents(null);
			School.addCourse(newCourse);
		} catch (IOException e) {
			System.out.println("Entry not valid. Please try again.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Invalid max number.");
			System.out.println();
		}
//		courses.listCourses.add(newCourse);
		//the prob here is idk how to create courses without naming them arbitrary var names; rethink game plan
	}
	
	//deletes a course
	@Override
	public void deleteCourse() {
		String tempCourseID = "";
		try {
			System.out.println("Enter in the course ID for the course you want to delete.");
			tempCourseID = input.readLine();
			School.deleteCourse(tempCourseID);
		} catch (IOException e) {
			System.out.println("Not a valid course code. Please try again.");
			e.printStackTrace();
		}
	}
	
	//prompts edits to a course
	@Override
	public void editCourse() {
		try {
			int selection = -1;
			boolean exists = false;
			System.out.println("Enter the course ID of the course you would like to edit.");
			String currCourseID = input.readLine();
			System.out.println("Enter in the section ID.");
			String currSectionID = input.readLine();
			for (Course a : School.listCourses) {
				if ((a.getCourseID().equals(currCourseID)) && (a.getSectionID().equals(currSectionID))){
					exists = true;
					break;
				}
			}
			if (!exists) {
				System.out.println("Not a valid course/section ID.");
				System.out.println();
			} else 
			{
				System.out.print(
				"What would you like to edit? \n"
				+ "1. Edit section ID\n"
				+ "2. Edit the instructor's name\n"
				+ "3. Edit course location\n"
				+ "4. Edit max number of students\n"
				+ "5. Edit current number of students\n"
				+ "6. Edit student list\n"
				+ "0. Exit\n"
				+ "Please select the number corresponding to the options: "
				);
				selection = Integer.parseInt(input.readLine());
				System.out.println();
				if (selection == 1) editSectionID(currCourseID, currSectionID);
				else if (selection == 2) editInstructor(currCourseID, currSectionID);
				else if (selection == 3) editLocation(currCourseID, currSectionID);
				else if (selection == 4) editMax(currCourseID, currSectionID);
				else if (selection == 5) editCurrentNumber(currCourseID, currSectionID);
				else if (selection == 6) editStudents(currCourseID, currSectionID);
				else if (selection != 0) {
					System.out.println("Not a valid selection. Please try again.");
					System.out.println();
					editCourse();
				}
		}
		} catch (IOException | NumberFormatException e) {
				System.out.println("Error in selection.");
		}
	}
	
	//edits a section ID
	@Override
	public void editSectionID(String course, String current) {
		try {
			System.out.println("What would you like to change the section to?");
			String tempSectionID = input.readLine();
			School.changeSection(course, current, tempSectionID);
		} catch (IOException e) {
			System.out.println("Invalid section number.");
			System.out.println();
		}
		
	}
	
	//edits the instructor 
	@Override
	public void editInstructor(String course, String current) {
		try {
			System.out.println("Who is the new instructor?");
			String tempInstructor = input.readLine();
			School.changeInstructor(course, current, tempInstructor);
		} catch (IOException e) {
			System.out.println("Invalid instructor name.");
			System.out.println();
		}
	}
	
	//edits the location
	@Override
	public void editLocation(String course, String current) {
		try {
			System.out.println("Where is the new location?");
			String tempLocation = input.readLine();
			School.changeLocation(course, current, tempLocation);
		} catch (IOException e) {
			System.out.println("Invalid location.");
			System.out.println();
		}
	}
	
	//edits the max number of students
	@Override
	public void editMax(String course, String current) {
		try {
			System.out.println("What is the new max capacity?");
			String tempMax = input.readLine();
			Integer.parseInt(tempMax);
			School.changeMax(course, current, tempMax);
		} catch (NumberFormatException | IOException e) {
			System.out.println("Invalid max number.");
			System.out.println();
		}
	}
	
	//edits the current number of students
	@Override
	public void editCurrentNumber(String course, String current) {
		try {
			System.out.println("What is the new current number of students?");
			String tempCurrent = input.readLine();
			Integer.parseInt(tempCurrent);
			School.changeCurrent(course, current, tempCurrent);
		} catch (NumberFormatException | IOException e) {
			System.out.println("Invalid current number.");
			System.out.println();
		}
	}

	//edits the list of students
	@Override
	public void editStudents(String course, String current) {
		try {
			System.out.println("Student first name: ");
			String tempFirst = input.readLine();
			System.out.println("Student last name: ");
			String tempLast = input.readLine();
			String[] name = {tempFirst, tempLast};
			School.editStudent(course, current, name);
			submitCourses();
		} catch (IOException e) {
			System.out.println("Invalid student name.");
			System.out.println();
		}
	}
	
	
//METHODS FOR VIEWING INFORMATION
	
	//display a specific course info using courseID
	@Override
	public void displayCourseInfo() {
		try {
		System.out.println("What is the course ID of the course you want to see?");
		String courseID = input.readLine();
		System.out.println();
		School.displayInfo(courseID);
		} catch (IOException e) {
			System.out.println("Invalid course ID.");
			System.out.println();
		}
	}
	
	//view all courses + details
	@Override
	public void viewAllCourses() {
		String show = "show";
		School.displayAllCourses(show);
	}
	
	//view full classes
	@Override
	public void ViewAllFull() {
		School.displayAllFull();
	}
	
	//shows the students in a section
	@Override
	public void viewStudents() {
		try {
			boolean exists = false;
			System.out.println("What is the course ID?");
			String courseID = input.readLine();
			System.out.println("What is the section ID?");
			String sectionID = input.readLine();
			for (Course a : School.listCourses) {
				if ((a.getCourseID().equals(courseID)) && (a.getSectionID().equals(sectionID))){
					exists = true;
					break;
				}
			}
			if (exists) School.displayStudents(courseID, sectionID);
			else System.out.println("\nNot a valid course/section ID.\n");
		} catch (IOException e) {
			System.out.println("Invalid course/section ID.");
			System.out.println();
		}
		
	}
	
	//view a specific student's courses
	@Override
	public void viewStudentCourses() {
		try {
			boolean exists = false;
			System.out.println("Student first name: ");
			String tempFirst = input.readLine();
			System.out.println("Student last name: ");
			String tempLast = input.readLine();
			String[] name = {tempFirst, tempLast};
			for (Student a : School.allStudents) {
				if ((a.getFirstName().equals(tempFirst)) && 
						(a.getLastName().equals(tempLast))){
					exists = true;
					break;
				}
			} 
			if (exists) School.displayStudentClasses(name);
			else System.out.println("\nInvalid student name.\n");
		} catch (IOException e) {
			System.out.println("Invalid student name.");
			System.out.println();
		}
	}

	//sort courses based on the number of students registered
	@Override
	public void sortCourses() {
		School.sortCourses();
	}
	

	//WRITE TO FILE METHODS
	

	//serialization to store courses
	@Override
	public void submitCourses() {
		ArrayList <Course> data = School.listCourses;
		try{
//			file.createNewFile();
			submitCoursesCSV();
	        FileOutputStream fos = new FileOutputStream("MyUniversityCourses");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
			System.out.println("Courses info fully updated.");
		}catch(IOException ioe){
			System.out.println("Courses info unable to be updated.");
			ioe.printStackTrace();
	    }
	}
	
	//serialization to store students
	@Override
	public void submitStudents() {
		ArrayList <Student> data = School.allStudents;
//		file.createNewFile();
		try{
			submitStudentsCSV();
			FileOutputStream fos = new FileOutputStream("MyUniversityStudents");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			fos.close();
			oos.close();
		    System.out.println("Student info fully updated.");
		}catch(IOException ioe){ 
			System.out.println("Student info unable to be updated.");
			ioe.printStackTrace();
	    }

	}
	
	//writes to a file the list of full classes
	@Override
	public void submitFullCourses() {
		try {
			ArrayList <Course> data = School.retrieveAllFull();
			File file = new File("MyUniversityFullCourses.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			file.createNewFile();
			
	        writer.append(String.join(",", "Course_Name","Course_ID", "Maximum_Students", "Current_Students",
	        		"List_Of_Names", "Course_Instructor","Course_Section_Number", "Course_Location"));
	        writer.append("\n");
	        
	        if (data.size() > 0) {
				for (Course course : data) {
					ArrayList<String[]> students = course.getStudents();
					writer.append(String.join(",",course.getCourseName(), course.getCourseID(), course.getMaxNum(), course.getCurrentNum()));
					writer.append(",");
					if (students.size() < 1) {
						writer.append("NULL");
						writer.append(",");
					} else {
						for (String[] studentInfo : students) {
							writer.append(String.join(" ", studentInfo[0],studentInfo[1], studentInfo[2]));
							writer.append(";");
						}
						writer.append(",");
					}
				    writer.append(String.join(",", course.getInstructorName(), course.getSectionID(), course.getCourseLoc()));
				    writer.append("\n");
				}
	        }
			writer.flush();
		    writer.close();
			System.out.println("Full courses info fully updated to csv.\n");
		} catch (IOException ioe) {
			System.out.println("Unable to save full courses to csv file.");
			ioe.printStackTrace();
		}
	}
	
	//writes to a file the list of students
	@Override
	public void submitStudentsCSV() {
		try {
			ArrayList <Student> data = School.allStudents;
			File file = new File("MyUniversityStudents.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			file.createNewFile();
	        writer.append(String.join(",", "Username","Password", "Student ID", "First_Name", "Last_Name"));
	        writer.append("\n");
			for (Student student : data) {
				writer.append(String.join(",", student.getUsername(),student.getPassword(),
				student.getStudentID(),student.getFirstName(),student.getLastName()));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			System.out.println("Students info submitted to csv.");
		} catch (IOException ioe) {
			System.out.println("Student info unable to save to csv.");
			ioe.printStackTrace();
		}
	}
	
	//writes to a file the course list
	@Override
	public void submitCoursesCSV(){
		try {
			ArrayList <Course> data = School.listCourses;
			File file = new File("MyUniversityCourses.csv");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			file.createNewFile();
			
	        writer.append(String.join(",", "Course_Name","Course_ID", "Maximum_Students", "Current_Students",
	        		"List_Of_Names", "Course_Instructor","Course_Section_Number", "Course_Location"));
	        writer.append("\n");
	        
	        if (data.size() > 0) {
				for (Course course : data) {
					ArrayList<String[]> students = course.getStudents();
					writer.append(String.join(",",course.getCourseName(), course.getCourseID(), course.getMaxNum(), course.getCurrentNum()));
					writer.append(",");
					if (students.size() < 1) {
						writer.append("NULL");
						writer.append(",");
					} else {
						for (String[] studentInfo : students) {
							writer.append(String.join(" ", studentInfo[0],studentInfo[1], studentInfo[2]));
							writer.append(";");
						}
						writer.append(",");
					}
				    writer.append(String.join(",", course.getInstructorName(), course.getSectionID(), course.getCourseLoc()));
				    writer.append("\n");
				}
	        }
			writer.flush();
		    System.out.println("Courses info fully updated to csv.");
		    writer.close();
		} catch (IOException ioe) {
			System.out.println("Courses info unable to save to csv.");
			ioe.printStackTrace();
		}
	}


}
