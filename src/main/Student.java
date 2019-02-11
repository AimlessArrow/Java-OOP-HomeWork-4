package main;

public class Student extends Human {

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String patronymic, String gender, int age) {
		super(firstName, lastName, patronymic, gender, age);
	}

	@Override
	public String toString() {
		return "Student [firstName=" + this.getFirstName() 
		+ ", lastName=" + this.getLastName() 
		+ ", patronymic=" + this.getPatronymic()
		+ ", gender=" + this.getGender()
		+ ", age=" + this.getAge() + "]";
	}
}
