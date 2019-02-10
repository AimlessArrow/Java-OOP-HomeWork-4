package main;

public class overflowException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "The student group is already full";
	}
}
