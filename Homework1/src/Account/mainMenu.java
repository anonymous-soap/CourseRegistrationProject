package Account;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class mainMenu {
	
	static Admin admin = new Admin();
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome!");
		//adds course + student data from csv
		addCourseData();
		addStudentData();
//		admin.submitCourses();
//		updateCourseData();
//		updateStudentData();
//		adminMenu();
		welcomeScreen();
		input.close();
	}
	
	
//MENUS
	
//OPENING MENUS
	
	//welcome menu
	public static void welcomeScreen() {
		try {
			int selection = 0;
			System.out.println("You are an: \n"
					+ "1. Admin\n"
					+ "2. Student\n"
					+ "Please enter the number corresponding to the action: ");
			selection = Integer.parseInt(input.readLine());
			if (selection == 1) {
				if (adminVerification()) {
					System.out.println();
					adminMenu();
				} else {
					System.out.println("You are not the admin.\n");
					welcomeScreen();
				}
			}
			else if (selection == 2) {
				Student student = studentVerification();
				if (student != null) {
					System.out.println();
					studentMenu(student);
				} else {
					System.out.println("You are not a valid user.\n");
					welcomeScreen();
				}
			} else {
				System.out.println("\nWhoopsies! Not a valid selection.\n");
				welcomeScreen();
			}
			System.out.println();
		} catch (NumberFormatException | IOException e) {
			System.out.println("\nWhoopsies! Not a valid selection.\n");
			welcomeScreen();
		}
	}
	
	//checks if admin login is valid
	public static boolean adminVerification() {
		try {
			String username = "";
			String password = "";
			System.out.println("What is the admin username?");
			username = input.readLine();
			System.out.println("What is the admin password?");
			password = input.readLine();
			if ("Admin".equals(username) && ("Admin001".equals(password))) return true;
		} catch (IOException e) {
			System.out.println("There seems to be an error.");
			welcomeScreen();
		}
		return false;
	}
	
	//checks if student login is valid
	public static Student studentVerification() {
		String username = "";
		String password = "";
		try {
			System.out.println("What is your username?");
			username = input.readLine();
			System.out.println("What is your password?");
			password = input.readLine();
		} catch (IOException e) {
			System.out.println("There seems to be an error.");
			welcomeScreen();
		}
		return School.checkStudent(username, password);
	}
		
//ADMIN MENUS 
	
	//admin selection
	public static void adminMenu() {
		try {
			int selection = -1;
			while (selection != 0){
				System.out.print("What would you like to do?\n"
						+ "1. Course Management\n"
						+ "2. Reports\n"
						+ "0. Log out\n"
						+ "Please enter the number corresponding to the action: ");
				selection = Integer.parseInt(input.readLine());
				System.out.println();
				if (selection == 1) managementMenu();
				else if (selection == 2) reportsMenu();
				else if (selection != 0) {
					System.out.println("Not a valid selection. Please try again.\n");
					adminMenu();
				}
			};
			if (selection == 0) {
//				admin.submitFullCourses();
				admin.submitCourses();
				admin.submitStudents();
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("\nNot a valid selection. Please try again.\n");
		}
	}
	
	//management menu
	private static void managementMenu() {
		try {
			int selection = -1;
			while (selection != 0){
			System.out.print("Course Management: \n"
					+ "1. Create a new course\n"
					+ "2. Delete a course\n"
					+ "3. Edit a course\n"
					+ "4. Display course information\n"
					+ "5. Register a new student\n"
					+ "0. Exit\n"
					+ "Please enter the number corresponding to the action: ");
			selection = Integer.parseInt(input.readLine());
			System.out.println();
			if (selection == 1) admin.createCourse();
			else if (selection == 2) admin.deleteCourse();
			else if (selection == 3) admin.editCourse();
			else if (selection == 4) admin.displayCourseInfo();
			else if (selection == 5) admin.registerStudent();
			else if (selection != 0) {
				System.out.println("Not a valid selection. Please try again.");
				System.out.println();
			}
			};
		} catch (NumberFormatException | IOException e) {
			System.out.println("\nNot a valid selection. Please try again.\n");
		}
		
	}
	
	//reports menu
	private static void reportsMenu() {
		try {
			int selection = -1;
			while (selection != 0){
			System.out.print("Reports: \n"
					+ "1. View all courses\n"
					+ "2. View all full courses\n"
					+ "3. Submit full courses\n"
					+ "4. View students in a course\n"
					+ "5. View a student's enrolled courses\n"
					+ "6. Sort courses\n"
					+ "0. Exit\n"
					+ "Please enter the number corresponding to the action: ");
			selection = Integer.parseInt(input.readLine());
			System.out.println();
			if (selection == 1) admin.viewAllCourses();
			else if (selection == 2) admin.ViewAllFull();
			else if (selection == 3) admin.submitFullCourses();
			else if (selection == 4) admin.viewStudents();
			else if (selection == 5) admin.viewStudentCourses();
			else if (selection == 6) admin.sortCourses();
			else if (selection != 0) {
				System.out.println("Not a valid selection. Please try again.");
				System.out.println();
				reportsMenu();
			}
			};
		} catch (NumberFormatException | IOException e) {
			System.out.println("\nNot a valid selection. Please try again.\n");
		}
	}
	
	
//STUDENT MENU
	//student selection
	private static void studentMenu(Student student) {
		try {
			int selection = -1;
			while (selection != 0){
			System.out.print("Course Management: \n"
					+ "1. View all courses\n"
					+ "2. View available courses\n"
					+ "3. Register for a course\n"
					+ "4. Withdraw from a course\n"
					+ "5. View enrolled courses\n"
					+ "0. Log out\n"
					+ "Please enter the number corresponding to the action: ");
			selection = Integer.parseInt(input.readLine());
			System.out.println();
			if (selection == 1) student.viewAllCourses();
			else if (selection == 2) student.viewNotFull();
			else if (selection == 3) student.registerCourse(student);
			else if (selection == 4) student.withdrawCourse(student);
			else if (selection == 5) student.viewRegistered(student);
			else if (selection != 0) {
				System.out.println("Not a valid selection. Please try again.");
				reportsMenu();
			}
			};
			if (selection == 0) {
				admin.submitFullCourses();
				admin.submitCourses();
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("\nNot a valid selection. Please try again.\n");
		}
	}

	
//UPDATE FROM FILES
	
	//deserialize the course data
	public static void updateCourseData() {
		try {
			FileInputStream fis = new FileInputStream("MyUniversityCourses");
            ObjectInputStream ois = new ObjectInputStream(fis);
            School.listCourses = (ArrayList<Course>) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             System.out.println("Unable to update system courses.");
             ioe.printStackTrace();
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             c.printStackTrace();
             return;
          }
	}
	
	//deserialize the student data
	public static void updateStudentData() {
		try {
			FileInputStream fis = new FileInputStream("MyUniversityStudents");
            ObjectInputStream ois = new ObjectInputStream(fis);
            School.allStudents = (ArrayList<Student>) ois.readObject();
            ois.close();
            fis.close();
		} catch(IOException ioe){
			System.out.println("Unable to update system students.");
			ioe.printStackTrace();
        } catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
        }
	}
    
    
	//add data from a csv file
	public static void addCourseData() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("MyUniversityCourses.csv"));
			String line = reader.readLine();
			while(line != null) {
				String[] courseData = line.split(",");
				ArrayList<String[]> tempStudentList = new ArrayList<String[]>();
//				System.out.println(courseData[4]);
				String[] students = courseData[4].split(";");
				for (int i = 0; i < students.length; i++) {
					String[] studentInfo = students[i].split(" ");
					if (studentInfo.length == 3) {
						String[] tempStudent = {studentInfo[0],studentInfo[1], studentInfo[2]};
						tempStudentList.add(tempStudent);
					} 
				}
				Course tempCourse = new Course(courseData[0], courseData[1], courseData[6], courseData[5], courseData[7], courseData[2], courseData[3], tempStudentList);
				School.addCourse(tempCourse);
				line = reader.readLine();
				}
			School.listCourses.remove(0);
			reader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("File not found!");
		} catch (IOException ioe) {
			System.out.println("Unable to add course data from csv file.");
			ioe.printStackTrace();
		}
	}
	
	//add data from a csv file
	public static void addStudentData() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("MyUniversityStudents.csv"));
			String line = reader.readLine();
		    while(line != null) {
		        String[] studentData = line.split(",");
//		        (String username, String password,String studentID, String firstName, String lastName)
		        Student tempStudent = new Student(studentData[0],studentData[1],studentData[2],studentData[3],studentData[4]);
				School.registerNew(tempStudent);
				line = reader.readLine();
			}
		    line = reader.readLine();
		    School.allStudents.remove(0);
		    reader.close();
		} catch (FileNotFoundException e) {
		    System.out.println("File not found!");
		} catch (IOException ioe) {
			System.out.println("Unable to add course data from csv file.");
			ioe.printStackTrace();
		}
	}
	

}

