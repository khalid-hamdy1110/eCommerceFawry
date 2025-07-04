package exceptions;

public class InsufficientBalanceException extends InvalidProcessException {

	public InsufficientBalanceException() {
		super();
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}
	
}
