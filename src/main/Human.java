package main;

public class Human {
	private String firstName;
	private String lastName;
	private String patronymic;
	private String gender;
	private int age;
	
	public Human() {
		super();
	}

	public Human(String firstName, String lastName, String patronymic, String gender, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
		this.gender = gender;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Human [firstName=" + this.firstName 
				+ ", lastName=" + this.lastName
				+ ", patronymic=" + this.patronymic
				+ ", gender=" + this.gender
				+ ", age=" + this.age + "]";
	}
}
