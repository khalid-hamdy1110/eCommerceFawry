package exceptions;

public class EmptyCartExcpetion extends InvalidProcessException {

	public EmptyCartExcpetion() {
		super();
	}

	public EmptyCartExcpetion(String message) {
		super(message);
	}
	
}
