package exceptions;

public class AvailableQuantityExceededException extends InvalidProcessException {
	public AvailableQuantityExceededException() {
		super();
	}

	public AvailableQuantityExceededException(String message) {
		super(message);
	}
}
