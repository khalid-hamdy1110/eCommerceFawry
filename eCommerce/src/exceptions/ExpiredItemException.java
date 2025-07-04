package exceptions;

public class ExpiredItemException extends InvalidProcessException {

	public ExpiredItemException() {
		super();
	}

	public ExpiredItemException(String message) {
		super(message);
	}
	
}
