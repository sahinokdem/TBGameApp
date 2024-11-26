package exceptionsLayer;

public class NotAUniqueNameException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2549849591854608414L;
	public NotAUniqueNameException() {
		super("You throw spear last turn, you will wait this turn");
	}
	public NotAUniqueNameException(String message) {
		super(message);
	}

}
