package exceptions;

public class OutOfStockException extends InvalidProcessException {

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(String message) {
		super(message);
	}
	
}
