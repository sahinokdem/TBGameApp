package exceptionsLayer;

public class SpearThrownException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2215179829169203899L;
	
	public SpearThrownException() {
		super("Not a unique name! ");
	}
	public SpearThrownException(String message) {
		super(message);
	}
}
