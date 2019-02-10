package main;

public class Student extends Human {
	private int studentID;

	public Student() {}
	
	public Student(int studentID) {
		super();
		this.studentID = studentID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	@Override
	public String toString() {
		return "Student [firstName" + this.getFirstName() 
		+ ", lastName=" + this.getLastName() 
		+ ", patronymic=" + this.getPatronymic()
		+ ", gender=" + this.getGender()
		+ ", age=" + this.getAge()
		+ ", studentID=" + studentID + "]";
	}
}
