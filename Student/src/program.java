import java.util.List;
import java.util.Scanner;

import controller.StudentHelper;
import model.Student;

/**
 * @author DeoTheHung -The Deo
 * CIS175 Spring 2022
 * Feb 3, 2022
 */
public class program {
	static Scanner in = new Scanner(System.in);
	static StudentHelper sth = new StudentHelper();
	
	private static void addAStudent() {
		System.out.print("Enter first name: ");
		String firstName = in.nextLine();
		System.out.print("Enter last name: ");
		String lastName = in.nextLine();
		Student toAdd = new Student(lastName,firstName);
		sth.insertStudent(toAdd);

	}
	
	private static void deleteAStudent() {
		System.out.print("Enter the first name to delete: ");
		String firstName = in.nextLine();
		System.out.print("Enter the last name to delete: ");
		String lastName = in.nextLine();
		Student toDelete = new Student(lastName,firstName);
		sth.deleteStudent(toDelete);

	}

	private static void editAStudent() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by First Name");
		System.out.println("2 : Search by Last Name");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Student> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the student's first name: ");
			String firstName = in.nextLine();
			foundItems = sth.searchForStudentByFirstName(firstName);
			
		} else {
			System.out.print("Enter the student's last name: ");
			String lastName = in.nextLine();
			foundItems = sth.searchForStudentByLastName(lastName);

		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (Student s : foundItems) {
				System.out.println(s.getStudentID() + " : " + s.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Student toEdit = sth.searchForStudentByStudentId(idToEdit);
			System.out.println("Student: " + toEdit.getFirstName() + toEdit.getLastName());
			System.out.println("1 : Update First Name");
			System.out.println("2 : Update Last Name");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New First Name: ");
				String newFirstName = in.nextLine();
				toEdit.setFirstName(newFirstName);;
			} else if (update == 2) {
				System.out.print("New Last Name: ");
				String newLastName = in.nextLine();
				toEdit.setLastName(newLastName);;
			}

			sth.updateStudent(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	private static void viewTheList() {
		List<Student> allStudent = sth.showAllStudent();
		for (Student singleStudent : allStudent) {
			System.out.println(singleStudent.fullName());
		}
	}
	
	public static void main(String[] args) {
		runMenu();

	}
	
	public static void runMenu() {
		boolean goAgain = true;
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a student");
			System.out.println("*  2 -- Delete a student");
			System.out.println("*  3 -- Edit");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAStudent();
			} else if (selection == 2) {
			    deleteAStudent();				
			} else if (selection == 3) {
				editAStudent();
			} else if (selection == 4) {
				viewTheList();
			} else {
				sth.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

}
