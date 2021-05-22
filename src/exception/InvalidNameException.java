package exception;

public class InvalidNameException extends Exception {
	private static final long serialVersionUID = 1L;
	private String type;

	public InvalidNameException(String type) {
		this.type = type;
		System.out.println(type);
	}

	public String getType() {
		return type;
	}
}