package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class Group implements MilCom {
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
		Comparator<Student> comparator;
		switch (sortSubject) {
		case "firstName":
			comparator = Comparator.comparing(obj -> obj.getFirstName());
			if (ascending == false) {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
			} else {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
			}
			break;
		case "lastName":
			comparator = Comparator.comparing(obj -> obj.getLastName());
			if (ascending == false) {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
			} else {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
			}
			break;
		case "patronymic":
			comparator = Comparator.comparing(obj -> obj.getPatronymic());
			if (ascending == false) {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
			} else {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
			}
			break;
		case "age":
			comparator = Comparator.comparing(obj -> obj.getAge());
			if (ascending == false) {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
			} else {
				Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
			}
			break;
		default:
			System.out.println("Invalid input");
		}
	}
	
	public void printGroup(String sortSubject) {
		String outputString = "studentGroup [";
		ArrayList<String> nameArray = new ArrayList<String>();
		switch (sortSubject) {
		case "firstName":
			for (Student currentStudent : this.studentGroup) {
				if (currentStudent != null) {
					nameArray.add(currentStudent.getFirstName());
				}
			}
			break;
		case "lastName":
			for (Student currentStudent : this.studentGroup) {
				if (currentStudent != null) {
					nameArray.add(currentStudent.getLastName());
				}
			}
			break;
		case "patronymic":
			for (Student currentStudent : this.studentGroup) {
				if (currentStudent != null) {
					nameArray.add(currentStudent.getPatronymic());
				}
			}
			break;
		case "age":
			for (Student currentStudent : this.studentGroup) {
				if (currentStudent != null) {
					nameArray.add(Integer.toString(currentStudent.getAge()));
				}
			}
			break;
		default:
			System.out.println("Invalid input");
		}
		outputString += String.join(", ", nameArray) + "]";
		System.out.println(outputString);
	}

	public Student[] getRecruits() {
		System.out.println(this.studentGroup[0]);
		Student[] recruits = new Student[10];
		int tempAge;
		String tempGender;
		int counter = 0;
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent == null) {
				continue;
			}
			tempAge = currentStudent.getAge();
			tempGender = currentStudent.getGender();
			if (tempAge >= 18 && tempGender == "male") {
				/* Deep copy of an object */
				/*recruits[counter] = new Student(currentStudent.getFirstName(), 
						currentStudent.getLastName(), currentStudent.getPatronymic(),
						currentStudent.getGender(), currentStudent.getAge());*/
				recruits[counter] = currentStudent;
				counter++;
			}
		}
		return recruits;
	}

	@Override
	public String toString() {
		String outputString = "studentGroup [";
		// this.sortGroup("lastName", true);
		ArrayList<String> nameArray = new ArrayList<String>();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(currentStudent.getLastName());
			}
		}
		outputString += String.join(", ", nameArray) + "]";

		return outputString;
	}
}
