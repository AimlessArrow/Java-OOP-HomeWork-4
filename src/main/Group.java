package main;

//import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Group {
	private Student[] studentGroup = new Student[10];

	public Group() {
		super();
	}

	public Student getStudent(int index) {
		Student targetStudent = null;
		try {
			targetStudent = this.studentGroup[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index is out of bounds");
		}
		/* Returns null if index is out of bounds */
		return targetStudent;
	}

	public void addStudent(Student newStudent) throws overflowException {
		int vacantIndex = -1;
		for (int i = 0; i < this.studentGroup.length; i++) {
			if (this.studentGroup[i] == null) {
				vacantIndex = i;
			}
		}
		if (vacantIndex == -1) {
			throw new overflowException();
		} else {
			this.studentGroup[vacantIndex] = newStudent;
		}
	}

	public void removeStudent(int index) {
		try {
			this.studentGroup[index] = null;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index is out of bounds");
		}
	}

	public void removeStudent(String surname) {
		int targetStudent;
		targetStudent = this.searchStudent(surname);
		if (targetStudent == -1) {
			System.out.println("Student is not found");
		} else {
			this.studentGroup[targetStudent] = null;
		}
	}

	public int searchStudent(String lastName) {
		String currentSurname = "";

		for (int i = 0; i < this.studentGroup.length; i++) {
			try {
				currentSurname = this.studentGroup[i].getLastName();
			} catch (NullPointerException e) {
				continue;
			}
			if (currentSurname == lastName) {
				return i;
			}
		}
		/* Student not found */
		return -1;
	}

	public void inputStudent() throws overflowException {
		String firstName = "";
		String lastName = "";
		String patronymic = "";
		String gender = "";
		int age = 0;
		int vacantIndex = -1;
		Student newStudent = null;
		Scanner input = new Scanner(System.in);
		int genderInt = 0;

		for (;;) {
			try {
				/* Getting first name */
				System.out.println("Enter first name");
				firstName = input.next();
				if (firstName == "Exit") {
					input.close();
					return;
				}
				/* Getting last name */
				System.out.println("Enter last name");
				lastName = input.next();
				if (lastName == "Exit") {
					input.close();
					return;
				}
				/* Getting patronymic */
				System.out.println("Enter patronymic");
				patronymic = input.next();
				if (patronymic == "Exit") {
					input.close();
					return;
				}
				/* Getting gender */
				System.out.println("Choose gender: ");
				System.out.println("(1) male");
				System.out.println("(2) female");
				genderInt = input.nextInt();
				switch (genderInt) {
				case 1:
					gender = "male";
					break;
				case 2:
					gender = "female";
					break;
				default:
					continue;
				}
				/* Getting age */
				System.out.println("Enter age");
				age = input.nextInt();
				break;
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Input aborted");
				input.close();
				return;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input format");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input format");
			}
		}
		input.close();

		/* Saving results */
		newStudent = new Student(firstName, lastName, patronymic, gender, age);
		/* Inserting results into the group array */
		for (int i = 0; i < this.studentGroup.length; i++) {
			if (this.studentGroup[i] == null) {
				vacantIndex = i;
			}
		}
		if (vacantIndex == -1) {
			throw new overflowException();
		} else {
			this.studentGroup[vacantIndex] = newStudent;
		}
	}

	public void sortGroup(String sortSubject, boolean ascending) {
		Student temp;
		boolean wasSwap = true;
		switch (sortSubject) {
		case "firstName":
			for (int i = 0; i < this.studentGroup.length - 1 && wasSwap; i++) {
				wasSwap = false;
				for (int j = 0; j < this.studentGroup.length - i - 1; j++) {
					if (this.studentGroup[j].getFirstName().compareToIgnoreCase(this.studentGroup[j + 1].getFirstName()) > 0) {
						temp = this.studentGroup[j];
						this.studentGroup[j] = this.studentGroup[j + 1];
						this.studentGroup[j + 1] = temp;
						wasSwap = true;
					}
				}
			}
			break;
		case "lastName":
			for (int i = 0; i < this.studentGroup.length - 1 && wasSwap; i++) {
				wasSwap = false;
				for (int j = 0; j < this.studentGroup.length - i - 1; j++) {
					if (this.studentGroup[j].getLastName().compareToIgnoreCase(this.studentGroup[j + 1].getLastName()) > 0) {
						temp = this.studentGroup[j];
						this.studentGroup[j] = this.studentGroup[j + 1];
						this.studentGroup[j + 1] = temp;
						wasSwap = true;
					}
				}
			}
			break;
		case "patronymic":
			for (int i = 0; i < this.studentGroup.length - 1 && wasSwap; i++) {
				wasSwap = false;
				for (int j = 0; j < this.studentGroup.length - i - 1; j++) {
					if (this.studentGroup[j].getPatronymic().compareToIgnoreCase(this.studentGroup[j + 1].getPatronymic()) > 0) {
						temp = this.studentGroup[j];
						this.studentGroup[j] = this.studentGroup[j + 1];
						this.studentGroup[j + 1] = temp;
						wasSwap = true;
					}
				}
			}
			break;
		case "age":
			for (int i = 0; i < this.studentGroup.length; i++) {
				for (int j = 1; j < (this.studentGroup.length - i); j++) {
					if (this.studentGroup[j - 1].getAge() > this.studentGroup[j].getAge()) {
						temp = this.studentGroup[j - 1];
						this.studentGroup[j - 1] = this.studentGroup[j];
						this.studentGroup[j] = temp;
					}
				}
			}
			break;
		default:
			System.out.println("Invalid input");
		}
	}

	static void bubbleSort(int[] arr) {
		int n = arr.length;
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					// swap elements
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}
	}

	@Override
	public String toString() {
		String outputString = "studentGroup [";
		ArrayList<String> nameArray = new ArrayList<String>();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(currentStudent.getLastName());
			}
		}

		String temp;
		boolean wasSwap = true;
		for (int i = 0; i < nameArray.size() - 1 && wasSwap; i++) {
			wasSwap = false;
			for (int j = 0; j < nameArray.size() - i - 1; j++) {
				if (nameArray.get(j).compareToIgnoreCase(nameArray.get(j + 1)) > 0) {
					temp = nameArray.get(j);
					nameArray.set(j, nameArray.get(j + 1));
					nameArray.set(j + 1, temp);
					wasSwap = true;
				}
			}
		}

		outputString += String.join(", ", nameArray) + "]";

		return outputString;
	}
}
