package main;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
		/* First name, last name and patronymic */
		String[] nameArray = new String[3]; 
		String gender;
		int age = 0;
		Student newStudent = null;
		Scanner input = new Scanner(System.in);

		while (true) {
			try {
				nameArray[0] = this.inputName(input, "Enter First Name");
				nameArray[1] = this.inputName(input, "Enter Last Name");
				nameArray[2] = this.inputName(input, "Enter Patronymic");
				gender = this.inputGender(input);
				age = this.inputAge(input);
				break;
			} catch (NullPointerException e) {
				System.out.println("Input aborted");
				input.close();
				return;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input format");
				continue;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input format");
				continue;
			}
		}
		input.close();

		/* Saving results */
		newStudent = new Student(nameArray[0], nameArray[1], nameArray[2], gender, age);
		/* Inserting results into the group array */
		this.addStudent(newStudent);
	}

	private String inputName(Scanner input, String message) {
		/* Getting first, last name and patronymic */
		String name;
		if (message == null) {
			message = "Enter your name";
		}
		System.out.println(message);
		name = input.next();
		if (name == "Exit") {
			input.close();
			return null;
		} else {
			return name;
		}
	}

	private String inputGender(Scanner input) {
		/* Getting gender */
		int genderInt = 0;
		System.out.println("Choose gender: ");
		System.out.println("(1) male");
		System.out.println("(2) female");
		genderInt = input.nextInt();
		switch (genderInt) {
		case 1:
			return "male";
		case 2:
			return "female";
		default:
			System.out.println("Wrong input. Try again.");
			return null;
		}
	}

	private int inputAge(Scanner input) {
		/* Getting age */
		System.out.println("Enter age");
		int age;
		age = input.nextInt();
		return age;
	}

	public void sortGroup(String sortSubject, boolean ascending) {
		switch (sortSubject) {
		case "firstName":
			this.sortByFirstName(ascending);
			break;
		case "lastName":
			this.sortByLastName(ascending);
			break;
		case "patronymic":
			this.sortByPatronymic(ascending);
			break;
		case "age":
			this.sortByAge(ascending);
			break;
		default:
			System.out.println("Invalid input");
		}
	}

	private void sortByFirstName(boolean ascending) {
		Comparator<Student> comparator;
		comparator = Comparator.comparing(obj -> obj.getFirstName());
		if (ascending == false) {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
		} else {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
		}
	}

	private void sortByLastName(boolean ascending) {
		Comparator<Student> comparator;
		comparator = Comparator.comparing(obj -> obj.getLastName());
		if (ascending == false) {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
		} else {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
		}
	}

	private void sortByPatronymic(boolean ascending) {
		Comparator<Student> comparator;
		comparator = Comparator.comparing(obj -> obj.getPatronymic());
		if (ascending == false) {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
		} else {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
		}
	}

	private void sortByAge(boolean ascending) {
		Comparator<Student> comparator;
		comparator = Comparator.comparing(obj -> obj.getAge());
		if (ascending == false) {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator.reversed()));
		} else {
			Arrays.sort(this.studentGroup, Comparator.nullsLast(comparator));
		}
	}

	public void printGroup(String sortSubject) {
		switch (sortSubject) {
		case "firstName":
			this.printFirstNames();
			break;
		case "lastName":
			this.printLastNames();
			break;
		case "patronymic":
			this.printPatronymics();
			break;
		case "age":
			this.printAge();
			break;
		default:
			System.out.println("Invalid input");
		}
	}

	private void printFirstNames() {
		String outputString = "studentGroup [";
		ArrayList nameArray = new ArrayList();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(currentStudent.getFirstName());
			}
		}
		outputString += String.join(", ", nameArray) + "]";
		System.out.println(outputString);
	}

	private void printLastNames() {
		String outputString = "studentGroup [";
		ArrayList nameArray = new ArrayList();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(currentStudent.getLastName());
			}
		}
		outputString += String.join(", ", nameArray) + "]";
		System.out.println(outputString);
	}

	private void printPatronymics() {
		String outputString = "studentGroup [";
		ArrayList nameArray = new ArrayList();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(currentStudent.getPatronymic());
			}
		}
		outputString += String.join(", ", nameArray) + "]";
		System.out.println(outputString);
	}

	private void printAge() {
		String outputString = "studentGroup [";
		ArrayList nameArray = new ArrayList();
		for (Student currentStudent : this.studentGroup) {
			if (currentStudent != null) {
				nameArray.add(Integer.toString(currentStudent.getAge()));
			}
		}
		outputString += String.join(", ", nameArray) + "]";
		System.out.println(outputString);
	}

	public Student[] getRecruits() {
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
			if (tempAge >= 18 && tempGender.equals("male")) {
				/* Deep copy of an object */
				recruits[counter] = new Student(currentStudent.getFirstName(), currentStudent.getLastName(),
						currentStudent.getPatronymic(), currentStudent.getGender(), currentStudent.getAge());
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
