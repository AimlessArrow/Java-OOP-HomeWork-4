package main;
import java.util.Collections;
import java.util.ArrayList;

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
}
