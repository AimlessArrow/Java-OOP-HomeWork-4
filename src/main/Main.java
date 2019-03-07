package main;

public class Main {

	public static void main(String[] args) {
		Group myGroup = new Group();

		/* Populating student group */
		try {
			myGroup.addStudent(new Student("John", "Johnson", "John", "male", 23));
			myGroup.addStudent(new Student("Fred", "Fredericson", "Frank", "male", 40));
			myGroup.addStudent(new Student("Helen", "Smith", "Veronica", "female", 35));
			myGroup.addStudent(new Student("Summer", "Times", "Victoria", "female", 33));
			myGroup.addStudent(new Student("Ava", "Terry", "Evelyn", "female", 32));
			myGroup.addStudent(new Student("Audrey", "McDonald", "Franklin", "male", 50));
			myGroup.addStudent(new Student("Jasmine", "Bond", "Bond", "female", 23));
			myGroup.addStudent(new Student("Rebecca", "Welch", "Victoria", "female", 47));
			myGroup.addStudent(new Student("Ian", "Hardacre", "George", "male", 49));
			// myGroup.addStudent(new Student("John", "Malkovich", "Junior", "male", 51));
		} catch (overflowException e) {
			System.out.println(e);
		}
		//System.out.println(myGroup);

		/* inputStudent() adds new a student to the myGroup array */
		/*try {
			myGroup.inputStudent();
		} catch (overflowException e) {
			System.out.println(e);
		}*/

		/* example of sorting by sortGroup() function */
		//myGroup.sortGroup("age", true);
		//myGroup.printGroup("age");
		
		/* getRecruits() returns an array of recruits of 18 years old or higher */
		Student[] newRecruits;
		newRecruits = myGroup.getRecruits();
		System.out.println(newRecruits);
	}

}
