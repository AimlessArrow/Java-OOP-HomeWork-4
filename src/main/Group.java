package main;
import java.util.Collections;
import java.util.InputMismatchException;
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
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Index is out of bounds");
		}
		/* Returns null if index is out of bounds */
		return targetStudent;
	}
	
	public void addStudent(Student newStudent) throws overflowException {
		int vacantIndex = -1;
		for(int i = 0; i < this.studentGroup.length; i++) {
			if(this.studentGroup[i] == null) {
				vacantIndex = i;
			}
		}
		if(vacantIndex == -1) {
			throw new overflowException();
		} else{
			this.studentGroup[vacantIndex] = newStudent;
		}
	}
	
	public void removeStudent(int index) {
		try {
			this.studentGroup[index] = null;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Index is out of bounds");
		}
	}
	
	public void removeStudent(String surname) {
		int targetStudent;
		targetStudent = this.searchStudent(surname);
		if(targetStudent == -1) {
			System.out.println("Student is not found");
		}
		else {
			this.studentGroup[targetStudent] = null;
		}
	}
	
	public int searchStudent(String lastName) {
		String currentSurname = "";
		
		for(int i = 0; i < this.studentGroup.length; i++) {
			try {
				currentSurname = this.studentGroup[i].getLastName();
			} catch(NullPointerException e) {
				continue;
			}
			if(currentSurname == lastName) {
				return i;
			}
		}
		/* Student not found */
		return -1;
	}
	
	@Override
	public String toString() {
		String outputString = "studentGroup [";
		ArrayList<String> nameArray = new ArrayList<String>();
		for(Student currentStudent : this.studentGroup) {
			if(currentStudent != null) {
				nameArray.add(currentStudent.getLastName());
			}
		}
		
		Collections.sort(nameArray);
		for(String name : nameArray) {
			if(nameArray.indexOf(name) == (nameArray.size() - 1)) {
				outputString += name + "]";
			} else {
				outputString += name + ", ";
			}
		}
		
		return outputString;
	}
	
	public void inputStudent() throws overflowException {
		String firstName = "";
		String lastName = "";
		String patronymic = "";
		String gender = "";
		int age = 0;
		int vacantIndex = -1;
		Student newStudent = null;
		String[] options = {"male", "female"};
		
		for(;;) {
			try {
				/* Getting first name */
				firstName = JOptionPane.showInputDialog("Enter first name");
				if(firstName == null) {
					return;
				}
				/* Getting last name */
				lastName = JOptionPane.showInputDialog("Enter last name");
				if(lastName == null) {
					return;
				}
				/* Getting patronymic */
				patronymic = JOptionPane.showInputDialog("Enter patronymic");
				if(patronymic == null) {
					return;
				}
				/* Getting gender */
				gender = JOptionPane.showOptionDialog(null, "Select gender", "Gender selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])
											== 0 ? options[0] : options[1];
				/* Getting age */
				age = Integer.parseInt(JOptionPane.showInputDialog("Enter age"));
				break;
			} catch(NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Input aborted");
				return;
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input format");
				continue;
			} catch(InputMismatchException e) {
				System.out.println("Invalid input format");
			}
		}
		/* Saving results */
		newStudent = new Student(firstName, lastName, patronymic, gender, age);
		/* Inserting results into the group array */
		for(int i = 0; i < this.studentGroup.length; i++) {
			if(this.studentGroup[i] == null) {
				vacantIndex = i;
			}
		}
		if(vacantIndex == -1) {
			throw new overflowException();
		} else{
			this.studentGroup[vacantIndex] = newStudent;
		}
	}
}
