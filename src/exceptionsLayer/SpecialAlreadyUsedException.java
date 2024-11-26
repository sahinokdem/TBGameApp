package exceptionsLayer;

public class SpecialAlreadyUsedException extends Exception{

	/**
	 *   Each character can use their special action once per game
	 */
	private static final long serialVersionUID = 9045670556355711037L;
	
	public SpecialAlreadyUsedException() {
		super("Special is already used! ");
	}
	public SpecialAlreadyUsedException(String message) {
		super(message);
	}

}
