package exceptionsLayer;

public class InsufficientStaminaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7156467168558803152L;
	
	public InsufficientStaminaException() {
		super("Insufficient Stamina! ");
	}
	public InsufficientStaminaException(String message) {
		super(message);
	}
}
