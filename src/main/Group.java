package main;

import java.util.Arrays;

public class Group {
	private Student[] studentGroup = new Student[10];
	
	public Group() {}

	public Student getStudent(int index) {
		return studentGroup[index];
	}
	
	public void addStudent(Student newStudent) throws overflowException{
		int vacantIndex = -1;
		for(int i = 0; i < this.studentGroup.length; i++) {
			if(this.studentGroup[i] == null) {
				vacantIndex = i; 
			}
		}
		if(vacantIndex == -1) {
			throw new overflowException();
		} else {
			this.studentGroup[vacantIndex] = newStudent;
		}
	}
	
	public Student searchStudent(String lastName) {
		String currentSurname = "";
		for(Student currentStudent : studentGroup) {
			try {
				currentSurname = currentStudent.getLastName();
			} catch(Exception e) {
				
			}
			if(currentSurname == lastName) {
				return currentStudent;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Group [studentGroup=" + Arrays.toString(studentGroup) + "]";
	}
}
